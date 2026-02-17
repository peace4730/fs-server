package com.fast.server.exception;

/**
 *
 *
 * Author: []
 * Date: 2026/2/17
 */
public class CaptchaIncorrectException extends BaseException{
    public CaptchaIncorrectException() {
        super("验证码输入错误");
    }
}
