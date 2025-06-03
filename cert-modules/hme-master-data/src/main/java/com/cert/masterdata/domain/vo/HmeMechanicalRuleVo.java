package com.cert.masterdata.domain.vo;

import com.cert.masterdata.domain.HmeMechanicalRule;
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
 * 主数据 - 机械规则视图对象 hme_mechanical_rule
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = HmeMechanicalRule.class)
public class HmeMechanicalRuleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 规则代码 (对应PDF C_MECHANICAL_RULE, 如 ME0074)
     */
    @ExcelProperty(value = "规则代码 (对应PDF C_MECHANICAL_RULE, 如 ME0074)")
    private String ruleCode;

    /**
     * 子规则代码 (对应PDF C_SUB_MECH_RULE, 如 SM0379)
     */
    @ExcelProperty(value = "子规则代码 (对应PDF C_SUB_MECH_RULE, 如 SM0379)")
    private String subRuleCode;

    /**
     * 物料类型代码 (SAP中的, 对应PDF C_APTYPO1, 如 BO)
     */
    @ExcelProperty(value = "物料类型代码 (SAP中的, 对应PDF C_APTYPO1, 如 BO)")
    private String materialTypeCode;

    /**
     * 产品形态代码 (SAP中的, 对应PDF C_APFORM, 如 T01)
     */
    @ExcelProperty(value = "产品形态代码 (SAP中的, 对应PDF C_APFORM, 如 T01)")
    private String productShapeCode;

    /**
     * 通用规格代码 (SAP中的, 对应PDF C_APCNOR, 如 E01)
     */
    @ExcelProperty(value = "通用规格代码 (SAP中的, 对应PDF C_APCNOR, 如 E01)")
    private String generalSpecCode;

    /**
     * 内部回火状态代码 (SAP中的, 对应PDF C_APSEMA)
     */
    @ExcelProperty(value = "内部回火状态代码 (SAP中的, 对应PDF C_APSEMA)")
    private String internalTemperCode;

    /**
     * 尺寸规格代码 (SAP中的, 对应PDF C_APSPIN 如 S24)
     */
    @ExcelProperty(value = "尺寸规格代码 (SAP中的, 对应PDF C_APSPIN 如 S24)")
    private String dimensionSpecCode;

    /**
     * 合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA/C_APLEGI)
     */
    @ExcelProperty(value = "合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA/C_APLEGI)")
    private Long alloyId;

    /**
     * 标准ID (关联 hme_standard_info.id)
     */
    @ExcelProperty(value = "标准ID (关联 hme_standard_info.id)")
    private Long standardId;

    /**
     * 测试项目ID (机械性能/尺寸, 关联 hme_test_item.id, 对应PDF C_MECTEST)
     */
    @ExcelProperty(value = "测试项目ID (机械性能/尺寸, 关联 hme_test_item.id, 对应PDF C_MECTEST)")
    private Long itemId;

    /**
     * 适用尺寸1下限 (对应PDF V_MIN1)
     */
    @ExcelProperty(value = "适用尺寸1下限 (对应PDF V_MIN1)")
    private Long dimension1Min;

    /**
     * 适用尺寸1上限 (对应PDF V_MAX1)
     */
    @ExcelProperty(value = "适用尺寸1上限 (对应PDF V_MAX1)")
    private Long dimension1Max;

    /**
     * 适用尺寸2下限 (对应PDF V_MIN2)
     */
    @ExcelProperty(value = "适用尺寸2下限 (对应PDF V_MIN2)")
    private Long dimension2Min;

    /**
     * 适用尺寸2上限 (对应PDF V_MAX2)
     */
    @ExcelProperty(value = "适用尺寸2上限 (对应PDF V_MAX2)")
    private Long dimension2Max;

    /**
     * 适用尺寸3下限 (对应PDF V_MIN3)
     */
    @ExcelProperty(value = "适用尺寸3下限 (对应PDF V_MIN3)")
    private Long dimension3Min;

    /**
     * 适用尺寸3上限 (对应PDF V_MAX3)
     */
    @ExcelProperty(value = "适用尺寸3上限 (对应PDF V_MAX3)")
    private Long dimension3Max;

    /**
     * 最小值 (对应PDF V_LIMITMIN)
     */
    @ExcelProperty(value = "最小值 (对应PDF V_LIMITMIN)")
    private Long minValue;

    /**
     * 最大值 (对应PDF V_LIMITMAX)
     */
    @ExcelProperty(value = "最大值 (对应PDF V_LIMITMAX)")
    private Long maxValue;

    /**
     * 状态 (0=正常 1=停用)
     */
    @ExcelProperty(value = "状态 (0=正常 1=停用)")
    private String status;

    /**
     * 备注 (可存储PDF中的F_CHECKLIMIT, F_PRINTINTREPORT, F_PRINTRESULT等标志)
     */
    @ExcelProperty(value = "备注 (可存储PDF中的F_CHECKLIMIT, F_PRINTINTREPORT, F_PRINTRESULT等标志)")
    private String remark;


}
