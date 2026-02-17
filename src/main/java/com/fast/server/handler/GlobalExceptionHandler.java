package com.fast.server.handler;

import com.fast.server.controller.base.BaseController;
import com.fast.server.dto.AjaxResult;
import com.fast.server.dto.SystemDict;
import com.fast.server.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 *
 *
 * Author: []
 * Date: 2026/2/16
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler extends BaseController {

    private final SystemDict systemDict;

    @ExceptionHandler(Exception.class)
    public AjaxResult exceptionHandler(Exception e) throws Exception {
        if (e instanceof BaseException) {
            throw e;
        }
        log.error(e.getLocalizedMessage());
        return !systemDict.getDebugMode()
                ? this.error(500, "服务器内部未知异常")
                : this.error(500, "服务器内部异常\n" + e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public AjaxResult baseExceptionHandler(BaseException e) {
        return this.error(1, e.getMessage());
    }

    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public AjaxResult noHandlerFoundHandler() {
        return this.error(404, "访问目标资源不存在");
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public AjaxResult accessDeniedHandler() {
        return this.error(403, "权限不足无法访问");
    }
}
