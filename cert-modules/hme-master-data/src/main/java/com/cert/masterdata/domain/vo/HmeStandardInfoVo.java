package com.cert.masterdata.domain.vo;

import com.cert.masterdata.domain.HmeStandardInfo;
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
 * 主数据 - 标准信息视图对象 hme_standard_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = HmeStandardInfo.class)
public class HmeStandardInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 标准编号 (如 EN 12165, ASTM B124M, 或PDF中提到的 M.M. Salute 6.4.2004 n.174)
     */
    @ExcelProperty(value = "标准编号 (如 EN 12165, ASTM B124M, 或PDF中提到的 M.M. Salute 6.4.2004 n.174)")
    private String standardCode;

    /**
     * 标准类型 (1=化学, 2=机械, 3=尺寸)
     */
    @ExcelProperty(value = "标准类型 (1=化学, 2=机械, 3=尺寸)")
    private String standardType;

    /**
     * 标准描述
     */
    @ExcelProperty(value = "标准描述")
    private String description;

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
