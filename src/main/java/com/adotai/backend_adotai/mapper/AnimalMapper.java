package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Animal.Request.RequestAnimalDto;
import com.adotai.backend_adotai.dto.Animal.Response.ResponseAnimalDto;
import com.adotai.backend_adotai.dto.Animal.Response.ResponseAnimalPhotosDTO;
import com.adotai.backend_adotai.dto.Ong.Response.ResponseOngPhotosDTO;
import com.adotai.backend_adotai.entity.*;
import com.adotai.backend_adotai.entity.PhotosEntities.AnimalPhotos;
import com.adotai.backend_adotai.mapper.PhotosMapper.AnimalPhotosMapper;
import com.adotai.backend_adotai.mapper.PhotosMapper.OngPhotosMapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalMapper {
public static Animal toEntity(RequestAnimalDto dto, Ong ong, Color color, Breed breed, Specie specie, Timestamp createdAt) {

    List<AnimalPhotos> photos = dto.photos().stream()
            .map(photoDto -> AnimalPhotosMapper.toEntity(photoDto, null)) // O animal será associada posteriormente
            .collect(Collectors.toList());

    Animal animal = new Animal(
            ong,
            dto.name(),
            dto.gender(),
            color,
            breed,
            specie,
            dto.age(),
            dto.health(),
            dto.status(),
            dto.vaccinated(),
            dto.neutered(),
            dto.dewormed(),
            dto.temperament(),
            createdAt,
            photos,
            dto.animalDescription()
    );

    photos.forEach(photo -> photo.setAnimal(animal));

    return animal;
}

public static ResponseAnimalDto toDto(Animal animal) {

    List<ResponseAnimalPhotosDTO> photosDto = animal.getPhotos().stream()
            .map(AnimalPhotosMapper::toDto)
            .toList();

    return new ResponseAnimalDto(
            animal.getId(),
            animal.getOng().getId(),
            animal.getName(),
            animal.getGender(),
            animal.getColor().getName(),
            animal.getBreed().getName(),
            animal.getSpecies().getDescription(),
            animal.getAge(),
            animal.getHealth(),
            animal.isStatus(),
            animal.isVaccinated(),
            animal.isNeutered(),
            animal.isDewormed(),
            animal.getTemperament(),
            animal.getCreatedAt(),
            photosDto,
            animal.getAnimalDescription()
    );
}
}
