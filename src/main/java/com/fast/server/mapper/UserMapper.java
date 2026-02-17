package com.fast.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fast.server.dto.SystemUser;
import com.fast.server.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    SystemUser selectOneByUsername(String username);
}
