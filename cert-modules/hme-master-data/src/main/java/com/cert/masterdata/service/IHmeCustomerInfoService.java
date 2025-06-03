package com.cert.masterdata.service;

import com.cert.masterdata.domain.vo.HmeCustomerInfoVo;
import com.cert.masterdata.domain.bo.HmeCustomerInfoBo;
import com.cert.common.mybatis.core.page.TableDataInfo;
import com.cert.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 主数据 - 客户信息Service接口
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
public interface IHmeCustomerInfoService {

    /**
     * 查询主数据 - 客户信息
     *
     * @param id 主键
     * @return 主数据 - 客户信息
     */
    HmeCustomerInfoVo queryById(Long id);

    /**
     * 分页查询主数据 - 客户信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 主数据 - 客户信息分页列表
     */
    TableDataInfo<HmeCustomerInfoVo> queryPageList(HmeCustomerInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的主数据 - 客户信息列表
     *
     * @param bo 查询条件
     * @return 主数据 - 客户信息列表
     */
    List<HmeCustomerInfoVo> queryList(HmeCustomerInfoBo bo);

    /**
     * 新增主数据 - 客户信息
     *
     * @param bo 主数据 - 客户信息
     * @return 是否新增成功
     */
    Boolean insertByBo(HmeCustomerInfoBo bo);

    /**
     * 修改主数据 - 客户信息
     *
     * @param bo 主数据 - 客户信息
     * @return 是否修改成功
     */
    Boolean updateByBo(HmeCustomerInfoBo bo);

    /**
     * 校验并批量删除主数据 - 客户信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
