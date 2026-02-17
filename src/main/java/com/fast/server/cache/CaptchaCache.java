package com.fast.server.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 *
 * Author: []
 * Date: 2026/2/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaCache implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    String sessionId;
    String code;
}
