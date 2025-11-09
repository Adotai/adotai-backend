package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.dto.User.request.RequestUserDTO;
import com.adotai.backend_adotai.dto.User.response.ResponseUserDTO;
import com.adotai.backend_adotai.dto.User.response.ResponseUserPhotosDTO;
import com.adotai.backend_adotai.entity.Address;
import com.adotai.backend_adotai.entity.PhotosEntities.OngPhotos;
import com.adotai.backend_adotai.entity.PhotosEntities.UserPhotos;
import com.adotai.backend_adotai.entity.User;
import com.adotai.backend_adotai.entity.enum_types.Role;
import com.adotai.backend_adotai.mapper.PhotosMapper.OngPhotosMapper;
import com.adotai.backend_adotai.mapper.PhotosMapper.UserPhotosMapper;
import com.adotai.backend_adotai.util.ValidationUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

        public static User toEntity(RequestUserDTO dto, Address address, String encodedPassword) {
            List<UserPhotos> photos = dto.photos().stream()
                    .map(photoDto -> UserPhotosMapper.toEntity(photoDto, null)) // O usuário será associado posteriormente
                    .collect(Collectors.toList());

            User user = new User(
                    dto.name(),
                    ValidationUtils.formatStrNumber(dto.cpf()),
                    dto.email(),
                    parseRole(dto.role()),
                    encodedPassword,
                    ValidationUtils.formatStrNumber(dto.telephone()),
                    address,
                    dto.description(),
                    dto.birthDate(),
                    dto.gender(),
                    dto.animalsQuantity(),
                    dto.houseType(),
                    dto.houseSize(),
                    photos
            );

            // associa o usuário em cada foto
            photos.forEach(photo -> photo.setUser(user));

            return user;
        }

    public static ResponseUserDTO toDto(User user) {
        Address address = user.getAddress();
        ResponseAddressDTO addressDto = address == null ? null : AddressMapper.toDto(address);

        List<ResponseUserPhotosDTO> photosDto = user.getPhotos().stream()
                .map(UserPhotosMapper::toDto)
                .collect(Collectors.toList());

        return new ResponseUserDTO(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail(),
                user.getTelephone(),
                user.getRole().name(),
                addressDto,
                user.getDescription(),
                user.getBirthDate(),
                user.getGender(),
                user.getAnimalsQuantity(),
                user.getHouseType(),
                user.getHouseSize(),
                photosDto
        );
    }

    public static Role parseRole(String role){
            if(role.trim().equalsIgnoreCase("admin")){
                return Role.admin;
            }else
                return Role.normal;
    }
}
