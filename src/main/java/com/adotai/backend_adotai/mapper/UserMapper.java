package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.User.request.RequestUserDTO;
import com.adotai.backend_adotai.dto.User.response.ResponseUserDTO;
import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.entitiy.User;

public class UserMapper {

    public static User toEntity(RequestUserDTO dto, Address address, String encodedPassword) {
        return new User(
                dto.name(),
                dto.cpf(),
                dto.email(),
                dto.role() , // valor padrão
                encodedPassword,
                dto.telephone(),
                address
        );
    }

    public static ResponseUserDTO toDto(User user) {
        return new ResponseUserDTO(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getTelephone(),
                user.getRole().name(),
                user.getAddress().getStreet(),
                user.getAddress().getCity(),
                user.getAddress().getState().name(), // se for enum
                user.getAddress().getZipCode()
        );
    }
}
