package com.adotai.backend_adotai.mapper.PhotosMapper;


import com.adotai.backend_adotai.dto.Animal.Request.RequestAnimalPhotosDTO;
import com.adotai.backend_adotai.dto.Animal.Response.ResponseAnimalPhotosDTO;
import com.adotai.backend_adotai.entity.Animal;
import com.adotai.backend_adotai.entity.PhotosEntities.AnimalPhotos;
import com.adotai.backend_adotai.entity.PhotosEntities.OngPhotos;

import java.util.List;
import java.util.stream.Collectors;

public class AnimalPhotosMapper {



    public static AnimalPhotos toEntity(RequestAnimalPhotosDTO dto, Animal animal) {
        return new AnimalPhotos(animal, dto.getPhotoUrl());
    }

    public static ResponseAnimalPhotosDTO toDto(AnimalPhotos animalPhotos) {
        return new ResponseAnimalPhotosDTO(
                animalPhotos.getId(),
                animalPhotos.getPhotoUrl(),
                animalPhotos.getAnimal().getId()
        );
    }

}
