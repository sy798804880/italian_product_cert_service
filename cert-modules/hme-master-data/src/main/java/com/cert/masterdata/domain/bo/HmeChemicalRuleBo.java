package com.cert.masterdata.domain.bo;

import com.cert.masterdata.domain.HmeChemicalRule;
import com.cert.common.mybatis.core.domain.BaseEntity;
import com.cert.common.core.validate.AddGroup;
import com.cert.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 主数据 - 化学规则业务对象 hme_chemical_rule
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = HmeChemicalRule.class, reverseConvertGenerate = false)
public class HmeChemicalRuleBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 规则代码 (对应PDF CHEMICAL_RULE, 如 CH001)
     */
    @NotBlank(message = "规则代码 (对应PDF CHEMICAL_RULE, 如 CH001)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ruleCode;

    /**
     * 合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA)
     */
    @NotNull(message = "合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA)不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long alloyId;

    /**
     * 标准ID (关联 hme_standard_info.id)
     */
    @NotNull(message = "标准ID (关联 hme_standard_info.id)不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long standardId;

    /**
     * 测试项目ID (化学元素, 关联 hme_test_item.id, 对应PDF C_ELEMENT)
     */
    @NotNull(message = "测试项目ID (化学元素, 关联 hme_test_item.id, 对应PDF C_ELEMENT)不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long itemId;

    /**
     * 最小值 (对应PDF MIN)
     */
    private Long minValue;

    /**
     * 最大值 (对应PDF MAX)
     */
    private Long maxValue;

    /**
     * 值类型 (1=浓度 Concentration, 2=杂质 Impurity, 3=差值 Per differenza/Remain)
     */
    private String valueType;

    /**
     * 固定字符串 (如 Rem., 对应PDF STRING)
     */
    private String fixedString;

    /**
     * 检查限制标志 (1=是, 0=否, 对应PDF FL_CHECK_LIM)
     */
    private String checkLimitFlag;

    /**
     * 内部报告打印标志 (1=是, 0=否, 对应PDF FL_PRINT_DOC)
     */
    private String printReportFlag;

    /**
     * 证书打印标志 (1=是, 0=否, 对应PDF FL_PRINT_CERT)
     */
    private String printCertFlag;

    /**
     * 备注 (可存储PDF中的 D_CREAT, D_LASTMOD等信息)
     */
    private String remark;


}
