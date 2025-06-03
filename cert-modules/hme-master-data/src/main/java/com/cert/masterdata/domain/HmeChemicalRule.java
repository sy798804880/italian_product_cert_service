package com.cert.masterdata.domain;

import com.cert.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 主数据 - 化学规则对象 hme_chemical_rule
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("hme_chemical_rule")
public class HmeChemicalRule extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 规则代码 (对应PDF CHEMICAL_RULE, 如 CH001)
     */
    private String ruleCode;

    /**
     * 合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA)
     */
    private Long alloyId;

    /**
     * 标准ID (关联 hme_standard_info.id)
     */
    private Long standardId;

    /**
     * 测试项目ID (化学元素, 关联 hme_test_item.id, 对应PDF C_ELEMENT)
     */
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
     * 版本号
     */
    @Version
    private Long version;

    /**
     * 删除标志 (0=存在 1=删除)
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注 (可存储PDF中的 D_CREAT, D_LASTMOD等信息)
     */
    private String remark;


}
