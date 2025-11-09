package com.adotai.backend_adotai.mapper.PhotosMapper;

import com.adotai.backend_adotai.dto.User.UpdateUserPhotosDTO;
import com.adotai.backend_adotai.dto.User.request.RequestUserPhotosDTO;
import com.adotai.backend_adotai.dto.User.response.ResponseUserPhotosDTO;
import com.adotai.backend_adotai.entity.PhotosEntities.UserPhotos;
import com.adotai.backend_adotai.entity.User;

public class UserPhotosMapper {

    public static UserPhotos toEntity(RequestUserPhotosDTO dto, User user) {
        return new UserPhotos(user, dto.getPhotoUrl());
    }

    public static UserPhotos toEntity(UpdateUserPhotosDTO dto) {
        return new UserPhotos(dto.id(),dto.photoUrl());
    }

    public static ResponseUserPhotosDTO toDto(UserPhotos userPhotos) {
        return new ResponseUserPhotosDTO(
                userPhotos.getId(),
                userPhotos.getPhotoUrl(),
                userPhotos.getUser().getId()
        );
    }
}
