-- ----------------------------
-- 主数据模块 (hme-masterdata)
-- ----------------------------

-- 1. 合金信息表 (hme_alloy_info)
-- 存储内部合金代码、官方合金代码、铸造厂合金代码等
-- Finished Product Certificate(1).pdf: Anagrafica, Lega fonderia
-- How to validate Chemical_Mechanical results.pdf: Tab EA4-Off alloy, Tab EAS-Intermal Alloy
DROP TABLE IF EXISTS `hme_alloy_info`;
CREATE TABLE `hme_alloy_info`
(
    `id`                  bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `internal_alloy_code` varchar(50)  NOT NULL COMMENT '内部合金代码 (对应PDF中 C_APLEGI 如 N17, 或类似 AlloyChemicalRules.CODICE)',
    `official_alloy_code` varchar(50)  NULL DEFAULT NULL COMMENT '官方合金代码 (对应PDF中 C_APLEGA 如 CW617N, 或 DAPILEGI 如 CW617N-DW)',
    `foundry_alloy_code`  varchar(50)  NULL DEFAULT NULL COMMENT '铸造厂合金代码 (实验室设备原始代码, 对应PDF中 AlloyChemicalRules.CODICE 的 001 示例)',
    `description`         varchar(255) NULL DEFAULT NULL COMMENT '合金描述',
    `status`              char(1)      NULL DEFAULT '0' COMMENT '状态 (0=正常 1=停用)',
    `version`             int(0)       NULL DEFAULT 0 COMMENT '版本号',
    `create_dept`         bigint(0)    NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`           bigint(0)    NULL DEFAULT NULL COMMENT '创建人',
    `update_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`           bigint(0)    NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`            char(1)      NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`              varchar(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_internal_alloy_code` (`internal_alloy_code`) USING BTREE,
    INDEX `idx_official_alloy_code` (`official_alloy_code`) USING BTREE,
    INDEX `idx_foundry_alloy_code` (`foundry_alloy_code`) USING BTREE
) ENGINE = InnoDB COMMENT = '主数据 - 合金信息表';

-- 2. 标准信息表 (hme_standard_info)
-- 存储化学、机械、尺寸等标准
-- Finished Product Certificate(1).pdf: Norma Meccanice
-- How to validate Chemical_Mechanical results.pdf: Tab EA3-Standard, 以及规则中引用的标准文件
DROP TABLE IF EXISTS `hme_standard_info`;
CREATE TABLE `hme_standard_info`
(
    `id`            bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `standard_code` varchar(100) NOT NULL COMMENT '标准编号 (如 EN 12165, ASTM B124M, 或PDF中提到的 M.M. Salute 6.4.2004 n.174)',
    `standard_type` char(1)      NOT NULL COMMENT '标准类型 (1=化学, 2=机械, 3=尺寸)',
    `description`   varchar(255) NULL DEFAULT NULL COMMENT '标准描述',
    `status`        char(1)      NULL DEFAULT '0' COMMENT '状态 (0=正常 1=停用)',
    `version`       int(0)       NULL DEFAULT 0 COMMENT '版本号',
    `create_dept`   bigint(0)    NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`   datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`     bigint(0)    NULL DEFAULT NULL COMMENT '创建人',
    `update_time`   datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`     bigint(0)    NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`      char(1)      NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`        varchar(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_standard_code` (`standard_code`) USING BTREE
) ENGINE = InnoDB COMMENT = '主数据 - 标准信息表';

