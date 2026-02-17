package com.fast.server.exception;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
public class UsernameNotExistException extends BaseException{
    public UsernameNotExistException() {
        super("用户名不存在");
    }
}
