package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.login.request.RequestLoginDTO;
import com.adotai.backend_adotai.dto.login.response.ResponseLoginDTO;
import com.adotai.backend_adotai.entity.User;
import com.adotai.backend_adotai.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public ResponseApi login(RequestLoginDTO dto) {
        Optional<User> userOptional = userRepository.findByEmail(dto.email());

        if (userOptional.isEmpty()) {
            return ResponseApi.error(401,"User not found");
        }
        User user = userOptional.get();

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            return ResponseApi.error(401,"Invalid login");
        }

        var now = Instant.now();
        var claims = JwtClaimsSet.builder()
                .issuer("adotai")
                .subject(user.getEmail())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600)) // 1 hora
                .claim("role", user.getRole())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseApi.success("Success",new ResponseLoginDTO(token));
    }
}
