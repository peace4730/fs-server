package com.fast.server.util;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import com.fast.server.dto.Expires;
import lombok.Setter;

import java.util.Map;

/**
 *
 *
 * Author: []
 * Date: 2026/2/17
 */
public abstract class JwtUtil {
    @Setter
    private String key;

    protected String generate(Map<String, String> payload, Expires expires) {
        JWT jwt = JWT.create();
        payload.forEach(jwt::setPayload);
        jwt
                .setIssuedAt(expires.getCreateTime())
                .setExpiresAt(expires.getExpirationTime())
        ;
        jwt.setKey(key.getBytes());
        return jwt.sign();
    }

    public JSONObject parse(String token) {
        try {
            JWT jwt = JWT.of(token);
            return jwt.getPayloads();
        }
        catch (Exception e) {
            return null;
        }
    }

    public Boolean validate(String token) {
        try {
            return JWT.of(token).setKey(this.key.getBytes()).validate(0);
        }
        catch (Exception e) {
            return false;
        }
    }

}
