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
import com.cert.masterdata.domain.vo.HmeTestItemVo;
import com.cert.masterdata.domain.bo.HmeTestItemBo;
import com.cert.masterdata.service.IHmeTestItemService;
import com.cert.common.mybatis.core.page.TableDataInfo;

/**
 * 主数据 - 测试项目
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/masterData/testItem")
public class HmeTestItemController extends BaseController {

    private final IHmeTestItemService hmeTestItemService;

    /**
     * 查询主数据 - 测试项目列表
     */
    @SaCheckPermission("masterData:testItem:list")
    @GetMapping("/list")
    public TableDataInfo<HmeTestItemVo> list(HmeTestItemBo bo, PageQuery pageQuery) {
        return hmeTestItemService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出主数据 - 测试项目列表
     */
    @SaCheckPermission("masterData:testItem:export")
    @Log(title = "主数据 - 测试项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HmeTestItemBo bo, HttpServletResponse response) {
        List<HmeTestItemVo> list = hmeTestItemService.queryList(bo);
        ExcelUtil.exportExcel(list, "主数据 - 测试项目", HmeTestItemVo.class, response);
    }

    /**
     * 获取主数据 - 测试项目详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("masterData:testItem:query")
    @GetMapping("/{id}")
    public R<HmeTestItemVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(hmeTestItemService.queryById(id));
    }

    /**
     * 新增主数据 - 测试项目
     */
    @SaCheckPermission("masterData:testItem:add")
    @Log(title = "主数据 - 测试项目", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HmeTestItemBo bo) {
        return toAjax(hmeTestItemService.insertByBo(bo));
    }

    /**
     * 修改主数据 - 测试项目
     */
    @SaCheckPermission("masterData:testItem:edit")
    @Log(title = "主数据 - 测试项目", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HmeTestItemBo bo) {
        return toAjax(hmeTestItemService.updateByBo(bo));
    }

    /**
     * 删除主数据 - 测试项目
     *
     * @param ids 主键串
     */
    @SaCheckPermission("masterData:testItem:remove")
    @Log(title = "主数据 - 测试项目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(hmeTestItemService.deleteWithValidByIds(List.of(ids), true));
    }
}
