package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Breed.Request.RequestBreedDto;
import com.adotai.backend_adotai.dto.Breed.Response.ResponseBreedDto;
import com.adotai.backend_adotai.entity.Breed;
import com.adotai.backend_adotai.entity.Specie;

public class BreedMapper {
    public static Breed toEntity(RequestBreedDto dto, Specie specie) {
        return new Breed(specie, dto.name());
    }

    public static ResponseBreedDto toResponseDto(Breed breed) {
        return new ResponseBreedDto(
                breed.getId(),
                breed.getName(),
                breed.getSpecies().getDescription()
        );
    }
}
