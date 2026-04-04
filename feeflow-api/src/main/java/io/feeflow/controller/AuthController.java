package io.feeflow.controller;

import auth.service.AuthService;
import common.dto.auth.AuthResponse;
import common.dto.auth.LoginRequest;
import common.dto.auth.ForgotPasswordRequest;
import common.dto.auth.ResetPasswordRequest;
import common.dto.auth.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public AuthResponse me() {
        return authService.getCurrentUser();
    }

    @PostMapping("/logout")
    public String logout() {
        return authService.logout();
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(
            @RequestBody common.dto.auth.ForgotPasswordRequest request
    ) {
        return authService.forgotPassword(request.getEmail());
    }

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestBody common.dto.auth.ResetPasswordRequest request
    ) {
        return authService.resetPassword(request);
    }
}
