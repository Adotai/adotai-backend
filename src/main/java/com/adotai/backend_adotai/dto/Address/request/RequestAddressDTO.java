package com.adotai.backend_adotai.dto.Address.request;

import com.adotai.backend_adotai.entity.enum_types.States;

public record RequestAddressDTO(
        String street,
        int number,
        String city,
        States state,
        String zipCode
) {}
