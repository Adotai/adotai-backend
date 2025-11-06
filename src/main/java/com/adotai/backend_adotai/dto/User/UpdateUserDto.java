package com.adotai.backend_adotai.dto.User;

import com.adotai.backend_adotai.entity.Address;
import com.adotai.backend_adotai.entity.enum_types.Gender;
import com.adotai.backend_adotai.entity.enum_types.HouseSize;
import com.adotai.backend_adotai.entity.enum_types.HouseType;

public record UpdateUserDto(
        int id,
        String name,
        String cpf,
        String email,
        String password,
        String telephone,
        Address address,
        int addressId,
        String description,
        java.util.Date birthDate,
        Gender gender,
        String animalsQuantity,
        HouseType houseType,
        HouseSize houseSize
) {
}
