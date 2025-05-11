package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Address.request.RequestAddressDTO;
import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.dto.Specie.Request.RequestSpecieDto;
import com.adotai.backend_adotai.dto.Specie.Response.ResponseSpecieDto;
import com.adotai.backend_adotai.entity.Address;
import com.adotai.backend_adotai.entity.Specie;

public class SpecieMapper {
    public static Specie toEntity(RequestSpecieDto requestSpecieDto){
        return  new Specie(requestSpecieDto.description(), requestSpecieDto.status());
    }

    public static ResponseSpecieDto toDto(Specie specie){
        return new ResponseSpecieDto(
                specie.getId(),
                specie.getDescription()
        );
    }
}

