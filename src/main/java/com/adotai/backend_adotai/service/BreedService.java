package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Breed.Request.RequestBreedDto;
import com.adotai.backend_adotai.dto.Breed.Response.ResponseBreedDto;
import com.adotai.backend_adotai.entity.Breed;
import com.adotai.backend_adotai.entity.Specie;
import com.adotai.backend_adotai.mapper.BreedMapper;
import com.adotai.backend_adotai.repository.BreedRepository;
import com.adotai.backend_adotai.repository.SpecieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BreedService {
    private final BreedRepository breedRepository;
    private final SpecieRepository specieRepository;

    public BreedService(BreedRepository breedRepository, SpecieRepository specieRepository) {
        this.breedRepository = breedRepository;
        this.specieRepository = specieRepository;

    }

    public ResponseBreedDto findById(int id) {
        return breedRepository.findById(id)
                .map(BreedMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Breed not found with id: " + id));
    }

    public ResponseBreedDto save(RequestBreedDto dto) {
        Specie specie = specieRepository.findById(dto.speciesId())
                .orElseThrow(() -> new EntityNotFoundException("Specie not found with id: " + dto.speciesId()));

        Breed breed = BreedMapper.toEntity(dto, specie);
        breedRepository.save(breed);
        return BreedMapper.toResponseDto(breed);
    }

    public ResponseBreedDto findByDescription(String description) {
        return breedRepository.findByName(description)
                .map(BreedMapper::toResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Breed not found with id: " + description));
    }
}
