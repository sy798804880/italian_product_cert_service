package com.cert.masterdata.domain.vo;

import com.cert.masterdata.domain.HmeAlloyInfo;
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
 * 主数据 - 合金信息视图对象 hme_alloy_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = HmeAlloyInfo.class)
public class HmeAlloyInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 内部合金代码 (对应PDF中 C_APLEGI 如 N17, 或类似 AlloyChemicalRules.CODICE)
     */
    @ExcelProperty(value = "内部合金代码 (对应PDF中 C_APLEGI 如 N17, 或类似 AlloyChemicalRules.CODICE)")
    private String internalAlloyCode;

    /**
     * 官方合金代码 (对应PDF中 C_APLEGA 如 CW617N, 或 DAPILEGI 如 CW617N-DW)
     */
    @ExcelProperty(value = "官方合金代码 (对应PDF中 C_APLEGA 如 CW617N, 或 DAPILEGI 如 CW617N-DW)")
    private String officialAlloyCode;

    /**
     * 铸造厂合金代码 (实验室设备原始代码, 对应PDF中 AlloyChemicalRules.CODICE 的 001 示例)
     */
    @ExcelProperty(value = "铸造厂合金代码 (实验室设备原始代码, 对应PDF中 AlloyChemicalRules.CODICE 的 001 示例)")
    private String foundryAlloyCode;

    /**
     * 合金描述
     */
    @ExcelProperty(value = "合金描述")
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
