package com.cert.masterdata.domain.bo;

import com.cert.masterdata.domain.HmeTestItem;
import com.cert.common.mybatis.core.domain.BaseEntity;
import com.cert.common.core.validate.AddGroup;
import com.cert.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 主数据 - 测试项目业务对象 hme_test_item
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = HmeTestItem.class, reverseConvertGenerate = false)
public class HmeTestItemBo extends BaseEntity {

    /**
     * 主键ID
     */
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 项目代码 (建议使用PDF中的系统代码, 如 UM22, MT001, 或 Cu, HB 等符号)
     */
    @NotBlank(message = "项目代码 (建议使用PDF中的系统代码, 如 UM22, MT001, 或 Cu, HB 等符号)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String itemCode;

    /**
     * 项目名称 (如 铜, 铅, 硬度, 抗拉强度, 对应PDF D_ELEMENT, D_MECTEST)
     */
    @NotBlank(message = "项目名称 (如 铜, 铅, 硬度, 抗拉强度, 对应PDF D_ELEMENT, D_MECTEST)不能为空", groups = { AddGroup.class, EditGroup.class })
    private String itemName;

    /**
     * 类型 (1=化学元素, 2=机械性能, 3=尺寸公差)
     */
    @NotBlank(message = "类型 (1=化学元素, 2=机械性能, 3=尺寸公差)不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 备注
     */
    private String remark;


}
