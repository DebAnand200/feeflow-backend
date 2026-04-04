package auth.service.email;

public interface EmailService {
    void sendOtp(String email, String otp);
}