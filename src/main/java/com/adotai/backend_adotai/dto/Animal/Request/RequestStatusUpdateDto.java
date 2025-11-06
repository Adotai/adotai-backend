package com.adotai.backend_adotai.dto.Animal.Request;

public record RequestStatusUpdateDto(
        Boolean status,
        Boolean solicitationStatus
) {
}
