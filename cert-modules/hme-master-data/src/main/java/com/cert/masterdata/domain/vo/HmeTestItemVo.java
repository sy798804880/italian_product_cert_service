package com.cert.masterdata.domain.vo;

import com.cert.masterdata.domain.HmeTestItem;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cert.common.excel.annotation.ExcelDictFormat;
import com.cert.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 主数据 - 测试项目视图对象 hme_test_item
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = HmeTestItem.class)
public class HmeTestItemVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 项目代码 (建议使用PDF中的系统代码, 如 UM22, MT001, 或 Cu, HB 等符号)
     */
    @ExcelProperty(value = "项目代码 (建议使用PDF中的系统代码, 如 UM22, MT001, 或 Cu, HB 等符号)")
    private String itemCode;

    /**
     * 项目名称 (如 铜, 铅, 硬度, 抗拉强度, 对应PDF D_ELEMENT, D_MECTEST)
     */
    @ExcelProperty(value = "项目名称 (如 铜, 铅, 硬度, 抗拉强度, 对应PDF D_ELEMENT, D_MECTEST)")
    private String itemName;

    /**
     * 类型 (1=化学元素, 2=机械性能, 3=尺寸公差)
     */
    @ExcelProperty(value = "类型 (1=化学元素, 2=机械性能, 3=尺寸公差)")
    private String itemType;

    /**
     * 单位代码 (如 %, N/mm2, mm, 对应PDF D_UM)
     */
    @ExcelProperty(value = "单位代码 (如 %, N/mm2, mm, 对应PDF D_UM)")
    private String unitCode;

    /**
     * 项目描述
     */
    @ExcelProperty(value = "项目描述")
    private String description;

    /**
     * 显示排序号
     */
    @ExcelProperty(value = "显示排序号")
    private Long orderNum;

    /**
     * 状态 (0=正常 1=停用)
     */
    @ExcelProperty(value = "状态 (0=正常 1=停用)")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
