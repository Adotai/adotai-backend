package com.adotai.backend_adotai.dto.Address;

import com.adotai.backend_adotai.entity.enum_types.States;

public record UpdateAddressDto(
        int id,
        String street,
        int number,
        String city,
        States state,
        String zipCode
) {
}
