package com.adotai.backend_adotai.dto.User.request;

import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.entitiy.enum_types.Role;

public record RequestUserDTO(String name,
                             String cpf,
                             String email,
                             String password,
                             String telephone,
                             Role role,
                             Long addressId){}

