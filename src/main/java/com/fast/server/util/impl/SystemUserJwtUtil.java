package com.fast.server.util.impl;

import com.fast.server.annotation.Util;
import com.fast.server.dto.SystemUser;
import com.fast.server.dto.Token;
import com.fast.server.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Author: []
 * Date: 2026/2/17
 */
@Util
public class SystemUserJwtUtil extends JwtUtil {

    @Value("${fast.jwt.key}")
    private String jwtKey;

    @PostConstruct
    public void init() {
        super.setKey(this.jwtKey);
    }

    public Token generate(SystemUser systemUser, Long expiresIn) {
        Token token = new Token();
        token.build(expiresIn);
        Map<String, String> map = new HashMap<>();
        map.put("id", systemUser.getId().toString());
        map.put("username", systemUser.getUsername());
        map.put("avatar", systemUser.getAvatar());
        map.put("role", systemUser.getRole());
        token.setToken(super.generate(map, token));
        return token;
    }

}
