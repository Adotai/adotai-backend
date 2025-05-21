package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.dto.Ong.Request.RequestOngDTO;
import com.adotai.backend_adotai.dto.Ong.Response.ResponseOngDTO;
import com.adotai.backend_adotai.dto.Ong.Response.ResponseOngPhotosDTO;
import com.adotai.backend_adotai.entity.Address;
import com.adotai.backend_adotai.entity.Documents;
import com.adotai.backend_adotai.entity.Ong;
import com.adotai.backend_adotai.entity.PhotosEntities.OngPhotos;
import com.adotai.backend_adotai.mapper.PhotosMapper.OngPhotosMapper;
import com.adotai.backend_adotai.util.ValidationUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OngMapper {

    public static Ong toEntity(RequestOngDTO dto, Address address, String encondedPassword){

        List<OngPhotos> photos = dto.photos().stream()
                .map(photoDto -> OngPhotosMapper.toEntity(photoDto, null)) // A ONG será associada posteriormente
                .collect(Collectors.toList());


        Ong ong = new Ong(
                dto.name(),
                ValidationUtils.formatStrNumber(dto.phone()),
                dto.pix(),
                ValidationUtils.formatStrNumber(dto.cnpj()),
                dto.email(),
                encondedPassword,
                dto.documents().getSocialStatute(),
                dto.documents().getBoardMeeting(),
                address,
                dto.status(),
                dto.description(),
                photos

        );

        photos.forEach(photo -> photo.setOng(ong));

        return ong;
    }

    public static ResponseOngDTO toDto(Ong ong){
        Address address = ong.getAddress();
        ResponseAddressDTO addressDto = address == null ? null : AddressMapper.toDto(address);
        Documents documents = new Documents(ong.getBoardMeeting(), ong.getSocialStatute());

        List<ResponseOngPhotosDTO> photosDto = ong.getPhotos().stream()
                .map(OngPhotosMapper::toDto)
                .toList();

        return new ResponseOngDTO(
                ong.getId(),
                ong.getName(),
                ong.getPhone(),
                ong.getCnpj(),
                ong.getEmail(),
                ong.getPix(),
                documents,
                addressDto,
                photosDto,
                ong.getDescription(),
                ong.isStatus());
    }
}
