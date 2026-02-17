package com.fast.server.dto;

import com.fast.server.annotation.dict.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemDict {

    @Dictionary(key = "DEBUG_MODE", defaultVal = "false")
    private Boolean debugMode;

    @Dictionary(key = "NEW_USER_ROLE", defaultVal = "2023039982976638977")
    private Long newUserRole;

    @Dictionary(key = "CAPTCHA_EXPIRE_TIME", defaultVal = "300")
    private Long captchaExpireTime;

    @Dictionary(key = "LOGIN_TOKEN_EXPIRE_TIME", defaultVal = "604800")
    private Long loginTokenExpireTime;

}
