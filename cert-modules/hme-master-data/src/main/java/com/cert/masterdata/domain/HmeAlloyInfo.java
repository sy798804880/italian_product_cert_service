package com.cert.masterdata.domain;

import com.cert.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 主数据 - 合金信息对象 hme_alloy_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("hme_alloy_info")
public class HmeAlloyInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 内部合金代码 (对应PDF中 C_APLEGI 如 N17, 或类似 AlloyChemicalRules.CODICE)
     */
    private String internalAlloyCode;

    /**
     * 官方合金代码 (对应PDF中 C_APLEGA 如 CW617N, 或 DAPILEGI 如 CW617N-DW)
     */
    private String officialAlloyCode;

    /**
     * 铸造厂合金代码 (实验室设备原始代码, 对应PDF中 AlloyChemicalRules.CODICE 的 001 示例)
     */
    private String foundryAlloyCode;

    /**
     * 合金描述
     */
    private String description;

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
     * 备注
     */
    private String remark;


}
