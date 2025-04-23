package com.adotai.backend_adotai.dto.Address.response;

import com.adotai.backend_adotai.entitiy.enum_types.States;

public record ResponseAddressDTO(
    int id,
    String street,
    int number,
    String city,
    States state,
    String zipCode
) {}
