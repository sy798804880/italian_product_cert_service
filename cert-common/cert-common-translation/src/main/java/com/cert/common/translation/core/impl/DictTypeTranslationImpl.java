package com.cert.common.translation.core.impl;

import com.cert.common.core.service.DictService;
import com.cert.common.core.utils.StringUtils;
import com.cert.common.translation.annotation.TranslationType;
import com.cert.common.translation.constant.TransConstant;
import com.cert.common.translation.core.TranslationInterface;
import lombok.AllArgsConstructor;

/**
 * 字典翻译实现
 *
 * @author Lion Li
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.DICT_TYPE_TO_LABEL)
public class DictTypeTranslationImpl implements TranslationInterface<String> {

    private final DictService dictService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof String dictValue && StringUtils.isNotBlank(other)) {
            return dictService.getDictLabel(other, dictValue);
        }
        return null;
    }
}
