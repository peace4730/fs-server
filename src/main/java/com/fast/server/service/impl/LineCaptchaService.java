package com.fast.server.service.impl;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.fast.server.cache.CaptchaCache;
import com.fast.server.dto.Captcha;
import com.fast.server.dto.CaptchaResponse;
import com.fast.server.dto.SystemDict;
import com.fast.server.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 *
 *
 * Author: []
 * Date: 2026/2/16
 */
@Service
@RequiredArgsConstructor
public class LineCaptchaService implements CaptchaService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final SystemDict systemDict;

    @Override
    public CaptchaResponse generate() {
        LineCaptcha lineCaptcha = new LineCaptcha(200, 100);
        String sessionId = IdUtil.simpleUUID();
        Captcha captcha = new Captcha(sessionId, lineCaptcha.getImageBase64Data(), lineCaptcha.getCode());
        CaptchaCache cache = captcha.toCache();
        this.redisTemplate.opsForValue().set(this.buildSessionId(sessionId), cache, systemDict.getCaptchaExpireTime(), TimeUnit.SECONDS);
        return captcha.toResponse(systemDict.getCaptchaExpireTime());
    }

    @Override
    public Boolean validate(String sessionId, String code) {
        if (code.isBlank())
            return false;
        CaptchaCache cache = (CaptchaCache) this.redisTemplate.opsForValue().get(this.buildSessionId(sessionId));
        if (cache == null)
            return false;
        if (cache.getCode().equals(code)) {
            // 验证成功删除缓存
            this.redisTemplate.delete(this.buildSessionId(sessionId));
            return true;
        }
        else return false;
    }

    private String buildSessionId(String sessionId) {
        return "captcha:" + sessionId;
    }
}
