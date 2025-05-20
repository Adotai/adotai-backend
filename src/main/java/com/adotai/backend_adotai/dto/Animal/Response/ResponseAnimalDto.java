package com.adotai.backend_adotai.dto.Animal.Response;

import com.adotai.backend_adotai.dto.Ong.Response.ResponseOngPhotosDTO;
import com.adotai.backend_adotai.entity.enum_types.Gender;
import com.adotai.backend_adotai.entity.enum_types.HealthStatus;
import com.adotai.backend_adotai.entity.enum_types.Temperament;

import java.sql.Timestamp;
import java.util.List;

public record ResponseAnimalDto(
        int id,
        int ongId,
        String name,
        Gender gender,
        String color,
        String breed,
        String species,
        Integer age,
        HealthStatus health,
        boolean status,
        boolean vaccinated,
        boolean neutered,
        boolean dewormed,
        Temperament temperament,
        Timestamp createdAt,
        List<ResponseAnimalPhotosDTO> photos
) {
}