package auth.security;

import auth.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secretKey", "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970");
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 3600000L); // 1 hour

        User user = User.builder()
                .id(1)
                .email("test@example.com")
                .passwordHash("password")
                .build();
        
        customUserDetails = new CustomUserDetails(user);
    }

    @Test
    void generateToken_Success() {
        String token = jwtService.generateToken(customUserDetails);
        
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void extractUsername_ReturnsCorrectUsername() {
        String token = jwtService.generateToken(customUserDetails);
        
        String username = jwtService.extractUsername(token);
        
        assertEquals("test@example.com", username);
    }

    @Test
    void isTokenValid_ReturnsTrueForValidToken() {
        String token = jwtService.generateToken(customUserDetails);
        
        boolean isValid = jwtService.isTokenValid(token, customUserDetails);
        
        assertTrue(isValid);
    }
    
    @Test
    void isTokenValid_ReturnsFalseForInvalidUser() {
        String token = jwtService.generateToken(customUserDetails);
        
        User otherUser = User.builder()
                .email("wrong@example.com")
                .build();
        CustomUserDetails otherUserDetails = new CustomUserDetails(otherUser);
        
        boolean isValid = jwtService.isTokenValid(token, otherUserDetails);
        
        assertFalse(isValid);
    }

}
