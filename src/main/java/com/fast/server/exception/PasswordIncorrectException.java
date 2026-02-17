package com.fast.server.exception;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
public class PasswordIncorrectException extends BaseException{
    public PasswordIncorrectException() {
        super("密码输入错误");
    }
}
