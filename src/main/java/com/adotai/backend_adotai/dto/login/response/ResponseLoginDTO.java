package com.adotai.backend_adotai.dto.login.response;

import com.adotai.backend_adotai.entity.enum_types.Role;

public record ResponseLoginDTO(String token, Role role) {
}
