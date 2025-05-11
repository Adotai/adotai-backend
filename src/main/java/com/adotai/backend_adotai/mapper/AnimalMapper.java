package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Animal.Request.RequestAnimalDto;
import com.adotai.backend_adotai.dto.Animal.Response.ResponseAnimalDto;
import com.adotai.backend_adotai.entity.*;

import java.sql.Timestamp;

public class AnimalMapper {
public static Animal toEntity(RequestAnimalDto dto, Ong ong, Color color, Breed breed, Specie specie, Timestamp createdAt) {
    return new Animal(
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
            createdAt
    );
}

public static ResponseAnimalDto toDto(Animal animal) {
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
            animal.getCreatedAt()
    );
}
}
