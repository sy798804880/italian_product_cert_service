package com.cert.masterdata.service;

import com.cert.masterdata.domain.vo.HmeTestItemVo;
import com.cert.masterdata.domain.bo.HmeTestItemBo;
import com.cert.common.mybatis.core.page.TableDataInfo;
import com.cert.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 主数据 - 测试项目Service接口
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
public interface IHmeTestItemService {

    /**
     * 查询主数据 - 测试项目
     *
     * @param id 主键
     * @return 主数据 - 测试项目
     */
    HmeTestItemVo queryById(Long id);

    /**
     * 分页查询主数据 - 测试项目列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 主数据 - 测试项目分页列表
     */
    TableDataInfo<HmeTestItemVo> queryPageList(HmeTestItemBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的主数据 - 测试项目列表
     *
     * @param bo 查询条件
     * @return 主数据 - 测试项目列表
     */
    List<HmeTestItemVo> queryList(HmeTestItemBo bo);

    /**
     * 新增主数据 - 测试项目
     *
     * @param bo 主数据 - 测试项目
     * @return 是否新增成功
     */
    Boolean insertByBo(HmeTestItemBo bo);

    /**
     * 修改主数据 - 测试项目
     *
     * @param bo 主数据 - 测试项目
     * @return 是否修改成功
     */
    Boolean updateByBo(HmeTestItemBo bo);

    /**
     * 校验并批量删除主数据 - 测试项目信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
