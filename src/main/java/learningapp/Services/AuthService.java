package learningapp.Services;

import learningapp.Model.RefreshToken;
import learningapp.Model.User;
import learningapp.Repository.IRefreshTokenRepository;
import learningapp.Repository.IUserRepository;
import learningapp.dto.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired private IUserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtTokenProvider jwtProvider;
    @Autowired private IRefreshTokenRepository refreshTokenRepo;

    public User register(String username, String email, String rawPassword) {
        String hash = encoder.encode(rawPassword);
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setUserpassword(hash);
        u.setRole(Role.USER);
        return userRepo.save(u);
    }
    public Map<String, String>  login(String username, String rawPassword) {
        User u = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!encoder.matches(rawPassword, u.getUserpassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        // Generate tokens
        String accessToken = jwtProvider.generateAccessToken(u.getId(), u.getRole().name());
        String refreshToken = jwtProvider.generateRefreshToken(u.getId());

        // Save refresh token to database
        RefreshToken rt = refreshTokenRepo.findByUserId(u.getId()).orElse(new RefreshToken());
        rt.setUserId(u.getId());
        rt.setToken(refreshToken);
        rt.setIssuedAt(Instant.now());
        rt.setExpiresAt(Instant.now().plusMillis(jwtProvider.getRefreshTokenExpiration()));
        refreshTokenRepo.save(rt);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }
    public void logout(Long userId) {
        refreshTokenRepo.deleteByUserId(userId);
    }
    public Map<String, String> refreshToken(String token) {
        RefreshToken storedToken = refreshTokenRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (storedToken.getExpiresAt().isBefore(Instant.now())) {
            refreshTokenRepo.delete(storedToken); // Optional: delete expired token
            throw new RuntimeException("Refresh token expired");
        }

        User user = userRepo.findById(storedToken.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newAccessToken = jwtProvider.generateAccessToken(user.getId(), user.getRole().name());
        String newRefreshToken = jwtProvider.generateRefreshToken(user.getId());

        // Update and save new refresh token
        storedToken.setToken(newRefreshToken);
        storedToken.setIssuedAt(Instant.now());
        storedToken.setExpiresAt(Instant.now().plusMillis(jwtProvider.getRefreshTokenExpiration()));
        refreshTokenRepo.save(storedToken);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", newAccessToken);
        tokens.put("refreshToken", newRefreshToken);
        return tokens;
    }
}
