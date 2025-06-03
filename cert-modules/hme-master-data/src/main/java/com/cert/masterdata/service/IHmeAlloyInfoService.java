package com.cert.masterdata.service;

import com.cert.masterdata.domain.vo.HmeAlloyInfoVo;
import com.cert.masterdata.domain.bo.HmeAlloyInfoBo;
import com.cert.common.mybatis.core.page.TableDataInfo;
import com.cert.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 主数据 - 合金信息Service接口
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
public interface IHmeAlloyInfoService {

    /**
     * 查询主数据 - 合金信息
     *
     * @param id 主键
     * @return 主数据 - 合金信息
     */
    HmeAlloyInfoVo queryById(Long id);

    /**
     * 分页查询主数据 - 合金信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 主数据 - 合金信息分页列表
     */
    TableDataInfo<HmeAlloyInfoVo> queryPageList(HmeAlloyInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的主数据 - 合金信息列表
     *
     * @param bo 查询条件
     * @return 主数据 - 合金信息列表
     */
    List<HmeAlloyInfoVo> queryList(HmeAlloyInfoBo bo);

    /**
     * 新增主数据 - 合金信息
     *
     * @param bo 主数据 - 合金信息
     * @return 是否新增成功
     */
    Boolean insertByBo(HmeAlloyInfoBo bo);

    /**
     * 修改主数据 - 合金信息
     *
     * @param bo 主数据 - 合金信息
     * @return 是否修改成功
     */
    Boolean updateByBo(HmeAlloyInfoBo bo);

    /**
     * 校验并批量删除主数据 - 合金信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
