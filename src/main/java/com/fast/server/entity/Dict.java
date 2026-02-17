package com.fast.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Dict {
    @TableId(type = IdType.INPUT)
    String name;
    Object val;
}
