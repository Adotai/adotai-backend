package com.adotai.backend_adotai.dto.User.response;

public record ResponseUserDTO(Long id,
                              String name,
                              String cpf,
                              String email,
                              String telephone,
                              String role,
                              String street,
                              String city,
                              String state,
                              String zipCode) {}
