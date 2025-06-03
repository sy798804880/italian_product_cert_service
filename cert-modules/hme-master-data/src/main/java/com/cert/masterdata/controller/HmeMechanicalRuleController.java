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
import com.cert.masterdata.domain.vo.HmeMechanicalRuleVo;
import com.cert.masterdata.domain.bo.HmeMechanicalRuleBo;
import com.cert.masterdata.service.IHmeMechanicalRuleService;
import com.cert.common.mybatis.core.page.TableDataInfo;

/**
 * 主数据 - 机械规则
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/masterData/mechanicalRule")
public class HmeMechanicalRuleController extends BaseController {

    private final IHmeMechanicalRuleService hmeMechanicalRuleService;

    /**
     * 查询主数据 - 机械规则列表
     */
    @SaCheckPermission("masterData:mechanicalRule:list")
    @GetMapping("/list")
    public TableDataInfo<HmeMechanicalRuleVo> list(HmeMechanicalRuleBo bo, PageQuery pageQuery) {
        return hmeMechanicalRuleService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出主数据 - 机械规则列表
     */
    @SaCheckPermission("masterData:mechanicalRule:export")
    @Log(title = "主数据 - 机械规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HmeMechanicalRuleBo bo, HttpServletResponse response) {
        List<HmeMechanicalRuleVo> list = hmeMechanicalRuleService.queryList(bo);
        ExcelUtil.exportExcel(list, "主数据 - 机械规则", HmeMechanicalRuleVo.class, response);
    }

    /**
     * 获取主数据 - 机械规则详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("masterData:mechanicalRule:query")
    @GetMapping("/{id}")
    public R<HmeMechanicalRuleVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(hmeMechanicalRuleService.queryById(id));
    }

    /**
     * 新增主数据 - 机械规则
     */
    @SaCheckPermission("masterData:mechanicalRule:add")
    @Log(title = "主数据 - 机械规则", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HmeMechanicalRuleBo bo) {
        return toAjax(hmeMechanicalRuleService.insertByBo(bo));
    }

    /**
     * 修改主数据 - 机械规则
     */
    @SaCheckPermission("masterData:mechanicalRule:edit")
    @Log(title = "主数据 - 机械规则", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HmeMechanicalRuleBo bo) {
        return toAjax(hmeMechanicalRuleService.updateByBo(bo));
    }

    /**
     * 删除主数据 - 机械规则
     *
     * @param ids 主键串
     */
    @SaCheckPermission("masterData:mechanicalRule:remove")
    @Log(title = "主数据 - 机械规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(hmeMechanicalRuleService.deleteWithValidByIds(List.of(ids), true));
    }
}
