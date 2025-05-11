package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.Specie.Request.RequestSpecieDto;
import com.adotai.backend_adotai.dto.Specie.Response.ResponseSpecieDto;
import com.adotai.backend_adotai.entity.Specie;
import com.adotai.backend_adotai.mapper.SpecieMapper;
import com.adotai.backend_adotai.repository.SpecieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecieService {

    private final SpecieRepository specieRepository;

    public SpecieService(SpecieRepository specieRepository) {
        this.specieRepository = specieRepository;
    }

    public List<ResponseSpecieDto> findAll(){
        List<Specie> species = specieRepository.findAll();
        return species.stream().map(SpecieMapper::toDto).toList();
    }

    public ResponseSpecieDto findById(int id) {
        Optional<Specie> specie = specieRepository.findById(id);

        if (specie.isEmpty()) {
            throw new EntityNotFoundException("Specie not found with id: " + id);
        }

        return SpecieMapper.toDto(specie.get());
    }

    public ResponseSpecieDto findByDescription(String description) {
        Optional<Specie> specie = specieRepository.findByDescription(description);

        if (specie.isEmpty()) {
            throw new EntityNotFoundException("Specie not found with id: " + description);
        }

        return SpecieMapper.toDto(specie.get());
    }

    public ResponseSpecieDto save(RequestSpecieDto requestSpecieDto){
        Specie specie = specieRepository.save(SpecieMapper.toEntity(requestSpecieDto));
        return SpecieMapper.toDto(specie);
    }

}
