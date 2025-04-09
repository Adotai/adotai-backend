package com.adotai.backend_adotai.controller;

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
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody RequestLoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
