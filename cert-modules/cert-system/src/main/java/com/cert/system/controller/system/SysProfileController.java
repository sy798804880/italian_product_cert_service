package com.cert.system.controller.system;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import lombok.RequiredArgsConstructor;
import com.cert.common.core.domain.R;
import com.cert.common.core.utils.StringUtils;
import com.cert.common.core.utils.file.MimeTypeUtils;
import com.cert.common.encrypt.annotation.ApiEncrypt;
import com.cert.common.idempotent.annotation.RepeatSubmit;
import com.cert.common.log.annotation.Log;
import com.cert.common.log.enums.BusinessType;
import com.cert.common.mybatis.helper.DataPermissionHelper;
import com.cert.common.satoken.utils.LoginHelper;
import com.cert.common.web.core.BaseController;
import com.cert.system.domain.bo.SysUserBo;
import com.cert.system.domain.bo.SysUserPasswordBo;
import com.cert.system.domain.bo.SysUserProfileBo;
import com.cert.system.domain.vo.AvatarVo;
import com.cert.system.domain.vo.ProfileVo;
import com.cert.system.domain.vo.SysOssVo;
import com.cert.system.domain.vo.SysUserVo;
import com.cert.system.service.ISysOssService;
import com.cert.system.service.ISysUserService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * 个人信息 业务处理
 *
 * @author Lion Li
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {

    private final ISysUserService userService;
    private final ISysOssService ossService;

    /**
     * 个人信息
     */
    @GetMapping
    public R<ProfileVo> profile() {
        SysUserVo user = userService.selectUserById(LoginHelper.getUserId());
        ProfileVo profileVo = new ProfileVo();
        profileVo.setUser(user);
        profileVo.setRoleGroup(userService.selectUserRoleGroup(user.getUserId()));
        profileVo.setPostGroup(userService.selectUserPostGroup(user.getUserId()));
        return R.ok(profileVo);
    }

    /**
     * 修改用户信息
     */
    @RepeatSubmit
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Void> updateProfile(@Validated @RequestBody SysUserProfileBo profile) {
        SysUserBo user = BeanUtil.toBean(profile, SysUserBo.class);
        user.setUserId(LoginHelper.getUserId());
        String username = LoginHelper.getUsername();
        if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user)) {
            return R.fail("修改用户'" + username + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user)) {
            return R.fail("修改用户'" + username + "'失败，邮箱账号已存在");
        }
        int rows = DataPermissionHelper.ignore(() -> userService.updateUserProfile(user));
        if (rows > 0) {
            return R.ok();
        }
        return R.fail("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     *
     * @param bo 新旧密码
     */
    @RepeatSubmit
    @ApiEncrypt
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public R<Void> updatePwd(@Validated @RequestBody SysUserPasswordBo bo) {
        SysUserVo user = userService.selectUserById(LoginHelper.getUserId());
        String password = user.getPassword();
        if (!BCrypt.checkpw(bo.getOldPassword(), password)) {
            return R.fail("修改密码失败，旧密码错误");
        }
        if (BCrypt.checkpw(bo.getNewPassword(), password)) {
            return R.fail("新密码不能与旧密码相同");
        }
        int rows = DataPermissionHelper.ignore(() -> userService.resetUserPwd(user.getUserId(), BCrypt.hashpw(bo.getNewPassword())));
        if (rows > 0) {
            return R.ok();
        }
        return R.fail("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     *
     * @param avatarfile 用户头像
     */
    @RepeatSubmit
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<AvatarVo> avatar(@RequestPart("avatarfile") MultipartFile avatarfile) {
        if (!avatarfile.isEmpty()) {
            String extension = FileUtil.extName(avatarfile.getOriginalFilename());
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
                return R.fail("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
            }
            SysOssVo oss = ossService.upload(avatarfile);
            String avatar = oss.getUrl();
            boolean updateSuccess = DataPermissionHelper.ignore(() -> userService.updateUserAvatar(LoginHelper.getUserId(), oss.getOssId()));
            if (updateSuccess) {
                AvatarVo avatarVo = new AvatarVo();
                avatarVo.setImgUrl(avatar);
                return R.ok(avatarVo);
            }
        }
        return R.fail("上传图片异常，请联系管理员");
    }
}
