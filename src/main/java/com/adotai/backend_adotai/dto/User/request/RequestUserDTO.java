package com.adotai.backend_adotai.dto.User.request;

public record RequestUserDTO(String name,
                             String cpf,
                             String email,
                             String password,
                             String telephone,
                             String role,
                             int addressId){
}

