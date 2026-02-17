package com.fast.server.service.impl;

import com.fast.server.dto.SystemUser;
import com.fast.server.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 *
 *
 * Author: []
 * Date: 2026/2/17
 */
@Service
@RequiredArgsConstructor
public class SystemUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        SystemUser systemUser = this.userMapper.selectOneByUsername(username);
        if (systemUser == null)
            throw new UsernameNotFoundException("用户名不存在");
        return new User(
                systemUser.getUsername(),
                systemUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(systemUser.getRole()))
        );
    }
}
