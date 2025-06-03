package com.cert.masterdata.domain.bo;

import com.cert.masterdata.domain.HmeCustomerInfo;
import com.cert.common.mybatis.core.domain.BaseEntity;
import com.cert.common.core.validate.AddGroup;
import com.cert.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 主数据 - 客户信息业务对象 hme_customer_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = HmeCustomerInfo.class, reverseConvertGenerate = false)
public class HmeCustomerInfoBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 客户代码 (来自SAP)
     */
    @NotBlank(message = "客户代码 (来自SAP)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerCode;

    /**
     * 客户名称
     */
    @NotBlank(message = "客户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String customerName;

    /**
     * 证书接收邮箱
     */
    private String emailAddress;

    /**
     * 状态 (0=正常 1=停用)
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
