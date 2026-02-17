package com.fast.server.dto;

import com.fast.server.cache.CaptchaCache;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * Author: []
 * Date: 2026/2/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Captcha {
    String sessionId;
    String image;
    String code;

    public CaptchaResponse toResponse(Long expires) {
        return (CaptchaResponse) new CaptchaResponse(this.sessionId, this.image).build(expires);
    }

    public CaptchaCache toCache() {
        return new CaptchaCache(this.sessionId, this.code);
    }
}
