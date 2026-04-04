package auth.service;

import common.dto.auth.AuthResponse;
import common.dto.auth.LoginRequest;
import common.dto.auth.RegisterRequest;
import common.dto.auth.ResetPasswordRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse getCurrentUser();

    String logout();

    String forgotPassword(String email);
    String resetPassword(ResetPasswordRequest request);
}
