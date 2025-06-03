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
import com.cert.masterdata.domain.bo.HmeChemicalRuleBo;
import com.cert.masterdata.domain.vo.HmeChemicalRuleVo;
import com.cert.masterdata.domain.HmeChemicalRule;
import com.cert.masterdata.mapper.HmeChemicalRuleMapper;
import com.cert.masterdata.service.IHmeChemicalRuleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 主数据 - 化学规则Service业务层处理
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@RequiredArgsConstructor
@Service
public class HmeChemicalRuleServiceImpl implements IHmeChemicalRuleService {

    private final HmeChemicalRuleMapper baseMapper;

    /**
     * 查询主数据 - 化学规则
     *
     * @param id 主键
     * @return 主数据 - 化学规则
     */
    @Override
    public HmeChemicalRuleVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询主数据 - 化学规则列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 主数据 - 化学规则分页列表
     */
    @Override
    public TableDataInfo<HmeChemicalRuleVo> queryPageList(HmeChemicalRuleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<HmeChemicalRule> lqw = buildQueryWrapper(bo);
        Page<HmeChemicalRuleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的主数据 - 化学规则列表
     *
     * @param bo 查询条件
     * @return 主数据 - 化学规则列表
     */
    @Override
    public List<HmeChemicalRuleVo> queryList(HmeChemicalRuleBo bo) {
        LambdaQueryWrapper<HmeChemicalRule> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<HmeChemicalRule> buildQueryWrapper(HmeChemicalRuleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HmeChemicalRule> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(HmeChemicalRule::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getRuleCode()), HmeChemicalRule::getRuleCode, bo.getRuleCode());
        lqw.eq(bo.getAlloyId() != null, HmeChemicalRule::getAlloyId, bo.getAlloyId());
        lqw.eq(bo.getStandardId() != null, HmeChemicalRule::getStandardId, bo.getStandardId());
        lqw.eq(bo.getItemId() != null, HmeChemicalRule::getItemId, bo.getItemId());
        lqw.eq(bo.getMinValue() != null, HmeChemicalRule::getMinValue, bo.getMinValue());
        lqw.eq(bo.getMaxValue() != null, HmeChemicalRule::getMaxValue, bo.getMaxValue());
        lqw.eq(StringUtils.isNotBlank(bo.getValueType()), HmeChemicalRule::getValueType, bo.getValueType());
        lqw.eq(StringUtils.isNotBlank(bo.getFixedString()), HmeChemicalRule::getFixedString, bo.getFixedString());
        lqw.eq(StringUtils.isNotBlank(bo.getCheckLimitFlag()), HmeChemicalRule::getCheckLimitFlag, bo.getCheckLimitFlag());
        lqw.eq(StringUtils.isNotBlank(bo.getPrintReportFlag()), HmeChemicalRule::getPrintReportFlag, bo.getPrintReportFlag());
        lqw.eq(StringUtils.isNotBlank(bo.getPrintCertFlag()), HmeChemicalRule::getPrintCertFlag, bo.getPrintCertFlag());
        return lqw;
    }

    /**
     * 新增主数据 - 化学规则
     *
     * @param bo 主数据 - 化学规则
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(HmeChemicalRuleBo bo) {
        HmeChemicalRule add = MapstructUtils.convert(bo, HmeChemicalRule.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改主数据 - 化学规则
     *
     * @param bo 主数据 - 化学规则
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(HmeChemicalRuleBo bo) {
        HmeChemicalRule update = MapstructUtils.convert(bo, HmeChemicalRule.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HmeChemicalRule entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除主数据 - 化学规则信息
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
