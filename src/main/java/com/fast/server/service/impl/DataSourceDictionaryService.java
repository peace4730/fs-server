package com.fast.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fast.server.entity.Dict;
import com.fast.server.mapper.DictMapper;
import com.fast.server.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
@Service
@RequiredArgsConstructor
public class DataSourceDictionaryService implements DictionaryService {

    private final DictMapper dictMapper;

    @Override
    public Object set(String key, String defaultVal) {
        Dict dict = this.dictMapper.selectOne(new LambdaQueryWrapper<Dict>().eq(Dict::getName, key));
        if (dict == null)
            return defaultVal;
        return dict.getVal() != null ? dict.getVal() : defaultVal;
    }
}
