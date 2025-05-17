package learningapp.Config;

import learningapp.Services.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        String secret = "your-256-bit-secret-key-must-be-long-enough";
        long accessTokenExpiration = 3600000;        // 1 giờ
        long refreshTokenExpiration = 604800000;     // 7 ngày

        return new JwtTokenProvider(secret, accessTokenExpiration, refreshTokenExpiration);
    }
}
