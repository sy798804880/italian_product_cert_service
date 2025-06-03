package com.cert.masterdata.domain.bo;

import com.cert.masterdata.domain.HmeStandardInfo;
import com.cert.common.mybatis.core.domain.BaseEntity;
import com.cert.common.core.validate.AddGroup;
import com.cert.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 主数据 - 标准信息业务对象 hme_standard_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = HmeStandardInfo.class, reverseConvertGenerate = false)
public class HmeStandardInfoBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 标准编号 (如 EN 12165, ASTM B124M, 或PDF中提到的 M.M. Salute 6.4.2004 n.174)
     */
    @NotBlank(message = "标准编号 (如 EN 12165, ASTM B124M, 或PDF中提到的 M.M. Salute 6.4.2004 n.174)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String standardCode;

    /**
     * 标准类型 (1=化学, 2=机械, 3=尺寸)
     */
    @NotBlank(message = "标准类型 (1=化学, 2=机械, 3=尺寸)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String standardType;

    /**
     * 标准描述
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
