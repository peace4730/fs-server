package com.fast.server.dto;

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
public class UserInfoResponse {
    Long id;
    String username;
    String avatar;
    String role;
}
