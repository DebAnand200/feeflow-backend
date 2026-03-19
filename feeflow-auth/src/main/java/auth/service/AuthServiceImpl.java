package auth.service;


import auth.repository.UserRepository;
import common.auth.AuthResponse;
import common.auth.RegisterRequest;
import common.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

//    @Override
//    public AuthResponse register(RegisterRequest request) {
//
//        User user = new User();
//
//        user.setPublicId(UUID.randomUUID().toString());
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
//
//        userRepository.save(user);
//
//        String token = jwtService.generateToken(user.getPublicId());
//
//        return AuthResponse.builder()
//                .userId(user.getPublicId())
//                .name(user.getName())
//                .email(user.getEmail())
//                .token(token)
//                .build();
//    }
//
//    @Override
//    public AuthResponse login(LoginRequest request) {
//
//        User user = userRepository
//                .findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        String token = jwtService.generateToken(user.getPublicId());
//
//        return AuthResponse.builder()
//                .userId(user.getPublicId())
//                .name(user.getName())
//                .email(user.getEmail())
//                .token(token)
//                .build();
//    }
//
//    @Override
//    public AuthResponse getCurrentUser() {
//
//        String userId = jwtService.getCurrentUserId();
//
//        User user = userRepository
//                .findByPublicId(userId)
//                .orElseThrow();
//
//        return AuthResponse.builder()
//                .userId(user.getPublicId())
//                .name(user.getName())
//                .email(user.getEmail())
//                .build();
//    }
//
//    @Override
//    public String logout() {
//        return "Logged out successfully";
//    }
}