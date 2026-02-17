package com.fast.server.service;

import com.fast.server.dto.CaptchaResponse;

/**
 *
 *
 * Author: []
 * Date: 2026/2/16
 */
public interface CaptchaService {
    CaptchaResponse generate();

    Boolean validate(String sessionId, String code);
}
