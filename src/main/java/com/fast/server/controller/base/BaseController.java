package com.fast.server.controller.base;

import com.fast.server.dto.AjaxResult;

import java.util.ArrayList;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
public abstract class BaseController {
    protected AjaxResult success() {
        return success(new ArrayList<>());
    }

    protected AjaxResult success(Object data) {
        return new AjaxResult(0, "", data);
    }

    protected AjaxResult error(Integer code, String msg) {
        return new AjaxResult(code, msg, new ArrayList<>());
    }
}
