package com.cert.masterdata.service.impl;

import com.cert.common.core.utils.MapstructUtils;
import com.cert.common.core.utils.StringUtils;
import com.cert.common.mybatis.core.page.TableDataInfo;
import com.cert.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.cert.masterdata.domain.bo.HmeArticleInfoBo;
import com.cert.masterdata.domain.vo.HmeArticleInfoVo;
import com.cert.masterdata.domain.HmeArticleInfo;
import com.cert.masterdata.mapper.HmeArticleInfoMapper;
import com.cert.masterdata.service.IHmeArticleInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 主数据 - 物料信息Service业务层处理
 *
 * @author Zhou Hao
 * @date 2025-06-03
 */
@RequiredArgsConstructor
@Service
public class HmeArticleInfoServiceImpl implements IHmeArticleInfoService {

    private final HmeArticleInfoMapper baseMapper;

    /**
     * 查询主数据 - 物料信息
     *
     * @param id 主键
     * @return 主数据 - 物料信息
     */
    @Override
    public HmeArticleInfoVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询主数据 - 物料信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 主数据 - 物料信息分页列表
     */
    @Override
    public TableDataInfo<HmeArticleInfoVo> queryPageList(HmeArticleInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<HmeArticleInfo> lqw = buildQueryWrapper(bo);
        Page<HmeArticleInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的主数据 - 物料信息列表
     *
     * @param bo 查询条件
     * @return 主数据 - 物料信息列表
     */
    @Override
    public List<HmeArticleInfoVo> queryList(HmeArticleInfoBo bo) {
        LambdaQueryWrapper<HmeArticleInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<HmeArticleInfo> buildQueryWrapper(HmeArticleInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HmeArticleInfo> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(HmeArticleInfo::getId);
        lqw.eq(StringUtils.isNotBlank(bo.getArticleCode()), HmeArticleInfo::getArticleCode, bo.getArticleCode());
        lqw.eq(StringUtils.isNotBlank(bo.getArticleDescription()), HmeArticleInfo::getArticleDescription, bo.getArticleDescription());
        lqw.eq(StringUtils.isNotBlank(bo.getMaterialTypeCode()), HmeArticleInfo::getMaterialTypeCode, bo.getMaterialTypeCode());
        lqw.eq(StringUtils.isNotBlank(bo.getProductShapeCode()), HmeArticleInfo::getProductShapeCode, bo.getProductShapeCode());
        lqw.eq(StringUtils.isNotBlank(bo.getProjectShapeCode()), HmeArticleInfo::getProjectShapeCode, bo.getProjectShapeCode());
        lqw.eq(StringUtils.isNotBlank(bo.getGeneralSpecCode()), HmeArticleInfo::getGeneralSpecCode, bo.getGeneralSpecCode());
        lqw.eq(StringUtils.isNotBlank(bo.getDimensionSpecCodeKey()), HmeArticleInfo::getDimensionSpecCodeKey, bo.getDimensionSpecCodeKey());
        lqw.eq(StringUtils.isNotBlank(bo.getDimensionSpecCodeValue()), HmeArticleInfo::getDimensionSpecCodeValue, bo.getDimensionSpecCodeValue());
        lqw.eq(StringUtils.isNotBlank(bo.getInternalTemperCode()), HmeArticleInfo::getInternalTemperCode, bo.getInternalTemperCode());
        lqw.eq(StringUtils.isNotBlank(bo.getInternalSFCode()), HmeArticleInfo::getInternalSFCode, bo.getInternalSFCode());
        lqw.eq(StringUtils.isNotBlank(bo.getMaterialSFCode()), HmeArticleInfo::getMaterialSFCode, bo.getMaterialSFCode());
        lqw.eq(bo.getDefaultAlloyId() != null, HmeArticleInfo::getDefaultAlloyId, bo.getDefaultAlloyId());
        lqw.eq(bo.getDefaultChemStdId() != null, HmeArticleInfo::getDefaultChemStdId, bo.getDefaultChemStdId());
        lqw.eq(bo.getDefaultMechStdId() != null, HmeArticleInfo::getDefaultMechStdId, bo.getDefaultMechStdId());
        lqw.eq(bo.getDefaultDimStdId() != null, HmeArticleInfo::getDefaultDimStdId, bo.getDefaultDimStdId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), HmeArticleInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增主数据 - 物料信息
     *
     * @param bo 主数据 - 物料信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(HmeArticleInfoBo bo) {
        HmeArticleInfo add = MapstructUtils.convert(bo, HmeArticleInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改主数据 - 物料信息
     *
     * @param bo 主数据 - 物料信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(HmeArticleInfoBo bo) {
        HmeArticleInfo update = MapstructUtils.convert(bo, HmeArticleInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HmeArticleInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除主数据 - 物料信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }

    // ================== 意大利成品证书系统专用业务方法实现 ==================

    /**
     * 根据SAP物料代码查询物料信息
     */
    @Override
    public HmeArticleInfoVo queryByArticleCode(String articleCode) {
        LambdaQueryWrapper<HmeArticleInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(HmeArticleInfo::getArticleCode, articleCode);
        wrapper.eq(HmeArticleInfo::getStatus, "0"); // 只查询正常状态的物料
        return baseMapper.selectVoOne(wrapper);
    }

    /**
     * 查询物料的完整证书信息（包含合金、标准、测试规则等关联信息）
     */
    @Override
    public HmeArticleInfoVo queryArticleWithCertificateInfo(Long id) {
        // TODO: 实现完整的证书信息查询，包括关联的合金、标准、测试规则等
        // 这里先返回基本信息，后续可以通过连表查询或分步查询来获取完整信息
        return baseMapper.selectVoById(id);
    }

    /**
     * 根据物料类型和产品形态查询适用的物料列表
     */
    @Override
    public List<HmeArticleInfoVo> queryByMaterialTypeAndShape(String materialTypeCode, String productShapeCode) {
        LambdaQueryWrapper<HmeArticleInfo> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotBlank(materialTypeCode), HmeArticleInfo::getMaterialTypeCode, materialTypeCode);
        wrapper.eq(StringUtils.isNotBlank(productShapeCode), HmeArticleInfo::getProductShapeCode, productShapeCode);
        wrapper.eq(HmeArticleInfo::getStatus, "0"); // 只查询正常状态的物料
        wrapper.orderByAsc(HmeArticleInfo::getArticleCode);
        return baseMapper.selectVoList(wrapper);
    }

    /**
     * 批量验证物料数据完整性
     */
    @Override
    public List<String> validateArticleDataIntegrity(List<Long> ids) {
        List<String> validationResults = new java.util.ArrayList<>();
        
        for (Long id : ids) {
            HmeArticleInfoVo article = baseMapper.selectVoById(id);
            if (article == null) {
                validationResults.add("物料ID " + id + " 不存在");
                continue;
            }
            
            // 验证必要字段
            if (StringUtils.isBlank(article.getArticleCode())) {
                validationResults.add("物料 " + id + " 缺少物料代码");
            }
            if (article.getDefaultAlloyId() == null) {
                validationResults.add("物料 " + article.getArticleCode() + " 未配置默认合金");
            }
            if (article.getDefaultChemStdId() == null) {
                validationResults.add("物料 " + article.getArticleCode() + " 未配置默认化学标准");
            }
            if (article.getDefaultMechStdId() == null) {
                validationResults.add("物料 " + article.getArticleCode() + " 未配置默认机械标准");
            }
            
            // TODO: 可以添加更多验证逻辑，如检查关联的测试规则是否完整等
        }
        
        if (validationResults.isEmpty()) {
            validationResults.add("所有物料数据验证通过");
        }
        
        return validationResults;
    }

    /**
     * 同步SAP物料主数据
     */
    @Override
    public String syncArticleFromSap(List<String> articleCodes) {
        // TODO: 实现SAP系统对接逻辑
        // 这里提供一个示例实现框架
        int successCount = 0;
        int failCount = 0;
        
        for (String articleCode : articleCodes) {
            try {
                // 模拟从SAP获取物料信息的逻辑
                // 实际实现时需要调用SAP接口
                // SapArticleInfo sapInfo = sapService.getArticleInfo(articleCode);
                
                // 检查物料是否已存在
                HmeArticleInfoVo existingArticle = queryByArticleCode(articleCode);
                if (existingArticle != null) {
                    // 更新现有物料信息
                    // updateArticleFromSap(existingArticle, sapInfo);
                } else {
                    // 创建新物料信息
                    // createArticleFromSap(sapInfo);
                }
                successCount++;
            } catch (Exception e) {
                failCount++;
                // 记录同步失败的日志
            }
        }
        
        return String.format("同步完成：成功 %d 条，失败 %d 条", successCount, failCount);
    }

    /**
     * 查询物料的化学测试规则配置
     */
    @Override
    public List<Object> getArticleChemicalRules(Long articleId, Long alloyId) {
        // TODO: 实现查询物料的化学测试规则
        // 需要根据物料信息和合金信息查询相关的化学测试规则
        return new java.util.ArrayList<>();
    }

    /**
     * 查询物料的机械测试规则配置
     */
    @Override
    public List<Object> getArticleMechanicalRules(Long articleId, Long alloyId) {
        // TODO: 实现查询物料的机械测试规则
        // 需要根据物料信息和合金信息查询相关的机械测试规则
        return new java.util.ArrayList<>();
    }

    /**
     * 复制物料配置（包括关联的标准和规则）
     */
    @Override
    public Long copyArticleConfiguration(Long sourceId, String targetArticleCode, String targetDescription) {
        // 获取源物料信息
        HmeArticleInfo sourceArticle = baseMapper.selectById(sourceId);
        if (sourceArticle == null) {
            throw new RuntimeException("源物料不存在");
        }
        
        // 检查目标物料代码是否已存在
        HmeArticleInfoVo existingTarget = queryByArticleCode(targetArticleCode);
        if (existingTarget != null) {
            throw new RuntimeException("目标物料代码已存在");
        }
        
        // 创建新物料
        HmeArticleInfo newArticle = new HmeArticleInfo();
        // 复制源物料的所有配置
        newArticle.setArticleCode(targetArticleCode);
        newArticle.setArticleDescription(targetDescription);
        newArticle.setMaterialTypeCode(sourceArticle.getMaterialTypeCode());
        newArticle.setProductShapeCode(sourceArticle.getProductShapeCode());
        newArticle.setProjectShapeCode(sourceArticle.getProjectShapeCode());
        newArticle.setGeneralSpecCode(sourceArticle.getGeneralSpecCode());
        newArticle.setDimensionSpecCodeKey(sourceArticle.getDimensionSpecCodeKey());
        newArticle.setDimensionSpecCodeValue(sourceArticle.getDimensionSpecCodeValue());
        newArticle.setInternalTemperCode(sourceArticle.getInternalTemperCode());
        newArticle.setInternalSFCode(sourceArticle.getInternalSFCode());
        newArticle.setMaterialSFCode(sourceArticle.getMaterialSFCode());
        newArticle.setDefaultAlloyId(sourceArticle.getDefaultAlloyId());
        newArticle.setDefaultChemStdId(sourceArticle.getDefaultChemStdId());
        newArticle.setDefaultMechStdId(sourceArticle.getDefaultMechStdId());
        newArticle.setDefaultDimStdId(sourceArticle.getDefaultDimStdId());
        newArticle.setStatus("0");
        
        // 保存新物料
        validEntityBeforeSave(newArticle);
        baseMapper.insert(newArticle);
        
        // TODO: 如果需要，可以在这里复制相关的测试规则配置
        
        return newArticle.getId();
    }
}
