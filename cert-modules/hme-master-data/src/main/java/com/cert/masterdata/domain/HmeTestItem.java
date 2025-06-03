package com.cert.masterdata.domain;

import com.cert.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 主数据 - 测试项目对象 hme_test_item
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("hme_test_item")
public class HmeTestItem extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 项目代码 (建议使用PDF中的系统代码, 如 UM22, MT001, 或 Cu, HB 等符号)
     */
    private String itemCode;

    /**
     * 项目名称 (如 铜, 铅, 硬度, 抗拉强度, 对应PDF D_ELEMENT, D_MECTEST)
     */
    private String itemName;

    /**
     * 类型 (1=化学元素, 2=机械性能, 3=尺寸公差)
     */
    private String itemType;

    /**
     * 单位代码 (如 %, N/mm2, mm, 对应PDF D_UM)
     */
    private String unitCode;

    /**
     * 项目描述
     */
    private String description;

    /**
     * 显示排序号
     */
    private Long orderNum;

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
