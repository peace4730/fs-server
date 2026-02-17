package com.fast.server.service;

import com.fast.server.dto.AuthenticationRequest;
import com.fast.server.dto.CaptchaResponse;
import com.fast.server.dto.Token;
import com.fast.server.exception.*;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
public interface AuthenticationService {
    void register(AuthenticationRequest authenticationRequest) throws UsernameExistException, CharacterInvalidException, CaptchaIncorrectException;

    Token login(AuthenticationRequest authenticationRequest) throws UsernameNotExistException, PasswordIncorrectException, CaptchaIncorrectException;

    CaptchaResponse generateCaptcha();
}
