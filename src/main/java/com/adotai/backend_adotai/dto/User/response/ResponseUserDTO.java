package com.adotai.backend_adotai.dto.User.response;

import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.entity.enum_types.Gender;
import com.adotai.backend_adotai.entity.enum_types.HouseSize;
import com.adotai.backend_adotai.entity.enum_types.HouseType;

import java.util.Date;
import java.util.List;

public record ResponseUserDTO(int id,
                              String name,
                              String cpf,
                              String email,
                              String telephone,
                              String role,
                              ResponseAddressDTO address,
                              String description,
                              Date birthDate,
                              Gender gender,
                              String animalsQuantity,
                              HouseType houseType,
                              HouseSize houseSize,
                              List<ResponseUserPhotosDTO> photos
                              ) {}
