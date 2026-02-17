package com.fast.server.config;

import com.fast.server.annotation.dict.Dictionary;
import com.fast.server.dto.SystemDict;
import com.fast.server.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

import java.lang.reflect.Field;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class DictConfig {

    private final DictionaryService dictionaryService;
    private final ConversionService conversionService;

    @Bean
    public SystemDict systemDict() {
        SystemDict systemDict = new SystemDict();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        typeConverter.setConversionService(this.conversionService);
        Class<SystemDict> dictClass = SystemDict.class;
        Field[] fields = dictClass.getDeclaredFields();
        for (Field f : fields) {
            // 设置私有字段操作权限
            f.setAccessible(true);
            Dictionary dictAnnotation = f.getAnnotation(Dictionary.class);
            if (dictAnnotation == null)
                continue;
            Object val = this.dictionaryService.set(dictAnnotation.key(), dictAnnotation.defaultVal());
            try {
                f.set(systemDict, typeConverter.convertIfNecessary(val, f.getType()));
            }
            catch (Exception e) {
                log.error("系统字典初始化失败");
                log.error(e.getLocalizedMessage());
            }
        }
        log.info("系统字典初始化成功");
        return systemDict;
    }
}
