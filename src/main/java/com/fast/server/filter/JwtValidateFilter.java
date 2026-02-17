package com.fast.server.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.fast.server.dto.UserInfoResponse;
import com.fast.server.util.impl.SystemUserJwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 *
 *
 * Author: []
 * Date: 2026/2/17
 */
@Component
@RequiredArgsConstructor
public class JwtValidateFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final SystemUserJwtUtil systemUserJwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        SecurityContext context = SecurityContextHolder.getContext();
        if (authorization != null && context.getAuthentication() == null && !authorization.isBlank()) {
            if (authorization.startsWith("Bearer")) {
                String token = StrUtil.removePrefix(authorization, "Bearer ");
                if (this.systemUserJwtUtil.validate(token)) {
                    JSONObject parseToken = this.systemUserJwtUtil.parse(token);
                    try {
                        String username = parseToken.getStr("username");
                        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                        context.setAuthentication(
                                new UsernamePasswordAuthenticationToken(
                                        parseToken.toBean(UserInfoResponse.class),
                                        null,
                                        userDetails.getAuthorities()
                                )
                        );
                    }
                    catch (UsernameNotFoundException ignore) {}
                }
            }
        }
        else {
            context.setAuthentication(new UsernamePasswordAuthenticationToken(null, null));
        }
        filterChain.doFilter(request, response);
    }
}
