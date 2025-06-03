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
import com.cert.masterdata.domain.vo.HmeStandardInfoVo;
import com.cert.masterdata.domain.bo.HmeStandardInfoBo;
import com.cert.masterdata.service.IHmeStandardInfoService;
import com.cert.common.mybatis.core.page.TableDataInfo;

/**
 * 主数据 - 标准信息
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/masterData/standardInfo")
public class HmeStandardInfoController extends BaseController {

    private final IHmeStandardInfoService hmeStandardInfoService;

    /**
     * 查询主数据 - 标准信息列表
     */
    @SaCheckPermission("masterData:standardInfo:list")
    @GetMapping("/list")
    public TableDataInfo<HmeStandardInfoVo> list(HmeStandardInfoBo bo, PageQuery pageQuery) {
        return hmeStandardInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出主数据 - 标准信息列表
     */
    @SaCheckPermission("masterData:standardInfo:export")
    @Log(title = "主数据 - 标准信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HmeStandardInfoBo bo, HttpServletResponse response) {
        List<HmeStandardInfoVo> list = hmeStandardInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "主数据 - 标准信息", HmeStandardInfoVo.class, response);
    }

    /**
     * 获取主数据 - 标准信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("masterData:standardInfo:query")
    @GetMapping("/{id}")
    public R<HmeStandardInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(hmeStandardInfoService.queryById(id));
    }

    /**
     * 新增主数据 - 标准信息
     */
    @SaCheckPermission("masterData:standardInfo:add")
    @Log(title = "主数据 - 标准信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HmeStandardInfoBo bo) {
        return toAjax(hmeStandardInfoService.insertByBo(bo));
    }

    /**
     * 修改主数据 - 标准信息
     */
    @SaCheckPermission("masterData:standardInfo:edit")
    @Log(title = "主数据 - 标准信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HmeStandardInfoBo bo) {
        return toAjax(hmeStandardInfoService.updateByBo(bo));
    }

    /**
     * 删除主数据 - 标准信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("masterData:standardInfo:remove")
    @Log(title = "主数据 - 标准信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(hmeStandardInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
