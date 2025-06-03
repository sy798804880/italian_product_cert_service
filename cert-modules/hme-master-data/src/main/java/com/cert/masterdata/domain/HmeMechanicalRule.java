package com.cert.masterdata.domain;

import com.cert.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 主数据 - 机械规则对象 hme_mechanical_rule
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("hme_mechanical_rule")
public class HmeMechanicalRule extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 规则代码 (对应PDF C_MECHANICAL_RULE, 如 ME0074)
     */
    private String ruleCode;

    /**
     * 子规则代码 (对应PDF C_SUB_MECH_RULE, 如 SM0379)
     */
    private String subRuleCode;

    /**
     * 物料类型代码 (SAP中的, 对应PDF C_APTYPO1, 如 BO)
     */
    private String materialTypeCode;

    /**
     * 产品形态代码 (SAP中的, 对应PDF C_APFORM, 如 T01)
     */
    private String productShapeCode;

    /**
     * 通用规格代码 (SAP中的, 对应PDF C_APCNOR, 如 E01)
     */
    private String generalSpecCode;

    /**
     * 内部回火状态代码 (SAP中的, 对应PDF C_APSEMA)
     */
    private String internalTemperCode;

    /**
     * 尺寸规格代码 (SAP中的, 对应PDF C_APSPIN 如 S24)
     */
    private String dimensionSpecCode;

    /**
     * 合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA/C_APLEGI)
     */
    private Long alloyId;

    /**
     * 标准ID (关联 hme_standard_info.id)
     */
    private Long standardId;

    /**
     * 测试项目ID (机械性能/尺寸, 关联 hme_test_item.id, 对应PDF C_MECTEST)
     */
    private Long itemId;

    /**
     * 适用尺寸1下限 (对应PDF V_MIN1)
     */
    private Long dimension1Min;

    /**
     * 适用尺寸1上限 (对应PDF V_MAX1)
     */
    private Long dimension1Max;

    /**
     * 适用尺寸2下限 (对应PDF V_MIN2)
     */
    private Long dimension2Min;

    /**
     * 适用尺寸2上限 (对应PDF V_MAX2)
     */
    private Long dimension2Max;

    /**
     * 适用尺寸3下限 (对应PDF V_MIN3)
     */
    private Long dimension3Min;

    /**
     * 适用尺寸3上限 (对应PDF V_MAX3)
     */
    private Long dimension3Max;

    /**
     * 最小值 (对应PDF V_LIMITMIN)
     */
    private Long minValue;

    /**
     * 最大值 (对应PDF V_LIMITMAX)
     */
    private Long maxValue;

    /**
     * 状态 (0=正常 1=停用)
     */
    private String status;

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
     * 备注 (可存储PDF中的F_CHECKLIMIT, F_PRINTINTREPORT, F_PRINTRESULT等标志)
     */
    private String remark;


}
