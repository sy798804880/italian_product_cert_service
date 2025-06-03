package com.cert.masterdata.service;

import com.cert.masterdata.domain.vo.HmeArticleInfoVo;
import com.cert.masterdata.domain.bo.HmeArticleInfoBo;
import com.cert.common.mybatis.core.page.TableDataInfo;
import com.cert.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 主数据 - 物料信息Service接口
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
public interface IHmeArticleInfoService {

    /**
     * 查询主数据 - 物料信息
     *
     * @param id 主键
     * @return 主数据 - 物料信息
     */
    HmeArticleInfoVo queryById(Long id);

    /**
     * 分页查询主数据 - 物料信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 主数据 - 物料信息分页列表
     */
    TableDataInfo<HmeArticleInfoVo> queryPageList(HmeArticleInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的主数据 - 物料信息列表
     *
     * @param bo 查询条件
     * @return 主数据 - 物料信息列表
     */
    List<HmeArticleInfoVo> queryList(HmeArticleInfoBo bo);

    /**
     * 新增主数据 - 物料信息
     *
     * @param bo 主数据 - 物料信息
     * @return 是否新增成功
     */
    Boolean insertByBo(HmeArticleInfoBo bo);

    /**
     * 修改主数据 - 物料信息
     *
     * @param bo 主数据 - 物料信息
     * @return 是否修改成功
     */
    Boolean updateByBo(HmeArticleInfoBo bo);

    /**
     * 校验并批量删除主数据 - 物料信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    // ================== 意大利成品证书系统专用业务方法 ==================

    /**
     * 根据SAP物料代码查询物料信息
     *
     * @param articleCode SAP物料代码
     * @return 物料信息
     */
    HmeArticleInfoVo queryByArticleCode(String articleCode);

    /**
     * 查询物料的完整证书信息（包含合金、标准、测试规则等关联信息）
     *
     * @param id 物料ID
     * @return 物料完整信息
     */
    HmeArticleInfoVo queryArticleWithCertificateInfo(Long id);

    /**
     * 根据物料类型和产品形态查询适用的物料列表
     *
     * @param materialTypeCode 物料类型代码
     * @param productShapeCode 产品形态代码
     * @return 物料列表
     */
    List<HmeArticleInfoVo> queryByMaterialTypeAndShape(String materialTypeCode, String productShapeCode);

    /**
     * 批量验证物料数据完整性
     *
     * @param ids 物料ID列表
     * @return 验证结果
     */
    List<String> validateArticleDataIntegrity(List<Long> ids);

    /**
     * 同步SAP物料主数据
     *
     * @param articleCodes SAP物料代码列表
     * @return 同步结果
     */
    String syncArticleFromSap(List<String> articleCodes);

    /**
     * 查询物料的化学测试规则配置
     *
     * @param articleId 物料ID
     * @param alloyId   合金ID
     * @return 化学测试规则列表
     */
    List<Object> getArticleChemicalRules(Long articleId, Long alloyId);

    /**
     * 查询物料的机械测试规则配置
     *
     * @param articleId 物料ID
     * @param alloyId   合金ID
     * @return 机械测试规则列表
     */
    List<Object> getArticleMechanicalRules(Long articleId, Long alloyId);

    /**
     * 复制物料配置（包括关联的标准和规则）
     *
     * @param sourceId            源物料ID
     * @param targetArticleCode   目标物料代码
     * @param targetDescription   目标物料描述
     * @return 新创建的物料ID
     */
    Long copyArticleConfiguration(Long sourceId, String targetArticleCode, String targetDescription);
}
