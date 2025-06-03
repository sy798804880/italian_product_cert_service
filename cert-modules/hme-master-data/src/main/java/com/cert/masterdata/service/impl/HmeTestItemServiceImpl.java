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
import com.cert.masterdata.domain.bo.HmeTestItemBo;
import com.cert.masterdata.domain.vo.HmeTestItemVo;
import com.cert.masterdata.domain.HmeTestItem;
import com.cert.masterdata.mapper.HmeTestItemMapper;
import com.cert.masterdata.service.IHmeTestItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 主数据 - 测试项目Service业务层处理
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@RequiredArgsConstructor
@Service
public class HmeTestItemServiceImpl implements IHmeTestItemService {

    private final HmeTestItemMapper baseMapper;

    /**
     * 查询主数据 - 测试项目
     *
     * @param id 主键
     * @return 主数据 - 测试项目
     */
    @Override
    public HmeTestItemVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询主数据 - 测试项目列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 主数据 - 测试项目分页列表
     */
    @Override
    public TableDataInfo<HmeTestItemVo> queryPageList(HmeTestItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<HmeTestItem> lqw = buildQueryWrapper(bo);
        Page<HmeTestItemVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的主数据 - 测试项目列表
     *
     * @param bo 查询条件
     * @return 主数据 - 测试项目列表
     */
    @Override
    public List<HmeTestItemVo> queryList(HmeTestItemBo bo) {
        LambdaQueryWrapper<HmeTestItem> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<HmeTestItem> buildQueryWrapper(HmeTestItemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HmeTestItem> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(HmeTestItem::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getItemCode()), HmeTestItem::getItemCode, bo.getItemCode());
        lqw.like(StringUtils.isNotBlank(bo.getItemName()), HmeTestItem::getItemName, bo.getItemName());
        lqw.eq(StringUtils.isNotBlank(bo.getItemType()), HmeTestItem::getItemType, bo.getItemType());
        lqw.eq(StringUtils.isNotBlank(bo.getUnitCode()), HmeTestItem::getUnitCode, bo.getUnitCode());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), HmeTestItem::getDescription, bo.getDescription());
        lqw.eq(bo.getOrderNum() != null, HmeTestItem::getOrderNum, bo.getOrderNum());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), HmeTestItem::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增主数据 - 测试项目
     *
     * @param bo 主数据 - 测试项目
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(HmeTestItemBo bo) {
        HmeTestItem add = MapstructUtils.convert(bo, HmeTestItem.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改主数据 - 测试项目
     *
     * @param bo 主数据 - 测试项目
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(HmeTestItemBo bo) {
        HmeTestItem update = MapstructUtils.convert(bo, HmeTestItem.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HmeTestItem entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除主数据 - 测试项目信息
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
