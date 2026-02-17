package com.fast.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fast.server.dto.*;
import com.fast.server.entity.User;
import com.fast.server.exception.*;
import com.fast.server.mapper.UserMapper;
import com.fast.server.service.AuthenticationService;
import com.fast.server.service.CaptchaService;
import com.fast.server.util.impl.SystemUserJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
@Service
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationService implements AuthenticationService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final SystemDict systemDict;
    private final CaptchaService captchaService;
    private final SystemUserJwtUtil systemUserJwtUtil;

    @Override
    public void register(AuthenticationRequest authenticationRequest)
            throws UsernameExistException, CharacterInvalidException, CaptchaIncorrectException {
        // 校验验证码
        if (!authenticationRequest.validate(this.captchaService)) {
            throw new CaptchaIncorrectException();
        }
        // 用户名密码字符集合判断是否为数字+字母的组合
        if (
                !this.checkStrIsDigitAndLetter(authenticationRequest.getUsername())
                || !this.checkStrIsDigitAndLetter(authenticationRequest.getPassword())
        ) {
            throw new CharacterInvalidException();
        }
        // 查找用户名是否存在
        User existUser = this.userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUsername, authenticationRequest.getUsername())
        );
        if (existUser != null) {
            throw new UsernameExistException();
        }
        // 新用户，密码进行加密
        User newUser = new User(
                null,
                authenticationRequest.getUsername(),
                this.passwordEncoder.encode(authenticationRequest.getPassword()),
                null,
                systemDict.getNewUserRole(),
                null
        );
        this.userMapper.insert(newUser);
    }

    @Override
    public Token login(AuthenticationRequest authenticationRequest)
            throws UsernameNotExistException, PasswordIncorrectException, CaptchaIncorrectException {
        // 校验验证码
        if (!authenticationRequest.validate(this.captchaService)) {
            throw new CaptchaIncorrectException();
        }
        SystemUser systemUser = this.userMapper.selectOneByUsername(authenticationRequest.getUsername());
        if (systemUser == null)
            throw new UsernameNotExistException();
        if (!this.passwordEncoder.matches(authenticationRequest.getPassword(), systemUser.getPassword()))
            throw new PasswordIncorrectException();
        return this.systemUserJwtUtil.generate(systemUser, this.systemDict.getLoginTokenExpireTime());
    }

    @Override
    public CaptchaResponse generateCaptcha() {
        return this.captchaService.generate();
    }

    private Boolean checkStrIsDigitAndLetter(String str) {
        return str.chars().allMatch(c -> Character.isDigit(c) || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
    }
}
