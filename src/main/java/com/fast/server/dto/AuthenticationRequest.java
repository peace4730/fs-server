package com.fast.server.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AuthenticationRequest extends CaptchaRequest {
    @NotNull(message = "用户名不得为空")
    @Length(min = 6, max = 20, message = "用户名的长度在6-20个字符之间")
    String username;
    @NotNull(message = "密码不得为空")
    @Length(min = 8, max = 16, message = "密码的长度在8-16个字符之间")
    String password;
}
