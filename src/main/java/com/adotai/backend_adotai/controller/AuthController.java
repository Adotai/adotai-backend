package com.adotai.backend_adotai.controller;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.login.request.RequestLoginDTO;
import com.adotai.backend_adotai.dto.login.response.ResponseLoginDTO;
import com.adotai.backend_adotai.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<ResponseApi<?>> login(@RequestBody RequestLoginDTO dto) {
        ResponseApi<?> response = authService.login(dto);
        return ResponseEntity.status(response.status()).body(response);
    }
}
