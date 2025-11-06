package com.adotai.backend_adotai.dto.User.request;

import com.adotai.backend_adotai.entity.enum_types.Gender;
import com.adotai.backend_adotai.entity.enum_types.HouseSize;
import com.adotai.backend_adotai.entity.enum_types.HouseType;

import java.util.Date;

public record RequestUserDTO(String name,
                             String cpf,
                             String email,
                             String password,
                             String telephone,
                             String role,
                             int addressId,
                             String description,
                             Date birthDate,
                             Gender gender,
                             String animalsQuantity,
                             HouseType houseType,
                             HouseSize houseSize) {
}

