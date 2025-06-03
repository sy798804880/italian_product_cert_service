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
import com.cert.masterdata.domain.bo.HmeMechanicalRuleBo;
import com.cert.masterdata.domain.vo.HmeMechanicalRuleVo;
import com.cert.masterdata.domain.HmeMechanicalRule;
import com.cert.masterdata.mapper.HmeMechanicalRuleMapper;
import com.cert.masterdata.service.IHmeMechanicalRuleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 主数据 - 机械规则Service业务层处理
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@RequiredArgsConstructor
@Service
public class HmeMechanicalRuleServiceImpl implements IHmeMechanicalRuleService {

    private final HmeMechanicalRuleMapper baseMapper;

    /**
     * 查询主数据 - 机械规则
     *
     * @param id 主键
     * @return 主数据 - 机械规则
     */
    @Override
    public HmeMechanicalRuleVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询主数据 - 机械规则列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 主数据 - 机械规则分页列表
     */
    @Override
    public TableDataInfo<HmeMechanicalRuleVo> queryPageList(HmeMechanicalRuleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<HmeMechanicalRule> lqw = buildQueryWrapper(bo);
        Page<HmeMechanicalRuleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的主数据 - 机械规则列表
     *
     * @param bo 查询条件
     * @return 主数据 - 机械规则列表
     */
    @Override
    public List<HmeMechanicalRuleVo> queryList(HmeMechanicalRuleBo bo) {
        LambdaQueryWrapper<HmeMechanicalRule> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<HmeMechanicalRule> buildQueryWrapper(HmeMechanicalRuleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HmeMechanicalRule> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(HmeMechanicalRule::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getRuleCode()), HmeMechanicalRule::getRuleCode, bo.getRuleCode());
        lqw.eq(StringUtils.isNotBlank(bo.getSubRuleCode()), HmeMechanicalRule::getSubRuleCode, bo.getSubRuleCode());
        lqw.eq(StringUtils.isNotBlank(bo.getMaterialTypeCode()), HmeMechanicalRule::getMaterialTypeCode, bo.getMaterialTypeCode());
        lqw.eq(StringUtils.isNotBlank(bo.getProductShapeCode()), HmeMechanicalRule::getProductShapeCode, bo.getProductShapeCode());
        lqw.eq(StringUtils.isNotBlank(bo.getGeneralSpecCode()), HmeMechanicalRule::getGeneralSpecCode, bo.getGeneralSpecCode());
        lqw.eq(StringUtils.isNotBlank(bo.getInternalTemperCode()), HmeMechanicalRule::getInternalTemperCode, bo.getInternalTemperCode());
        lqw.eq(StringUtils.isNotBlank(bo.getDimensionSpecCode()), HmeMechanicalRule::getDimensionSpecCode, bo.getDimensionSpecCode());
        lqw.eq(bo.getAlloyId() != null, HmeMechanicalRule::getAlloyId, bo.getAlloyId());
        lqw.eq(bo.getStandardId() != null, HmeMechanicalRule::getStandardId, bo.getStandardId());
        lqw.eq(bo.getItemId() != null, HmeMechanicalRule::getItemId, bo.getItemId());
        lqw.eq(bo.getDimension1Min() != null, HmeMechanicalRule::getDimension1Min, bo.getDimension1Min());
        lqw.eq(bo.getDimension1Max() != null, HmeMechanicalRule::getDimension1Max, bo.getDimension1Max());
        lqw.eq(bo.getDimension2Min() != null, HmeMechanicalRule::getDimension2Min, bo.getDimension2Min());
        lqw.eq(bo.getDimension2Max() != null, HmeMechanicalRule::getDimension2Max, bo.getDimension2Max());
        lqw.eq(bo.getDimension3Min() != null, HmeMechanicalRule::getDimension3Min, bo.getDimension3Min());
        lqw.eq(bo.getDimension3Max() != null, HmeMechanicalRule::getDimension3Max, bo.getDimension3Max());
        lqw.eq(bo.getMinValue() != null, HmeMechanicalRule::getMinValue, bo.getMinValue());
        lqw.eq(bo.getMaxValue() != null, HmeMechanicalRule::getMaxValue, bo.getMaxValue());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), HmeMechanicalRule::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增主数据 - 机械规则
     *
     * @param bo 主数据 - 机械规则
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(HmeMechanicalRuleBo bo) {
        HmeMechanicalRule add = MapstructUtils.convert(bo, HmeMechanicalRule.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改主数据 - 机械规则
     *
     * @param bo 主数据 - 机械规则
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(HmeMechanicalRuleBo bo) {
        HmeMechanicalRule update = MapstructUtils.convert(bo, HmeMechanicalRule.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HmeMechanicalRule entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除主数据 - 机械规则信息
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
