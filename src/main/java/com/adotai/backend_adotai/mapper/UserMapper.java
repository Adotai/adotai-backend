package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.dto.User.request.RequestUserDTO;
import com.adotai.backend_adotai.dto.User.response.ResponseUserDTO;
import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.entitiy.User;
import com.adotai.backend_adotai.entitiy.enum_types.Role;
import com.adotai.backend_adotai.util.ValidationUtils;

public class UserMapper {

        public static User toEntity(RequestUserDTO dto, Address address, String encodedPassword) {
            return new User(
                    dto.name(),
                    ValidationUtils.formatStrNumber(dto.cpf()),
                    dto.email(),
                    parseRole(dto.role()),
                    encodedPassword,
                    ValidationUtils.formatStrNumber(dto.telephone()),
                    address
            );
        }

    public static ResponseUserDTO toDto(User user) {
            Address address = user.getAddress();
            ResponseAddressDTO addressDto = address == null ? null : AddressMapper.toDto(address);
            return new ResponseUserDTO(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getTelephone(),
                user.getRole().name(),
                addressDto
        );
    }

    public static Role parseRole(String role){
            if(role.trim().equalsIgnoreCase("admin")){
                return Role.admin;
            }else
                return Role.normal;
    }
}
