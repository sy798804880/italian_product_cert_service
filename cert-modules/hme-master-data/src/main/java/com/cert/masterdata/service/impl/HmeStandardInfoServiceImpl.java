package com.cert.masterdata.service.impl;

import com.cert.common.core.utils.MapstructUtils;
import com.cert.common.core.utils.StringUtils;
import com.cert.common.mybatis.core.page.TableDataInfo;
import com.cert.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cert.masterdata.domain.bo.HmeStandardInfoBo;
import com.cert.masterdata.domain.vo.HmeStandardInfoVo;
import com.cert.masterdata.domain.HmeStandardInfo;
import com.cert.masterdata.mapper.HmeStandardInfoMapper;
import com.cert.masterdata.service.IHmeStandardInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 主数据 - 标准信息Service业务层处理
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@RequiredArgsConstructor
@Service
public class HmeStandardInfoServiceImpl implements IHmeStandardInfoService {

    private final HmeStandardInfoMapper baseMapper;

    /**
     * 查询主数据 - 标准信息
     *
     * @param id 主键
     * @return 主数据 - 标准信息
     */
    @Override
    public HmeStandardInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询主数据 - 标准信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 主数据 - 标准信息分页列表
     */
    @Override
    public TableDataInfo<HmeStandardInfoVo> queryPageList(HmeStandardInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<HmeStandardInfo> lqw = buildQueryWrapper(bo);
        Page<HmeStandardInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的主数据 - 标准信息列表
     *
     * @param bo 查询条件
     * @return 主数据 - 标准信息列表
     */
    @Override
    public List<HmeStandardInfoVo> queryList(HmeStandardInfoBo bo) {
        LambdaQueryWrapper<HmeStandardInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<HmeStandardInfo> buildQueryWrapper(HmeStandardInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HmeStandardInfo> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(HmeStandardInfo::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getStandardCode()), HmeStandardInfo::getStandardCode, bo.getStandardCode());
        lqw.eq(StringUtils.isNotBlank(bo.getStandardType()), HmeStandardInfo::getStandardType, bo.getStandardType());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), HmeStandardInfo::getDescription, bo.getDescription());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), HmeStandardInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增主数据 - 标准信息
     *
     * @param bo 主数据 - 标准信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(HmeStandardInfoBo bo) {
        HmeStandardInfo add = MapstructUtils.convert(bo, HmeStandardInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改主数据 - 标准信息
     *
     * @param bo 主数据 - 标准信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(HmeStandardInfoBo bo) {
        HmeStandardInfo update = MapstructUtils.convert(bo, HmeStandardInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HmeStandardInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除主数据 - 标准信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
