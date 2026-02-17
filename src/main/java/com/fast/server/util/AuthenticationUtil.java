package com.fast.server.util;

import com.fast.server.dto.UserInfoResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 *
 * Author: []
 * Date: 2026/2/17
 */
public class AuthenticationUtil {
    public static UserInfoResponse current() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (UserInfoResponse) authentication.getPrincipal();
        }
        return null;
    }
}
