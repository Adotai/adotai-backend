package com.adotai.backend_adotai.dto.User;

import com.adotai.backend_adotai.entity.Address;

public record UpdateUserDto(
        int id,
        String name,
        String cpf,
        String email,
        String password,
        String telephone,
        Address address,
        int addressId
) {
}
