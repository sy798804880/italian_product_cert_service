package com.cert.masterdata.domain.bo;

import com.cert.masterdata.domain.HmeAlloyInfo;
import com.cert.common.mybatis.core.domain.BaseEntity;
import com.cert.common.core.validate.AddGroup;
import com.cert.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 主数据 - 合金信息业务对象 hme_alloy_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = HmeAlloyInfo.class, reverseConvertGenerate = false)
public class HmeAlloyInfoBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 内部合金代码 (对应PDF中 C_APLEGI 如 N17, 或类似 AlloyChemicalRules.CODICE)
     */
    @NotBlank(message = "内部合金代码 (对应PDF中 C_APLEGI 如 N17, 或类似 AlloyChemicalRules.CODICE)不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}
