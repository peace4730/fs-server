package com.fast.server.controller;

import com.fast.server.controller.base.BaseController;
import com.fast.server.dto.AjaxResult;
import com.fast.server.dto.AuthenticationRequest;
import com.fast.server.service.AuthenticationService;
import com.fast.server.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *
 *
 * Author: []
 * Date: 2026/2/15
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController extends BaseController {
    private final AuthenticationService authenticationService;

    @GetMapping("/captcha")
    public AjaxResult captcha() {
        return this.success(this.authenticationService.generateCaptcha());
    }

    @PostMapping("/register")
    public AjaxResult register(@RequestBody AuthenticationRequest authenticationRequest) {
        this.authenticationService.register(authenticationRequest);
        return this.success();
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody AuthenticationRequest authenticationRequest) {
        return this.success(this.authenticationService.login(authenticationRequest));
    }

    @GetMapping("/info")
    @PreAuthorize("isAuthenticated()")
    public AjaxResult info() {
        return this.success(AuthenticationUtil.current());
    }

}
