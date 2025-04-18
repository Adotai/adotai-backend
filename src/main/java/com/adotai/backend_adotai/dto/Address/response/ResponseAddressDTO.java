package com.adotai.backend_adotai.dto.response;

import com.adotai.backend_adotai.entitiy.enum_types.States;

public record ResponseAddressDTO(
    Long id,
    String street,
    int number,
    String city,
    States state,
    String zipCode
) {}
