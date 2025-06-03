package com.cert.masterdata.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.cert.common.idempotent.annotation.RepeatSubmit;
import com.cert.common.log.annotation.Log;
import com.cert.common.web.core.BaseController;
import com.cert.common.mybatis.core.page.PageQuery;
import com.cert.common.core.domain.R;
import com.cert.common.core.validate.AddGroup;
import com.cert.common.core.validate.EditGroup;
import com.cert.common.log.enums.BusinessType;
import com.cert.common.excel.utils.ExcelUtil;
import com.cert.masterdata.domain.vo.HmeCustomerInfoVo;
import com.cert.masterdata.domain.bo.HmeCustomerInfoBo;
import com.cert.masterdata.service.IHmeCustomerInfoService;
import com.cert.common.mybatis.core.page.TableDataInfo;

/**
 * 主数据 - 客户信息
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/masterData/customerInfo")
public class HmeCustomerInfoController extends BaseController {

    private final IHmeCustomerInfoService hmeCustomerInfoService;

    /**
     * 查询主数据 - 客户信息列表
     */
    @SaCheckPermission("masterData:customerInfo:list")
    @GetMapping("/list")
    public TableDataInfo<HmeCustomerInfoVo> list(HmeCustomerInfoBo bo, PageQuery pageQuery) {
        return hmeCustomerInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出主数据 - 客户信息列表
     */
    @SaCheckPermission("masterData:customerInfo:export")
    @Log(title = "主数据 - 客户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HmeCustomerInfoBo bo, HttpServletResponse response) {
        List<HmeCustomerInfoVo> list = hmeCustomerInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "主数据 - 客户信息", HmeCustomerInfoVo.class, response);
    }

    /**
     * 获取主数据 - 客户信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("masterData:customerInfo:query")
    @GetMapping("/{id}")
    public R<HmeCustomerInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(hmeCustomerInfoService.queryById(id));
    }

    /**
     * 新增主数据 - 客户信息
     */
    @SaCheckPermission("masterData:customerInfo:add")
    @Log(title = "主数据 - 客户信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HmeCustomerInfoBo bo) {
        return toAjax(hmeCustomerInfoService.insertByBo(bo));
    }

    /**
     * 修改主数据 - 客户信息
     */
    @SaCheckPermission("masterData:customerInfo:edit")
    @Log(title = "主数据 - 客户信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HmeCustomerInfoBo bo) {
        return toAjax(hmeCustomerInfoService.updateByBo(bo));
    }

    /**
     * 删除主数据 - 客户信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("masterData:customerInfo:remove")
    @Log(title = "主数据 - 客户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(hmeCustomerInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
