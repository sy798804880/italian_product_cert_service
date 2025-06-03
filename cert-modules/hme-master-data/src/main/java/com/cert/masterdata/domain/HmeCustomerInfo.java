package com.cert.masterdata.domain;

import com.cert.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 主数据 - 客户信息对象 hme_customer_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("hme_customer_info")
public class HmeCustomerInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 客户代码 (来自SAP)
     */
    private String customerCode;

    /**
     * 客户名称
     */
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
