package com.fast.server.exception;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
public class UsernameExistException extends BaseException{
    public UsernameExistException() {
        super("用户名已经存在");
    }
}
