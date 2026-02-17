package com.fast.server.dto;

import com.fast.server.service.CaptchaService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * Author: []
 * Date: 2026/2/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaRequest {
    @NotNull(message = "验证码不得为空")
    String captchaCode;
    @NotNull(message = "会话ID不得为空")
    String sessionId;

    public Boolean validate(CaptchaService captchaService) {
        return captchaService.validate(this.sessionId, this.captchaCode);
    }
}
