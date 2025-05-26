package com.adotai.backend_adotai.mapper.PhotosMapper;

import com.adotai.backend_adotai.dto.Ong.Request.RequestOngPhotosDTO;
import com.adotai.backend_adotai.dto.Ong.Response.ResponseOngPhotosDTO;
import com.adotai.backend_adotai.dto.Ong.UpdateOngPhotosDTO;
import com.adotai.backend_adotai.entity.Ong;
import com.adotai.backend_adotai.entity.PhotosEntities.OngPhotos;

public class OngPhotosMapper {

    public static OngPhotos toEntity(RequestOngPhotosDTO dto, Ong ong) {
        return new OngPhotos(ong, dto.getPhotoUrl());
    }

    public static OngPhotos toEntity(UpdateOngPhotosDTO dto) {
        return new OngPhotos(dto.id(),dto.photoUrl());
    }

    public static ResponseOngPhotosDTO toDto(OngPhotos ongPhotos) {
        return new ResponseOngPhotosDTO(
                ongPhotos.getId(),
                ongPhotos.getPhotoUrl(),
                ongPhotos.getOng().getId()
        );
    }
}
