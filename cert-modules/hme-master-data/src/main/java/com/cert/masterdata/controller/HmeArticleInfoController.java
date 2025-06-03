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
import com.cert.masterdata.domain.vo.HmeArticleInfoVo;
import com.cert.masterdata.domain.bo.HmeArticleInfoBo;
import com.cert.masterdata.service.IHmeArticleInfoService;
import com.cert.common.mybatis.core.page.TableDataInfo;

/**
 * 主数据 - 物料信息
 * 意大利成品证书系统物料主数据管理
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/masterData/articleInfo")
public class HmeArticleInfoController extends BaseController {

    private final IHmeArticleInfoService hmeArticleInfoService;

    /**
     * 查询主数据 - 物料信息列表
     */
    @SaCheckPermission("masterData:articleInfo:list")
    @GetMapping("/list")
    public TableDataInfo<HmeArticleInfoVo> list(HmeArticleInfoBo bo, PageQuery pageQuery) {
        return hmeArticleInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出主数据 - 物料信息列表
     */
    @SaCheckPermission("masterData:articleInfo:export")
    @Log(title = "主数据 - 物料信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HmeArticleInfoBo bo, HttpServletResponse response) {
        List<HmeArticleInfoVo> list = hmeArticleInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "主数据 - 物料信息", HmeArticleInfoVo.class, response);
    }

    /**
     * 获取主数据 - 物料信息详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("masterData:articleInfo:query")
    @GetMapping("/{id}")
    public R<HmeArticleInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(hmeArticleInfoService.queryById(id));
    }

    /**
     * 新增主数据 - 物料信息
     */
    @SaCheckPermission("masterData:articleInfo:add")
    @Log(title = "主数据 - 物料信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody HmeArticleInfoBo bo) {
        return toAjax(hmeArticleInfoService.insertByBo(bo));
    }

    /**
     * 修改主数据 - 物料信息
     */
    @SaCheckPermission("masterData:articleInfo:edit")
    @Log(title = "主数据 - 物料信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody HmeArticleInfoBo bo) {
        return toAjax(hmeArticleInfoService.updateByBo(bo));
    }

    /**
     * 删除主数据 - 物料信息
     *
     * @param ids 主键串
     */
    @SaCheckPermission("masterData:articleInfo:remove")
    @Log(title = "主数据 - 物料信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(hmeArticleInfoService.deleteWithValidByIds(List.of(ids), true));
    }

    // ================== 意大利成品证书系统专用业务接口 ==================

    /**
     * 根据SAP物料代码查询物料信息（用于证书生成时的物料信息获取）
     *
     * @param articleCode SAP物料代码
     * @return 物料信息
     */
    @SaCheckPermission("masterData:articleInfo:query")
    @GetMapping("/getBySapCode/{articleCode}")
    public R<HmeArticleInfoVo> getByArticleCode(@NotBlank(message = "物料代码不能为空")
                                               @PathVariable String articleCode) {
        return R.ok(hmeArticleInfoService.queryByArticleCode(articleCode));
    }

    /**
     * 获取物料的完整证书信息（包含合金、标准、测试规则等关联信息）
     * 用于证书生成时获取物料的所有相关配置
     *
     * @param id 物料ID
     * @return 物料完整信息
     */
    @SaCheckPermission("masterData:articleInfo:query")
    @GetMapping("/getCertificateInfo/{id}")
    public R<HmeArticleInfoVo> getCertificateInfo(@NotNull(message = "物料ID不能为空")
                                                 @PathVariable Long id) {
        return R.ok(hmeArticleInfoService.queryArticleWithCertificateInfo(id));
    }

    /**
     * 根据物料类型和产品形态查询适用的物料列表
     * 用于证书验证时快速筛选相同类型物料
     *
     * @param materialTypeCode 物料类型代码（如BO-Barre in verghe）
     * @param productShapeCode 产品形态代码（如T01-RND）
     * @return 物料列表
     */
    @SaCheckPermission("masterData:articleInfo:query")
    @GetMapping("/getByMaterialType")
    public R<List<HmeArticleInfoVo>> getByMaterialType(
            @RequestParam(required = false) String materialTypeCode,
            @RequestParam(required = false) String productShapeCode) {
        return R.ok(hmeArticleInfoService.queryByMaterialTypeAndShape(materialTypeCode, productShapeCode));
    }

    /**
     * 批量验证物料数据完整性
     * 检查物料是否配置了必要的合金、标准和测试规则
     *
     * @param ids 物料ID列表
     * @return 验证结果
     */
    @SaCheckPermission("masterData:articleInfo:query")
    @PostMapping("/validateArticles")
    public R<List<String>> validateArticleDataIntegrity(@RequestBody List<Long> ids) {
        List<String> validationResults = hmeArticleInfoService.validateArticleDataIntegrity(ids);
        return R.ok(validationResults);
    }

    /**
     * 同步SAP物料主数据
     * 从SAP系统同步物料信息（如果有对接需求）
     *
     * @param articleCodes SAP物料代码列表
     * @return 同步结果
     */
    @SaCheckPermission("masterData:articleInfo:import")
    @Log(title = "同步SAP物料数据", businessType = BusinessType.IMPORT)
    @PostMapping("/syncFromSap")
    public R<String> syncFromSap(@RequestBody List<String> articleCodes) {
        String result = hmeArticleInfoService.syncArticleFromSap(articleCodes);
        return R.ok(result);
    }

    /**
     * 查询物料的化学测试规则配置
     *
     * @param articleId 物料ID
     * @param alloyId 合金ID（可选）
     * @return 化学测试规则列表
     */
    @SaCheckPermission("masterData:articleInfo:query")
    @GetMapping("/{articleId}/chemicalRules")
    public R<List<Object>> getChemicalRules(@PathVariable Long articleId,
                                           @RequestParam(required = false) Long alloyId) {
        return R.ok(hmeArticleInfoService.getArticleChemicalRules(articleId, alloyId));
    }

    /**
     * 查询物料的机械测试规则配置
     *
     * @param articleId 物料ID
     * @param alloyId 合金ID（可选）
     * @return 机械测试规则列表
     */
    @SaCheckPermission("masterData:articleInfo:query")
    @GetMapping("/{articleId}/mechanicalRules")
    public R<List<Object>> getMechanicalRules(@PathVariable Long articleId,
                                             @RequestParam(required = false) Long alloyId) {
        return R.ok(hmeArticleInfoService.getArticleMechanicalRules(articleId, alloyId));
    }

    /**
     * 复制物料配置（包括关联的标准和规则）
     * 用于快速创建相似物料
     *
     * @param sourceId 源物料ID
     * @param targetArticleCode 目标物料代码
     * @param targetDescription 目标物料描述
     * @return 新创建的物料ID
     */
    @SaCheckPermission("masterData:articleInfo:add")
    @Log(title = "复制物料配置", businessType = BusinessType.INSERT)
    @PostMapping("/copyArticleConfig")
    public R<Long> copyArticleConfig(@RequestParam Long sourceId,
                                   @RequestParam String targetArticleCode,
                                   @RequestParam String targetDescription) {
        Long newArticleId = hmeArticleInfoService.copyArticleConfiguration(sourceId, targetArticleCode, targetDescription);
        return R.ok(newArticleId);
    }
}
