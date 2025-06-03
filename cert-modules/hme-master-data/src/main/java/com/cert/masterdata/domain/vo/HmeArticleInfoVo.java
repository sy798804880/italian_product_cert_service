package com.cert.masterdata.domain.vo;

import com.cert.masterdata.domain.HmeArticleInfo;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cert.common.excel.annotation.ExcelDictFormat;
import com.cert.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 主数据 - 物料信息视图对象 hme_article_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = HmeArticleInfo.class)
public class HmeArticleInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 物料代码 (SAP, 对应PDF Material)
     */
    @ExcelProperty(value = "物料代码 (SAP, 对应PDF Material)")
    private String articleCode;

    /**
     * 物料描述 (对应PDF Overall Description)
     */
    @ExcelProperty(value = "物料描述 (对应PDF Overall Description)")
    private String articleDescription;

    /**
     * 物料类型代码 (如 BO-Barre in verghe, 对应PDF Product Typology Code)
     */
    @ExcelProperty(value = "物料类型代码 (如 BO-Barre in verghe, 对应PDF Product Typology Code)")
    private String materialTypeCode;

    /**
     * 产品形态代码 (如 T01-RND, 对应PDF Product Shape Code)
     */
    @ExcelProperty(value = "产品形态代码 (如 T01-RND, 对应PDF Product Shape Code)")
    private String productShapeCode;

    /**
     * 项目形态代码 (对应PDF Project Shape Code)
     */
    @ExcelProperty(value = "项目形态代码 (对应PDF Project Shape Code)")
    private String projectShapeCode;

    /**
     * 通用规格代码 (如 E01-Estruso, 对应PDF General Specification Code)
     */
    @ExcelProperty(value = "通用规格代码 (如 E01-Estruso, 对应PDF General Specification Code)")
    private String generalSpecCode;

    /**
     * 尺寸规格代码-键 (对应PDF Dimension Specification Code e.g. S24)
     */
    @ExcelProperty(value = "尺寸规格代码-键 (对应PDF Dimension Specification Code e.g. S24)")
    private String dimensionSpecCodeKey;

    /**
     * 尺寸规格代码-值 (对应PDF Dimensional Specification Code e.g. 121856)
     */
    @ExcelProperty(value = "尺寸规格代码-值 (对应PDF Dimensional Specification Code e.g. 121856)")
    private String dimensionSpecCodeValue;

    /**
     * 内部回火状态代码 (对应PDF Internal Temper Code e.g. D41)
     */
    @ExcelProperty(value = "内部回火状态代码 (对应PDF Internal Temper Code e.g. D41)")
    private String internalTemperCode;

    /**
     * 内部半成品形态代码 (S.F. Interno)
     */
    @ExcelProperty(value = "内部半成品形态代码 (S.F. Interno)")
    private String internalSFCode;

    /**
     * 物料半成品形态代码 (S.F. Materiale)
     */
    @ExcelProperty(value = "物料半成品形态代码 (S.F. Materiale)")
    private String materialSFCode;

    /**
     * 默认合金ID (关联 hme_alloy_info.id, 对应PDF Official Alloy Code/Internal Alloy Code)
     */
    @ExcelProperty(value = "默认合金ID (关联 hme_alloy_info.id, 对应PDF Official Alloy Code/Internal Alloy Code)")
    private Long defaultAlloyId;

    /**
     * 默认化学标准ID (关联 hme_standard_info.id)
     */
    @ExcelProperty(value = "默认化学标准ID (关联 hme_standard_info.id)")
    private Long defaultChemStdId;

    /**
     * 默认机械标准ID (关联 hme_standard_info.id)
     */
    @ExcelProperty(value = "默认机械标准ID (关联 hme_standard_info.id)")
    private Long defaultMechStdId;

    /**
     * 默认尺寸标准ID (关联 hme_standard_info.id)
     */
    @ExcelProperty(value = "默认尺寸标准ID (关联 hme_standard_info.id)")
    private Long defaultDimStdId;

    /**
     * 状态 (0=正常 1=停用)
     */
    @ExcelProperty(value = "状态 (0=正常 1=停用)")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
