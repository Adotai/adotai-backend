package com.adotai.backend_adotai.dto.request;

import com.adotai.backend_adotai.entitiy.enum_types.States;

public record RequestAddressDTO(
        String street,
        int number,
        String city,
        States state,
        String zipCode
) {}
