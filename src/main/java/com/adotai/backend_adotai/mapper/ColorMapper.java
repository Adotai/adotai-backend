package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.dto.Color.Request.RequestColorDto;
import com.adotai.backend_adotai.dto.Color.Response.ResponseColorDto;
import com.adotai.backend_adotai.entity.Address;
import com.adotai.backend_adotai.entity.Color;

public class ColorMapper {
    public static Color toEntity(RequestColorDto requestColorDto){
        return new Color(requestColorDto.name());
    }

    public static ResponseColorDto toDto(Color color){
        return new ResponseColorDto(
                    color.getId(),
                    color.getName()
        );
    }
}