-- 3. 测试项目表 (hme_test_item)
-- 存储化学元素、机械测试项目等
-- How to validate Chemical_Mechanical results.pdf: Green part (mechanical units), Red part (chemical composition), C_ELEMENT, D_ELEMENT, C_MECTEST, D_MECTEST
DROP TABLE IF EXISTS `hme_test_item`;
CREATE TABLE `hme_test_item`
(
    `id`          bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `item_code`   varchar(50)  NOT NULL COMMENT '项目代码 (建议使用PDF中的系统代码, 如 UM22, MT001, 或 Cu, HB 等符号)',
    `item_name`   varchar(100) NOT NULL COMMENT '项目名称 (如 铜, 铅, 硬度, 抗拉强度, 对应PDF D_ELEMENT, D_MECTEST)',
    `item_type`   char(1)      NOT NULL COMMENT '类型 (1=化学元素, 2=机械性能, 3=尺寸公差)',
    `unit_code`   varchar(20)  NULL DEFAULT NULL COMMENT '单位代码 (如 %, N/mm2, mm, 对应PDF D_UM)',
    `description` varchar(255) NULL DEFAULT NULL COMMENT '项目描述',
    `order_num`   int(0)       NULL DEFAULT 0 COMMENT '显示排序号',
    `status`      char(1)      NULL DEFAULT '0' COMMENT '状态 (0=正常 1=停用)',
    `version`     int(0)       NULL DEFAULT 0 COMMENT '版本号',
    `create_dept` bigint(0)    NULL DEFAULT NULL COMMENT '创建部门',
    `create_time` datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   bigint(0)    NULL DEFAULT NULL COMMENT '创建人',
    `update_time` datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   bigint(0)    NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`    char(1)      NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`      varchar(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_item_code_type` (`item_code`, `item_type`) USING BTREE COMMENT '项目代码和类型组合唯一'
) ENGINE = InnoDB COMMENT = '主数据 - 测试项目表';

-- 4. 化学规则表 (hme_chemical_rule)
-- 存储特定合金在特定标准下的化学元素限值
-- How to validate Chemical_Mechanical results.pdf: Link Alloys Element Limits Tab (Page 3)
DROP TABLE IF EXISTS `hme_chemical_rule`;
CREATE TABLE `hme_chemical_rule`
(
    `id`                bigint(0)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `rule_code`         varchar(50)    NOT NULL COMMENT '规则代码 (对应PDF CHEMICAL_RULE, 如 CH001)',
    `alloy_id`          bigint(0)      NOT NULL COMMENT '合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA)',
    `standard_id`       bigint(0)      NOT NULL COMMENT '标准ID (关联 hme_standard_info.id)',
    `item_id`           bigint(0)      NOT NULL COMMENT '测试项目ID (化学元素, 关联 hme_test_item.id, 对应PDF C_ELEMENT)',
    `min_value`         decimal(10, 5) NULL DEFAULT NULL COMMENT '最小值 (对应PDF MIN)',
    `max_value`         decimal(10, 5) NULL DEFAULT NULL COMMENT '最大值 (对应PDF MAX)',
    `value_type`        char(1)        NULL DEFAULT '1' COMMENT '值类型 (1=浓度 Concentration, 2=杂质 Impurity, 3=差值 Per differenza/Remain)',
    `fixed_string`      varchar(50)    NULL DEFAULT NULL COMMENT '固定字符串 (如 Rem., 对应PDF STRING)',
    `check_limit_flag`  char(1)        NULL DEFAULT '1' COMMENT '检查限制标志 (1=是, 0=否, 对应PDF FL_CHECK_LIM)',
    `print_report_flag` char(1)        NULL DEFAULT '1' COMMENT '内部报告打印标志 (1=是, 0=否, 对应PDF FL_PRINT_DOC)',
    `print_cert_flag`   char(1)        NULL DEFAULT '1' COMMENT '证书打印标志 (1=是, 0=否, 对应PDF FL_PRINT_CERT)',
    `version`           int(0)         NULL DEFAULT 0 COMMENT '版本号',
    `create_dept`       bigint(0)      NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`       datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`         bigint(0)      NULL DEFAULT NULL COMMENT '创建人',
    `update_time`       datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`         bigint(0)      NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`          char(1)        NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`            varchar(500)   NULL DEFAULT NULL COMMENT '备注 (可存储PDF中的 D_CREAT, D_LASTMOD等信息)',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_chem_rule` (`rule_code`, `alloy_id`, `standard_id`, `item_id`) USING BTREE,
    INDEX `idx_chem_rule_alloy_std` (`alloy_id`, `standard_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '主数据 - 化学规则表';

-- 5. 机械规则表 (hme_mechanical_rule)
-- 存储特定物料、合金、形态、回火状态等在特定标准下的机械性能限值
-- How to validate Chemical_Mechanical results.pdf: C_MECHANICAL_RULE, C_SUB_MECH_RULE (Page 4 tables)
DROP TABLE IF EXISTS `hme_mechanical_rule`;
CREATE TABLE `hme_mechanical_rule`
(
    `id`                    bigint(0)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `rule_code`             varchar(50)    NOT NULL COMMENT '规则代码 (对应PDF C_MECHANICAL_RULE, 如 ME0074)',
    `sub_rule_code`         varchar(50)    NULL DEFAULT NULL COMMENT '子规则代码 (对应PDF C_SUB_MECH_RULE, 如 SM0379)',
    `material_type_code`    varchar(50)    NULL DEFAULT NULL COMMENT '物料类型代码 (SAP中的, 对应PDF C_APTYPO1, 如 BO)',
    `product_shape_code`    varchar(50)    NULL DEFAULT NULL COMMENT '产品形态代码 (SAP中的, 对应PDF C_APFORM, 如 T01)',
    `general_spec_code`     varchar(50)    NULL DEFAULT NULL COMMENT '通用规格代码 (SAP中的, 对应PDF C_APCNOR, 如 E01)',
    `internal_temper_code`  varchar(50)    NULL DEFAULT NULL COMMENT '内部回火状态代码 (SAP中的, 对应PDF C_APSEMA)',
    `dimension_spec_code`   varchar(50)    NULL DEFAULT NULL COMMENT '尺寸规格代码 (SAP中的, 对应PDF C_APSPIN 如 S24)',
    `alloy_id`              bigint(0)      NOT NULL COMMENT '合金ID (关联 hme_alloy_info.id, 对应PDF C_APLEGA/C_APLEGI)',
    `standard_id`           bigint(0)      NOT NULL COMMENT '标准ID (关联 hme_standard_info.id)',
    `item_id`               bigint(0)      NOT NULL COMMENT '测试项目ID (机械性能/尺寸, 关联 hme_test_item.id, 对应PDF C_MECTEST)',
    `dimension1_min`        decimal(10, 3) NULL DEFAULT NULL COMMENT '适用尺寸1下限 (对应PDF V_MIN1)',
    `dimension1_max`        decimal(10, 3) NULL DEFAULT NULL COMMENT '适用尺寸1上限 (对应PDF V_MAX1)',
    `dimension2_min`        decimal(10, 3) NULL DEFAULT NULL COMMENT '适用尺寸2下限 (对应PDF V_MIN2)',
    `dimension2_max`        decimal(10, 3) NULL DEFAULT NULL COMMENT '适用尺寸2上限 (对应PDF V_MAX2)',
    `dimension3_min`        decimal(10, 3) NULL DEFAULT NULL COMMENT '适用尺寸3下限 (对应PDF V_MIN3)', -- 新增
    `dimension3_max`        decimal(10, 3) NULL DEFAULT NULL COMMENT '适用尺寸3上限 (对应PDF V_MAX3)', -- 新增
    `min_value`             decimal(10, 3) NULL DEFAULT NULL COMMENT '最小值 (对应PDF V_LIMITMIN)',
    `max_value`             decimal(10, 3) NULL DEFAULT NULL COMMENT '最大值 (对应PDF V_LIMITMAX)',
    `status`                char(1)        NULL DEFAULT '0' COMMENT '状态 (0=正常 1=停用)',
    `version`               int(0)         NULL DEFAULT 0 COMMENT '版本号',
    `create_dept`           bigint(0)      NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`           datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`             bigint(0)      NULL DEFAULT NULL COMMENT '创建人',
    `update_time`           datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`             bigint(0)      NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`              char(1)        NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`                varchar(500)   NULL DEFAULT NULL COMMENT '备注 (可存储PDF中的F_CHECKLIMIT, F_PRINTINTREPORT, F_PRINTRESULT等标志)',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_mech_rule_lookup` (`alloy_id`, `standard_id`, `material_type_code`, `product_shape_code`, `general_spec_code`, `internal_temper_code`, `dimension_spec_code`, `item_id`) USING BTREE COMMENT '用于查找特定条件下项目规则的索引'
) ENGINE = InnoDB COMMENT = '主数据 - 机械规则表';

-- 6. 客户信息表 (hme_customer_info)
-- 存储客户基本信息及证书邮件地址
-- Finished Product Certificate(1).pdf: Mail per certificate, Cliente
DROP TABLE IF EXISTS `hme_customer_info`;
CREATE TABLE `hme_customer_info`
(
    `id`            bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `customer_code` varchar(50)  NOT NULL COMMENT '客户代码 (来自SAP)',
    `customer_name` varchar(255) NOT NULL COMMENT '客户名称',
    `email_address` varchar(255) NULL DEFAULT NULL COMMENT '证书接收邮箱',
    `status`        char(1)      NULL DEFAULT '0' COMMENT '状态 (0=正常 1=停用)',
    `version`       int(0)       NULL DEFAULT 0 COMMENT '版本号',
    `create_dept`   bigint(0)    NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`   datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`     bigint(0)    NULL DEFAULT NULL COMMENT '创建人',
    `update_time`   datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`     bigint(0)    NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`      char(1)      NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`        varchar(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_customer_code` (`customer_code`) USING BTREE
) ENGINE = InnoDB COMMENT = '主数据 - 客户信息表';

-- 7. 物料信息表 (hme_article_info)
-- 存储物料基础信息，用于规则匹配和报告显示
-- Finished Product Certificate(1).pdf: Articolo, Desc. Materiale
-- How to validate Chemical_Mechanical results.pdf: Page 4 top table
DROP TABLE IF EXISTS `hme_article_info`;
CREATE TABLE `hme_article_info` (
                                    `id`                    bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                    `article_code`          varchar(50) NOT NULL COMMENT '物料代码 (SAP, 对应PDF Material)',
                                    `article_description`   varchar(255) NULL DEFAULT NULL COMMENT '物料描述 (对应PDF Overall Description)',
                                    `material_type_code`    varchar(50) NULL DEFAULT NULL COMMENT '物料类型代码 (如 BO-Barre in verghe, 对应PDF Product Typology Code)',
                                    `product_shape_code`    varchar(50) NULL DEFAULT NULL COMMENT '产品形态代码 (如 T01-RND, 对应PDF Product Shape Code)',
                                    `project_shape_code`    varchar(50) NULL DEFAULT NULL COMMENT '项目形态代码 (对应PDF Project Shape Code)', -- 对应PDF Page 4 top table
                                    `general_spec_code`     varchar(50) NULL DEFAULT NULL COMMENT '通用规格代码 (如 E01-Estruso, 对应PDF General Specification Code)',
                                    `dimension_spec_code_key` varchar(50) NULL DEFAULT NULL COMMENT '尺寸规格代码-键 (对应PDF Dimension Specification Code e.g. S24)', -- 对应PDF Page 4 top table
                                    `dimension_spec_code_value` varchar(50) NULL DEFAULT NULL COMMENT '尺寸规格代码-值 (对应PDF Dimensional Specification Code e.g. 121856)', -- 对应PDF Page 4 top table
                                    `internal_temper_code`  varchar(50) NULL DEFAULT NULL COMMENT '内部回火状态代码 (对应PDF Internal Temper Code e.g. D41)', -- 对应PDF Page 4 top table
                                    `internal_s_f_code`     varchar(50) NULL DEFAULT NULL COMMENT '内部半成品形态代码 (S.F. Interno)',
                                    `material_s_f_code`     varchar(50) NULL DEFAULT NULL COMMENT '物料半成品形态代码 (S.F. Materiale)',
                                    `default_alloy_id`      bigint(0) NULL DEFAULT NULL COMMENT '默认合金ID (关联 hme_alloy_info.id, 对应PDF Official Alloy Code/Internal Alloy Code)',
                                    `default_chem_std_id`   bigint(0) NULL DEFAULT NULL COMMENT '默认化学标准ID (关联 hme_standard_info.id)',
                                    `default_mech_std_id`   bigint(0) NULL DEFAULT NULL COMMENT '默认机械标准ID (关联 hme_standard_info.id)',
                                    `default_dim_std_id`    bigint(0) NULL DEFAULT NULL COMMENT '默认尺寸标准ID (关联 hme_standard_info.id)',
                                    `status`                char(1) NULL DEFAULT '0' COMMENT '状态 (0=正常 1=停用)',
                                    `version`               int(0) NULL DEFAULT 0 COMMENT '版本号',
                                    `create_dept`           bigint(0) NULL DEFAULT NULL COMMENT '创建部门',
                                    `create_time`           datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `create_by`             bigint(0) NULL DEFAULT NULL COMMENT '创建人',
                                    `update_time`           datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `update_by`             bigint(0) NULL DEFAULT NULL COMMENT '更新人',
                                    `del_flag`              char(1) NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
                                    `remark`                varchar(500) NULL DEFAULT NULL COMMENT '备注',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE INDEX `uk_article_code` (`article_code`) USING BTREE
) ENGINE=InnoDB COMMENT='主数据 - 物料信息表';

-- ----------------------------
-- 实验室模块 (hme-lab)
-- ----------------------------

-- 8. 化学分析结果表 (hme_chemical_analysis)
-- 存储从实验室导入并经过验证的化学分析结果
-- Finished Product Certificate(1).pdf: Gestione Chimica
-- Old App Screenshot (1).pdf: Chemical Analyse screens
DROP TABLE IF EXISTS `hme_chemical_analysis`;
CREATE TABLE `hme_chemical_analysis`
(
    `id`                  bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `analysis_no`         varchar(50)  NOT NULL COMMENT '分析号 (来自实验室设备, 唯一)',
    `alloy_id`            bigint(0)    NULL DEFAULT NULL COMMENT '合金ID (关联 hme_alloy_info.id, 验证后确定)',
    `foundry_alloy_code`  varchar(50)  NULL DEFAULT NULL COMMENT '铸造厂合金代码 (导入时原始值)',
    `format`              varchar(50)  NULL DEFAULT NULL COMMENT '形态 (如 Barra)',
    `dimension`           varchar(50)  NULL DEFAULT NULL COMMENT '尺寸 (如 410)',
    `fusion_date`         datetime(0)  NULL DEFAULT NULL COMMENT '熔炼日期 (Data Fusione)',
    `last_modified_date`  datetime(0)  NULL DEFAULT NULL COMMENT '最后修改日期 (Data Ult. Mod.)',
    `status`              char(1)      NOT NULL COMMENT '状态 (0=未确认 Not Confirmed, 1=已确认 Confirmed, 2=已报废 Scrapped)',
    `working_order_no`    varchar(50)  NULL DEFAULT NULL COMMENT '工作订单号 (后续关联)',
    `validation_notes`    text         NULL DEFAULT NULL COMMENT '验证备注/不合格原因',
    `import_batch_id`     varchar(50)  NULL DEFAULT NULL COMMENT '导入批次号 (用于追溯同一次导入)',
    `import_file_name`    varchar(255) NULL DEFAULT NULL COMMENT '导入文件名',
    `version`             int(0)       NULL DEFAULT 0 COMMENT '版本号',
    `create_dept`         bigint(0)    NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 (导入时间)',
    `create_by`           bigint(0)    NULL DEFAULT NULL COMMENT '创建人 (导入操作人)',
    `update_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 (验证/修改时间)',
    `update_by`           bigint(0)    NULL DEFAULT NULL COMMENT '更新人 (验证/修改操作人)',
    `del_flag`            char(1)      NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`              varchar(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_analysis_no` (`analysis_no`) USING BTREE,
    INDEX `idx_chem_analysis_status` (`status`) USING BTREE,
    INDEX `idx_chem_analysis_alloy` (`alloy_id`) USING BTREE,
    INDEX `idx_chem_analysis_fusion_date` (`fusion_date`) USING BTREE
) ENGINE = InnoDB COMMENT = '实验 - 化学分析结果表';

-- 9. 化学分析明细表 (hme_chemical_detail)
-- 存储单次化学分析中各元素的测量值
DROP TABLE IF EXISTS `hme_chemical_detail`;
CREATE TABLE `hme_chemical_detail`
(
    `id`                bigint(0)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `analysis_id`       bigint(0)      NOT NULL COMMENT '化学分析ID (关联 hme_chemical_analysis.id)',
    `item_id`           bigint(0)      NOT NULL COMMENT '测试项目ID (化学元素, 关联 hme_test_item.id)',
    `measured_value`    decimal(10, 5) NOT NULL COMMENT '测量值',
    `validation_status` char(1)        NULL DEFAULT '0' COMMENT '验证状态 (0=待验证, 1=OK, 2=KO)',
    `is_out_of_spec`    char(1)        NULL DEFAULT '0' COMMENT '是否超差 (1=是, 0=否)',
    `applied_rule_id`   bigint(0)      NULL DEFAULT NULL COMMENT '应用的化学规则ID (关联 hme_chemical_rule.id)',
    `create_time`       datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`       datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_chem_detail_item` (`analysis_id`, `item_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '实验 - 化学分析明细表';

-- 10. 机械性能测试表 (hme_mechanical_test_result)
-- 存储手动录入的机械性能测试结果
-- Finished Product Certificate(1).pdf: Gestione meccanica
DROP TABLE IF EXISTS `hme_mechanical_test_result`;
CREATE TABLE `hme_mechanical_test_result`
(
    `id`                  bigint(0)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `internal_report_id`  bigint(0)      NOT NULL COMMENT '内部报告ID (关联 hme_internal_report.id)',
    `item_id`             bigint(0)      NOT NULL COMMENT '测试项目ID (机械性能/尺寸, 关联 hme_test_item.id)',
    `measured_value`      decimal(10, 3) NULL DEFAULT NULL COMMENT '测量值',
    `measured_value_2`    decimal(10, 3) NULL DEFAULT NULL COMMENT '测量值2 (备用)',
    `measured_value_3`    decimal(10, 3) NULL DEFAULT NULL COMMENT '测量值3 (备用)',
    `validation_status`   char(1)        NULL DEFAULT '0' COMMENT '验证状态 (0=待执行 Da Eseguire, 1=OK, 2=KO)',
    `applied_rule_id`     bigint(0)      NULL DEFAULT NULL COMMENT '应用的机械规则ID (关联 hme_mechanical_rule.id)',
    `test_type`           varchar(50)    NULL DEFAULT NULL COMMENT '测试类型 (如 Tolleranza Dimensiona, Meccanica/Fisica)',
    `version`             int(0)         NULL DEFAULT 0 COMMENT '版本号',
    `create_dept`         bigint(0)      NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`         datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 (录入时间)',
    `create_by`           bigint(0)      NULL DEFAULT NULL COMMENT '创建人 (录入人)',
    `update_time`         datetime(0)    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`           bigint(0)      NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`            char(1)        NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`              varchar(500)   NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_mech_test_report_item` (`internal_report_id`, `item_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '实验 - 机械性能测试结果表';

-- -------------------------------------
-- 生产与订单模块 (hme-production)
-- -------------------------------------

-- 11. 生产订单表 (hme_production_order)
-- 存储从SAP同步或手动录入的生产订单信息
-- WorkflowFinishedProductCertificate(1).pdf: Production Order
-- Old App Screenshot (1).pdf: PRODUCTION ORDER/LINE
DROP TABLE IF EXISTS `hme_production_order`;
CREATE TABLE `hme_production_order`
(
    `id`                  bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `prod_order_no`       varchar(50)  NOT NULL COMMENT '生产订单号 (Conferma)',
    `item_no`             varchar(10)  NOT NULL COMMENT '行项目号 (Voce)',
    `article_id`          bigint(0)    NOT NULL COMMENT '物料ID (关联 hme_article_info.id)',
    `quantity`            decimal(12, 3) NULL DEFAULT NULL COMMENT '订单数量',
    `unit`                varchar(20)  NULL DEFAULT NULL COMMENT '数量单位',
    `batch_no`            varchar(50)  NULL DEFAULT NULL COMMENT '批次号 (Collo / COLLI PESATI)',
    `working_order_no`    varchar(50)  NULL DEFAULT NULL COMMENT '工作订单号 (OF/OdL / NumLavo / RG146146)',
    `customer_id`         bigint(0)    NULL DEFAULT NULL COMMENT '客户ID (关联 hme_customer_info.id, 如HME BRASS ITALY SPA)',
    `due_date`            date         NULL DEFAULT NULL COMMENT '到期日期 (Data Scadenza)',
    `source_system`       varchar(20)  NULL DEFAULT 'SAP' COMMENT '来源系统 (SAP, Manual)',
    `source_data_json`    text         NULL DEFAULT NULL COMMENT '源系统原始数据JSON',
    `status`              char(1)      NULL DEFAULT '0' COMMENT '状态 (0=待处理 1=已生成内部报告)',
    `create_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`           bigint(0)    NULL DEFAULT NULL COMMENT '创建人',
    `update_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`           bigint(0)    NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`            char(1)      NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`              varchar(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_prod_order_item` (`prod_order_no`, `item_no`) USING BTREE,
    INDEX `idx_prod_order_batch` (`batch_no`) USING BTREE,
    INDEX `idx_prod_order_working` (`working_order_no`) USING BTREE
) ENGINE = InnoDB COMMENT = '生产 - 生产订单表';

-- 12. 发货单表 (hme_delivery_note)
-- 存储从SAP同步或手动录入的发货单信息
-- WorkflowFinishedProductCertificate(1).pdf: Delivery Note
-- Old App Screenshot (1).pdf: Certification Creation (Enter Delivery note / Item)
DROP TABLE IF EXISTS `hme_delivery_note`;
CREATE TABLE `hme_delivery_note`
(
    `id`                    bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `dn_no`                 varchar(50)  NOT NULL COMMENT '发货单号 (Ddt / Documento)',
    `dn_item_no`            varchar(10)  NOT NULL COMMENT '发货单行项目号',
    `sales_order_no`        varchar(50)  NULL DEFAULT NULL COMMENT '销售订单号 (Conferma)',
    `sales_order_item_no`   varchar(10)  NULL DEFAULT NULL COMMENT '销售订单行项目号 (Voce)',
    `customer_id`           bigint(0)    NOT NULL COMMENT '客户ID (关联 hme_customer_info.id)',
    `customer_ref`          varchar(100) NULL DEFAULT NULL COMMENT '客户参考号 (Rif. Cliente)',
    `article_id`            bigint(0)    NOT NULL COMMENT '物料ID (关联 hme_article_info.id)',
    `customer_article_code` varchar(50)  NULL DEFAULT NULL COMMENT '客户物料代码 (Art. Cliente)',
    `quantity`              decimal(12, 3) NOT NULL COMMENT '发货数量 (Quantita KG)',
    `unit`                  varchar(20)  NULL DEFAULT NULL COMMENT '数量单位',
    `shipment_no`           varchar(50)  NULL DEFAULT NULL COMMENT '装运号',
    `certificate_type_req`  char(1)      NULL DEFAULT '1' COMMENT '要求的证书类型 (1=3.1, 2=2.2, 从销售订单带入)',
    `delivery_date`         date         NULL DEFAULT NULL COMMENT '发货日期',
    `shipping_point`        varchar(50)  NULL DEFAULT NULL COMMENT '发货点',
    `plant`                 varchar(50)  NULL DEFAULT NULL COMMENT '工厂',
    `source_system`         varchar(20)  NULL DEFAULT 'SAP' COMMENT '来源系统',
    `status`                char(1)      NULL DEFAULT '0' COMMENT '状态 (0=待生成证书 1=已生成证书)',
    `create_time`           datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`             bigint(0)    NULL DEFAULT NULL COMMENT '创建人',
    `update_time`           datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`             bigint(0)    NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`              char(1)      NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`                varchar(500) NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_dn_item` (`dn_no`, `dn_item_no`) USING BTREE
) ENGINE = InnoDB COMMENT = '生产 - 发货单表';

-- 13. 发货单批次关联表 (hme_dn_prod_order_link)
-- 存储发货单物料与生产订单批次的关联关系 (用于3.1证书追溯)
DROP TABLE IF EXISTS `hme_dn_prod_order_link`;
CREATE TABLE `hme_dn_prod_order_link`
(
    `id`              bigint(0)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `dn_id`           bigint(0)      NOT NULL COMMENT '发货单ID (关联 hme_delivery_note.id)',
    `prod_order_id`   bigint(0)      NOT NULL COMMENT '生产订单ID (关联 hme_production_order.id)',
    `linked_quantity` decimal(12, 3) NOT NULL COMMENT '关联数量',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_dn_link_dn` (`dn_id`) USING BTREE,
    INDEX `idx_dn_link_prod_order` (`prod_order_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '生产 - 发货单与生产订单关联表';

-- ----------------------------
-- 证书模块 (hme-certificate)
-- ----------------------------

-- 14. 内部报告表 (hme_internal_report)
-- 存储结合了化学和机械测试结果的内部质量报告
-- Finished Product Certificate(1).pdf: Gestione Bollettini
-- Old App Screenshot (1).pdf: INTERNAL REPORT screens
DROP TABLE IF EXISTS `hme_internal_report`;
CREATE TABLE `hme_internal_report`
(
    `id`                  bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `report_no`           varchar(50)  NOT NULL COMMENT '报告编号 (Bollettino Num.)',
    `prod_order_id`       bigint(0)    NOT NULL COMMENT '生产订单ID (关联 hme_production_order.id)',
    `analysis_id`         bigint(0)    NOT NULL COMMENT '化学分析ID (关联 hme_chemical_analysis.id)',
    `status`              char(1)      NOT NULL DEFAULT '0' COMMENT '状态 (0=已创建 CREATO, 1=完成OK FINE OK, 2=完成NG)',
    `chemical_status_ok`  char(1)      NULL DEFAULT '1' COMMENT '化学结果是否OK (1=是, 0=否)',
    `mechanical_status_ok` char(1)      NULL DEFAULT '0' COMMENT '机械结果是否OK (1=是, 0=否, 2=待执行)',
    `report_date`         date         NULL DEFAULT NULL COMMENT '报告日期',
    `version`             int(0)       NULL DEFAULT 0 COMMENT '版本号',
    `create_dept`         bigint(0)    NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`           bigint(0)    NULL DEFAULT NULL COMMENT '创建人',
    `update_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`           bigint(0)    NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`            char(1)      NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`              text         NULL DEFAULT NULL COMMENT '报告备注 (Note)',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_report_no` (`report_no`) USING BTREE,
    INDEX `idx_report_prod_order` (`prod_order_id`) USING BTREE,
    INDEX `idx_report_analysis` (`analysis_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '证书 - 内部报告表';

-- 15. 内部报告明细表 (hme_internal_report_detail)
-- 存储内部报告中显示的化学和机械测试项及其值
DROP TABLE IF EXISTS `hme_internal_report_detail`;
CREATE TABLE `hme_internal_report_detail`
(
    `id`                  bigint(0)      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `report_id`           bigint(0)      NOT NULL COMMENT '内部报告ID (关联 hme_internal_report.id)',
    `item_id`             bigint(0)      NOT NULL COMMENT '测试项目ID (关联 hme_test_item.id)',
    `item_type`           char(1)        NOT NULL COMMENT '项目类型 (1=化学, 2=机械, 3=尺寸)',
    `value`               varchar(100)   NULL DEFAULT NULL COMMENT '显示值 (可能是数值或Rem.)',
    `min_limit`           varchar(50)    NULL DEFAULT NULL COMMENT '最小限值 (显示用)',
    `max_limit`           varchar(50)    NULL DEFAULT NULL COMMENT '最大限值 (显示用)',
    `status_ok`           char(1)        NULL DEFAULT '1' COMMENT '该项是否OK (1=是, 0=否, 2=待执行)',
    `print_on_report_flag` char(1)        NULL DEFAULT '1' COMMENT '内部报告打印标志 (1=是, 0=否, 来自规则或手动调整)',
    `print_on_cert_flag`  char(1)        NULL DEFAULT '1' COMMENT '证书打印标志 (1=是, 0=否, 来自规则或手动调整)',
    `control_limit_flag`  char(1)        NULL DEFAULT '1' COMMENT '控制限制标志 (Ctl. Lim. 1=是, 0=否)',
    `notes`               varchar(255)   NULL DEFAULT NULL COMMENT '行备注',
    `order_num`           int(0)         NULL DEFAULT 0 COMMENT '显示排序号',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_report_detail_report` (`report_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '证书 - 内部报告明细表';

-- 16. 证书信息表 (hme_certificate_info)
-- 存储最终生成的成品证书信息
-- Finished Product Certificate(1).pdf: Certificati Barre
-- Old App Screenshot (1).pdf: Certification Creation, DOCUMENTO di CONTROLLO
DROP TABLE IF EXISTS `hme_certificate_info`;
CREATE TABLE `hme_certificate_info`
(
    `id`                  bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `certificate_no`      varchar(50)  NOT NULL COMMENT '证书号 (Documento Nr. / Certificato Nr.)',
    `dn_id`               bigint(0)    NOT NULL COMMENT '发货单ID (关联 hme_delivery_note.id)',
    `customer_id`         bigint(0)    NOT NULL COMMENT '客户ID (关联 hme_customer_info.id)',
    `certificate_type`    char(1)      NOT NULL COMMENT '证书类型 (1=3.1, 2=2.2)',
    `generation_date`     datetime(0)  NOT NULL COMMENT '生成日期',
    `language`            varchar(10)  NULL DEFAULT 'it' COMMENT '证书语言 (it, en)',
    `pdf_file_id`         bigint(0)    NULL DEFAULT NULL COMMENT 'PDF文件ID (关联系统文件表)',
    `pdf_file_name`       varchar(255) NULL DEFAULT NULL COMMENT 'PDF文件名',
    `email_status`        char(1)      NULL DEFAULT '0' COMMENT '邮件发送状态 (0=未发送, 1=已发送, 2=发送失败)',
    `email_sent_time`     datetime(0)  NULL DEFAULT NULL COMMENT '邮件发送时间',
    `traceability_info`   varchar(255) NULL DEFAULT NULL COMMENT '可追溯性信息 (Rintracciabilità)',
    `aggregation_info`    varchar(255) NULL DEFAULT NULL COMMENT '聚合信息 (Aggregazione)',
    `version`             int(0)       NULL DEFAULT 0 COMMENT '版本号',
    `create_dept`         bigint(0)    NULL DEFAULT NULL COMMENT '创建部门',
    `create_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`           bigint(0)    NULL DEFAULT NULL COMMENT '创建人',
    `update_time`         datetime(0)  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`           bigint(0)    NULL DEFAULT NULL COMMENT '更新人',
    `del_flag`            char(1)      NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
    `remark`              text         NULL DEFAULT NULL COMMENT '证书备注 (Note)',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_certificate_no` (`certificate_no`) USING BTREE,
    INDEX `idx_cert_dn` (`dn_id`) USING BTREE,
    INDEX `idx_cert_customer` (`customer_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '证书 - 证书信息表';

-- 17. 证书明细表 (hme_certificate_detail)
-- 存储证书上显示的化学和机械测试项及其值
DROP TABLE IF EXISTS `hme_certificate_detail`;
CREATE TABLE `hme_certificate_detail`
(
    `id`                  bigint(0)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `certificate_id`      bigint(0)    NOT NULL COMMENT '证书ID (关联 hme_certificate_info.id)',
    `item_id`             bigint(0)    NOT NULL COMMENT '测试项目ID (关联 hme_test_item.id)',
    `item_type`           char(1)      NOT NULL COMMENT '项目类型 (1=化学, 2=机械, 3=尺寸)',
    `display_value`       varchar(100) NOT NULL COMMENT '显示值 (可能是平均值或具体值, 或Rem.)',
    `min_limit_display`   varchar(50)  NULL DEFAULT NULL COMMENT '最小限值 (证书显示用)',
    `max_limit_display`   varchar(50)  NULL DEFAULT NULL COMMENT '最大限值 (证书显示用)',
    `source_analysis_no`  varchar(50)  NULL DEFAULT NULL COMMENT '源分析号 (3.1证书)',
    `source_report_no`    varchar(50)  NULL DEFAULT NULL COMMENT '源内部报告号 (3.1证书)',
    `avg_data_source_desc` text        NULL DEFAULT NULL COMMENT '平均值数据源描述 (2.2证书)',
    `order_num`           int(0)       NULL DEFAULT 0 COMMENT '显示排序号',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_cert_detail_cert` (`certificate_id`) USING BTREE
) ENGINE = InnoDB COMMENT = '证书 - 证书明细表';

-- 18. 拣配清单信息表 (hme_picking_list_info)
-- 存储与证书一起生成的拣配清单(装箱单)信息
-- Finished Product Certificate(1).pdf: Distinta del materiale certificato
DROP TABLE IF EXISTS `hme_picking_list_info`;
CREATE TABLE `hme_picking_list_info` (
                                         `id`                    bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                         `certificate_id`        bigint(0) NOT NULL COMMENT '关联的证书ID (hme_certificate_info.id)',
                                         `picking_list_no`       varchar(50) NOT NULL COMMENT '拣配清单号 (可与证书号一致或独立生成)',
                                         `generation_date`       datetime(0) NOT NULL COMMENT '生成日期',
                                         `version`               int(0) NULL DEFAULT 0 COMMENT '版本号',
                                         `create_dept`           bigint(0) NULL DEFAULT NULL COMMENT '创建部门',
                                         `create_time`           datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `create_by`             bigint(0) NULL DEFAULT NULL COMMENT '创建人',
                                         `update_time`           datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                         `update_by`             bigint(0) NULL DEFAULT NULL COMMENT '更新人',
                                         `del_flag`              char(1) NULL DEFAULT '0' COMMENT '删除标志 (0=存在 1=删除)',
                                         `remark`                varchar(500) NULL DEFAULT NULL COMMENT '备注',
                                         PRIMARY KEY (`id`) USING BTREE,
                                         UNIQUE INDEX `uk_picking_list_no` (`picking_list_no`) USING BTREE,
                                         INDEX `idx_picking_list_cert` (`certificate_id`) USING BTREE
) ENGINE=InnoDB COMMENT='证书 - 拣配清单信息表';

-- 19. 拣配清单明细表 (hme_picking_list_detail)
-- 存储拣配清单中的物料批次详情
DROP TABLE IF EXISTS `hme_picking_list_detail`;
CREATE TABLE `hme_picking_list_detail` (
                                           `id`                    bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                           `picking_list_id`       bigint(0) NOT NULL COMMENT '拣配清单ID (hme_picking_list_info.id)',
                                           `package_no`            varchar(50) NULL DEFAULT NULL COMMENT '包号/箱号 (Numero Collo)',
                                           `weight_kg`             decimal(10,3) NULL DEFAULT NULL COMMENT '重量 (KG) (Peso)',
                                           `prod_confirmation_no`  varchar(50) NULL DEFAULT NULL COMMENT '生产确认号 (Conferma di Produzione)',
                                           `processing_no`         varchar(50) NULL DEFAULT NULL COMMENT '加工号/工作订单号 (Numero di Lavorazione)',
                                           `batch_no`              varchar(50) NULL DEFAULT NULL COMMENT '实际批次号 (如果与生产订单批次不同)',
                                           PRIMARY KEY (`id`) USING BTREE,
                                           INDEX `idx_picking_detail_list` (`picking_list_id`) USING BTREE
) ENGINE=InnoDB COMMENT='证书 - 拣配清单明细表';

