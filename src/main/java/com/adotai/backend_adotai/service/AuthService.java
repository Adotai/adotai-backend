package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.login.request.RequestLoginDTO;
import com.adotai.backend_adotai.dto.login.response.ResponseLoginDTO;
import com.adotai.backend_adotai.entity.Ong;
import com.adotai.backend_adotai.entity.User;
import com.adotai.backend_adotai.entity.enum_types.Role;
import com.adotai.backend_adotai.repository.OngRepository;
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
    private final OngRepository ongRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public AuthService(UserRepository userRepository,OngRepository ongRepository ,BCryptPasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.ongRepository = ongRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public ResponseApi login(RequestLoginDTO dto) {
        Optional<User> userOptional = userRepository.findByEmail(dto.email());

        if (userOptional.isEmpty()) {
            Optional<Ong> ongOptional = ongRepository.findByEmail(dto.email());
            if(ongOptional.isEmpty()){
                return ResponseApi.error(401,"User not found");
            }
            Ong ong = ongOptional.get();

            if(!ong.isStatus()){
                return ResponseApi.error(401,"User don't have permission");
            }

            if (!passwordEncoder.matches(dto.password(), ong.getPassword())) {
                return ResponseApi.error(401, "Invalid login");
            }

            var now = Instant.now();
            var claims = JwtClaimsSet.builder()
                    .issuer("adotai")
                    .subject(ong.getEmail())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(3600)) // 1 hora
                    .claim("role", "ong")
                    .build();

            String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseApi.success("Success", new ResponseLoginDTO(token, Role.ong));
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

        return ResponseApi.success("Success",new ResponseLoginDTO(token, user.getRole()));
    }
}
