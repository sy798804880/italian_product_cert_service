package com.cert.web.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import com.cert.common.core.constant.SystemConstants;
import com.cert.common.core.domain.model.LoginUser;
import com.cert.common.core.domain.model.SocialLoginBody;
import com.cert.common.core.exception.ServiceException;
import com.cert.common.core.exception.user.UserException;
import com.cert.common.core.utils.StreamUtils;
import com.cert.common.core.utils.ValidatorUtils;
import com.cert.common.json.utils.JsonUtils;
import com.cert.common.satoken.utils.LoginHelper;
import com.cert.common.social.config.properties.SocialProperties;
import com.cert.common.social.utils.SocialUtils;
import com.cert.common.tenant.helper.TenantHelper;
import com.cert.system.domain.vo.SysClientVo;
import com.cert.system.domain.vo.SysSocialVo;
import com.cert.system.domain.vo.SysUserVo;
import com.cert.system.mapper.SysUserMapper;
import com.cert.system.service.ISysSocialService;
import com.cert.web.domain.vo.LoginVo;
import com.cert.web.service.IAuthStrategy;
import com.cert.web.service.SysLoginService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 第三方授权策略
 *
 * @author thiszhc is 三三
 */
@Slf4j
@Service("social" + IAuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class SocialAuthStrategy implements IAuthStrategy {

    private final SocialProperties socialProperties;
    private final ISysSocialService sysSocialService;
    private final SysUserMapper userMapper;
    private final SysLoginService loginService;

    /**
     * 登录-第三方授权登录
     *
     * @param body     登录信息
     * @param client   客户端信息
     */
    @Override
    public LoginVo login(String body, SysClientVo client) {
        SocialLoginBody loginBody = JsonUtils.parseObject(body, SocialLoginBody.class);
        ValidatorUtils.validate(loginBody);
        AuthResponse<AuthUser> response = SocialUtils.loginAuth(
                loginBody.getSource(), loginBody.getSocialCode(),
                loginBody.getSocialState(), socialProperties);
        if (!response.ok()) {
            throw new ServiceException(response.getMsg());
        }
        AuthUser authUserData = response.getData();
        if ("GITEE".equals(authUserData.getSource())) {
            // 如用户使用 gitee 登录顺手 star 给作者一点支持 拒绝白嫖
            HttpUtil.createRequest(Method.PUT, "https://gitee.com/api/v5/user/starred/cert/com-Vue-Plus")
                    .formStr(MapUtil.of("access_token", authUserData.getToken().getAccessToken()))
                    .executeAsync();
            HttpUtil.createRequest(Method.PUT, "https://gitee.com/api/v5/user/starred/cert/com-Cloud-Plus")
                    .formStr(MapUtil.of("access_token", authUserData.getToken().getAccessToken()))
                    .executeAsync();
        }

        List<SysSocialVo> list = sysSocialService.selectByAuthId(authUserData.getSource() + authUserData.getUuid());
        if (CollUtil.isEmpty(list)) {
            throw new ServiceException("你还没有绑定第三方账号，绑定后才可以登录！");
        }
        SysSocialVo social;
        if (TenantHelper.isEnable()) {
            Optional<SysSocialVo> opt = StreamUtils.findAny(list, x -> x.getTenantId().equals(loginBody.getTenantId()));
            if (opt.isEmpty()) {
                throw new ServiceException("对不起，你没有权限登录当前租户！");
            }
            social = opt.get();
        } else {
            social = list.get(0);
        }
        LoginUser loginUser = TenantHelper.dynamic(social.getTenantId(), () -> {
            SysUserVo user = loadUser(social.getUserId());
            // 此处可根据登录用户的数据不同 自行创建 loginUser 属性不够用继承扩展就行了
            return loginService.buildLoginUser(user);
        });
        loginUser.setClientKey(client.getClientKey());
        loginUser.setDeviceType(client.getDeviceType());
        SaLoginModel model = new SaLoginModel();
        model.setDevice(client.getDeviceType());
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        model.setTimeout(client.getTimeout());
        model.setActiveTimeout(client.getActiveTimeout());
        model.setExtra(LoginHelper.CLIENT_KEY, client.getClientId());
        // 生成token
        LoginHelper.login(loginUser, model);

        LoginVo loginVo = new LoginVo();
        loginVo.setAccessToken(StpUtil.getTokenValue());
        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setClientId(client.getClientId());
        return loginVo;
    }

    private SysUserVo loadUser(Long userId) {
        SysUserVo user = userMapper.selectVoById(userId);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", "");
            throw new UserException("user.not.exists", "");
        } else if (SystemConstants.DISABLE.equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", "");
            throw new UserException("user.blocked", "");
        }
        return user;
    }

}
