package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Animal.Request.RequestAnimalDto;
import com.adotai.backend_adotai.dto.Animal.Response.ResponseAnimalDto;
import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.Ong.Response.ResponseOngDTO;
import com.adotai.backend_adotai.entity.*;
import com.adotai.backend_adotai.entity.enum_types.States;
import com.adotai.backend_adotai.mapper.AnimalMapper;
import com.adotai.backend_adotai.mapper.OngMapper;
import com.adotai.backend_adotai.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final OngRepository ongRepository;
    private final ColorRepository colorRepository;
    private final BreedRepository breedRepository;
    private final SpecieRepository specieRepository;

    public AnimalService(AnimalRepository animalRepository,
                         OngRepository ongRepository,
                         ColorRepository colorRepository,
                         BreedRepository breedRepository,
                         SpecieRepository specieRepository) {
        this.animalRepository = animalRepository;
        this.ongRepository = ongRepository;
        this.colorRepository = colorRepository;
        this.breedRepository = breedRepository;
        this.specieRepository = specieRepository;
    }

    public ResponseApi<?> save(RequestAnimalDto dto) {

        Optional<Ong> ong = ongRepository.findById(dto.ongId());
        if (ong.isEmpty()) {
            return ResponseApi.error(404, "ONG NOT FOUND");
        }

        if (dto.photos() == null || dto.photos().isEmpty()) {
            return ResponseApi.error(404, "Fotos faltando ou inválidas.");
        }

        String specieDesc = dto.species().description().toUpperCase();
        String breedName = dto.breed().name().toUpperCase();
        String colorName = dto.color().name().toUpperCase();

        Specie specie = getOrCreateSpecie(specieDesc);
        Breed breed = getOrCreateBreed(breedName, specie);
        Color color = getOrCreateColor(colorName);

        Timestamp now = Timestamp.from(Instant.now());

        Animal animal = AnimalMapper.toEntity(dto, ong.get(), color, breed, specie, now);
        animalRepository.save(animal);

        return ResponseApi.success("Animal created successfully", AnimalMapper.toDto(animal));
    }

    public ResponseApi findAll() {
        List<ResponseAnimalDto> dtos = animalRepository.findAll().stream()
                .map(AnimalMapper::toDto)
                .toList();
        return ResponseApi.success("Animal found", dtos);
    }

    public ResponseAnimalDto findById(int id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with id: " + id));

        return AnimalMapper.toDto(animal);
    }

    private Specie getOrCreateSpecie(String description) {
        return specieRepository.findByDescription(description)
                .orElseGet(() -> {
                    Specie newSpecie = new Specie(description, true);
                    return specieRepository.save(newSpecie);
                });
    }

    private Breed getOrCreateBreed(String name, Specie specie) {
        return breedRepository.findByName(name)
                .orElseGet(() -> {
                    Breed newBreed = new Breed(specie, name);
                    return breedRepository.save(newBreed);
                });
    }

    private Color getOrCreateColor(String name) {
        return colorRepository.findByName(name)
                .orElseGet(() -> {
                    Color newColor = new Color(name);
                    return colorRepository.save(newColor);
                });
    }

    public ResponseApi deleteById(int id){
        Optional<Animal> animal = animalRepository.findById(id);

        if(animal.isEmpty())
            return ResponseApi.error(404,"Ong not found.");

        try{
            animalRepository.deleteById(id);
            ResponseAnimalDto dto = AnimalMapper.toDto(animal.get());

            return ResponseApi.success("Success",dto);
        } catch (Exception e) {
            return ResponseApi.error(500,"Error: " + e.getMessage());
        }
    }

    public ResponseApi findByState(String state){
        List<Animal> animal = animalRepository.findByOngAddressState(States.valueOf(state.toUpperCase()));
        if(animal.isEmpty())
            return ResponseApi.error(404,"Animals from this state not found.");
        List<ResponseAnimalDto> dto =  animal.stream().map(AnimalMapper::toDto).toList();
        return ResponseApi.success("Success", dto);
    }

}