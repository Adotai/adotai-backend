package com.adotai.backend_adotai.dto.User.response;

import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.entitiy.Address;

public record ResponseUserDTO(int id,
                              String name,
                              String cpf,
                              String email,
                              String telephone,
                              String role,
                              ResponseAddressDTO address
                              ) {}
