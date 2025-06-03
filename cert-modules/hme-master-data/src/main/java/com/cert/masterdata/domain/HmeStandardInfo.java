package com.cert.masterdata.domain;

import com.cert.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 主数据 - 标准信息对象 hme_standard_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("hme_standard_info")
public class HmeStandardInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 标准编号 (如 EN 12165, ASTM B124M, 或PDF中提到的 M.M. Salute 6.4.2004 n.174)
     */
    private String standardCode;

    /**
     * 标准类型 (1=化学, 2=机械, 3=尺寸)
     */
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
