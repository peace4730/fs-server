package com.fast.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult {
    Integer code;
    String msg;
    Object data;
}
