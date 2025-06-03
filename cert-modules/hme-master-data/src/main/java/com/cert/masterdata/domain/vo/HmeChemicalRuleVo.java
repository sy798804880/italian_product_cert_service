package com.cert.masterdata.domain.vo;

import com.cert.masterdata.domain.HmeChemicalRule;
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
 * 主数据 - 化学规则视图对象 hme_chemical_rule
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = HmeChemicalRule.class)
public class HmeChemicalRuleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 规则代码 (对应PDF CHEMICAL_RULE, 如 CH001)
     */
    @ExcelProperty(value = "规则代码 (对应PDF CHEMICAL_RULE, 如 CH001)")
    private String ruleCode;

    /**
     * 合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA)
     */
    @ExcelProperty(value = "合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA)")
    private Long alloyId;

    /**
     * 标准ID (关联 hme_standard_info.id)
     */
    @ExcelProperty(value = "标准ID (关联 hme_standard_info.id)")
    private Long standardId;

    /**
     * 测试项目ID (化学元素, 关联 hme_test_item.id, 对应PDF C_ELEMENT)
     */
    @ExcelProperty(value = "测试项目ID (化学元素, 关联 hme_test_item.id, 对应PDF C_ELEMENT)")
    private Long itemId;

    /**
     * 最小值 (对应PDF MIN)
     */
    @ExcelProperty(value = "最小值 (对应PDF MIN)")
    private Long minValue;

    /**
     * 最大值 (对应PDF MAX)
     */
    @ExcelProperty(value = "最大值 (对应PDF MAX)")
    private Long maxValue;

    /**
     * 值类型 (1=浓度 Concentration, 2=杂质 Impurity, 3=差值 Per differenza/Remain)
     */
    @ExcelProperty(value = "值类型 (1=浓度 Concentration, 2=杂质 Impurity, 3=差值 Per differenza/Remain)")
    private String valueType;

    /**
     * 固定字符串 (如 Rem., 对应PDF STRING)
     */
    @ExcelProperty(value = "固定字符串 (如 Rem., 对应PDF STRING)")
    private String fixedString;

    /**
     * 检查限制标志 (1=是, 0=否, 对应PDF FL_CHECK_LIM)
     */
    @ExcelProperty(value = "检查限制标志 (1=是, 0=否, 对应PDF FL_CHECK_LIM)")
    private String checkLimitFlag;

    /**
     * 内部报告打印标志 (1=是, 0=否, 对应PDF FL_PRINT_DOC)
     */
    @ExcelProperty(value = "内部报告打印标志 (1=是, 0=否, 对应PDF FL_PRINT_DOC)")
    private String printReportFlag;

    /**
     * 证书打印标志 (1=是, 0=否, 对应PDF FL_PRINT_CERT)
     */
    @ExcelProperty(value = "证书打印标志 (1=是, 0=否, 对应PDF FL_PRINT_CERT)")
    private String printCertFlag;

    /**
     * 备注 (可存储PDF中的 D_CREAT, D_LASTMOD等信息)
     */
    @ExcelProperty(value = "备注 (可存储PDF中的 D_CREAT, D_LASTMOD等信息)")
    private String remark;


}
