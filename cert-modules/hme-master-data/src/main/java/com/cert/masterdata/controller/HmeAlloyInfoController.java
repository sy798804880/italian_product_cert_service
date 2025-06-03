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
import com.cert.masterdata.domain.vo.HmeAlloyInfoVo;
import com.cert.masterdata.domain.bo.HmeAlloyInfoBo;
import com.cert.masterdata.service.IHmeAlloyInfoService;
import com.cert.common.mybatis.core.page.TableDataInfo;

/**
 * 主数据 - 合金信息
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/masterData/alloyInfo")
public class HmeAlloyInfoController extends BaseController {

    private final IHmeAlloyInfoService hmeAlloyInfoService;

    /**
     * 查询主数据 - 合金信息列表
     */
    @SaCheckPermission("masterData:alloyInfo:list")
    @GetMapping("/list")
    public TableDataInfo<HmeAlloyInfoVo> list(HmeAlloyInfoBo bo, PageQuery pageQuery) {
        return hmeAlloyInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出主数据 - 合金信息列表
     */
    @SaCheckPermission("masterData:alloyInfo:export")
    @Log(title = "主数据 - 合金信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HmeAlloyInfoBo bo, HttpServletResponse response) {
        List<HmeAlloyInfoVo> list = hmeAlloyInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "主数据 - 合金信息", HmeAlloyInfoVo.class, response);
    }

    /**
     * 获取主数据 - 合金信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("masterData:alloyInfo:query")
    @GetMapping("/{id}")
    public R<HmeAlloyInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(hmeAlloyInfoService.queryById(id));
    }

    /**
     * 新增主数据 - 合金信息
     */
    @SaCheckPermission("masterData:alloyInfo:add")
    @Log(title = "主数据 - 合金信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HmeAlloyInfoBo bo) {
        return toAjax(hmeAlloyInfoService.insertByBo(bo));
    }

    /**
     * 修改主数据 - 合金信息
     */
    @SaCheckPermission("masterData:alloyInfo:edit")
    @Log(title = "主数据 - 合金信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HmeAlloyInfoBo bo) {
        return toAjax(hmeAlloyInfoService.updateByBo(bo));
    }

    /**
     * 删除主数据 - 合金信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("masterData:alloyInfo:remove")
    @Log(title = "主数据 - 合金信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(hmeAlloyInfoService.deleteWithValidByIds(List.of(ids), true));
    }
}
