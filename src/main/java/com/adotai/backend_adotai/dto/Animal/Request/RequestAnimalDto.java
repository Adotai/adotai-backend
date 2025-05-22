package com.adotai.backend_adotai.dto.Animal.Request;

import com.adotai.backend_adotai.dto.Ong.Request.RequestOngPhotosDTO;
import com.adotai.backend_adotai.entity.enum_types.Gender;
import com.adotai.backend_adotai.entity.enum_types.HealthStatus;
import com.adotai.backend_adotai.entity.enum_types.Size;
import com.adotai.backend_adotai.entity.enum_types.Temperament;

import java.security.Timestamp;
import java.util.List;

public record RequestAnimalDto(
        Integer ongId,
        String name,
        Gender gender,
        ColorInfo color,
        BreedInfo breed,
        SpecieInfo species,
        Integer age,
        Size size,
        HealthStatus health,
        Boolean status,
        Boolean vaccinated,
        Boolean neutered,
        Boolean dewormed,
        Temperament temperament,
        List<RequestAnimalPhotosDTO> photos,
        String animalDescription
) {
    public record ColorInfo(String name) {}
    public record BreedInfo(String name, String speciesDescription) {}
    public record SpecieInfo(String description) {}
}
