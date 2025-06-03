package com.cert.masterdata.domain.vo;

import com.cert.masterdata.domain.HmeCustomerInfo;
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
 * 主数据 - 客户信息视图对象 hme_customer_info
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = HmeCustomerInfo.class)
public class HmeCustomerInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    private Long id;

    /**
     * 客户代码 (来自SAP)
     */
    @ExcelProperty(value = "客户代码 (来自SAP)")
    private String customerCode;

    /**
     * 客户名称
     */
    @ExcelProperty(value = "客户名称")
    private String customerName;

    /**
     * 证书接收邮箱
     */
    @ExcelProperty(value = "证书接收邮箱")
    private String emailAddress;

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
