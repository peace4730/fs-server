package com.fast.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 *
 * Author: []
 * Date: 2026/2/16
 */
@Data
@NoArgsConstructor
public abstract class Expires {
    Long expiresIn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date expirationTime;

    public Expires build(Long expiresIn) {
        this.expiresIn = expiresIn;
        this.createTime = new Date();
        this.expirationTime = Date.from(this.createTime.toInstant().plusSeconds(this.expiresIn));
        return this;
    }
}
