package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Animal.Request.RequestAnimalDto;
import com.adotai.backend_adotai.dto.Animal.Request.RequestAnimalPhotosDTO;
import com.adotai.backend_adotai.dto.Animal.Request.RequestStatusUpdateDto;
import com.adotai.backend_adotai.dto.Animal.Response.ReponseAnimalsRequestDto;
import com.adotai.backend_adotai.dto.Animal.Response.ResponseAnimalDto;
import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.entity.*;
import com.adotai.backend_adotai.entity.PhotosEntities.AnimalPhotos;
import com.adotai.backend_adotai.entity.enum_types.States;
import com.adotai.backend_adotai.mapper.AnimalMapper;
import com.adotai.backend_adotai.repository.*;
import com.adotai.backend_adotai.repository.PhotosRepository.AnimalPhotosRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final OngRepository ongRepository;
    private final ColorRepository colorRepository;
    private final BreedRepository breedRepository;
    private final SpecieRepository specieRepository;
    private final AnimalPhotosRepository animalPhotosRepository;
    private final UserRepository userRepository;

    public AnimalService(AnimalRepository animalRepository,
                         OngRepository ongRepository,
                         ColorRepository colorRepository,
                         BreedRepository breedRepository,
                         SpecieRepository specieRepository,
                         AnimalPhotosRepository animalPhotosRepository,
                         UserRepository userRepository) {
        this.animalRepository = animalRepository;
        this.ongRepository = ongRepository;
        this.colorRepository = colorRepository;
        this.breedRepository = breedRepository;
        this.specieRepository = specieRepository;
        this.animalPhotosRepository = animalPhotosRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseApi<?> save(RequestAnimalDto dto) {

        var ong = ongRepository.findById(dto.ongId())
                .orElse(null);
        if (ong == null) {
            return ResponseApi.error(404, "ONG NOT FOUND");
        }

        if (dto.photos() == null || dto.photos().isEmpty()) {
            return ResponseApi.error(400, "Fotos faltando ou inválidas.");
        }

        // Valida mínimos para evitar NPE em toUpperCase()
        if (dto.species() == null || dto.species().description() == null ||
                dto.breed() == null || dto.breed().name() == null ||
                dto.color() == null || dto.color().name() == null) {
            return ResponseApi.error(400, "Cor, raça e espécie são obrigatórias.");
        }

        String specieDesc = dto.species().description().toUpperCase();
        String breedName  = dto.breed().name().toUpperCase();
        String colorName  = dto.color().name().toUpperCase();

        Specie specie = getOrCreateSpecie(specieDesc);
        Breed  breed  = getOrCreateBreed(breedName, specie);
        Color  color  = getOrCreateColor(colorName);

        User user = null;
        if (dto.userId() != null) {
            user = userRepository.findById(dto.userId())
                    .orElse(null);
            if (user == null) {
                return ResponseApi.error(404, "USER NOT FOUND");
            }
        }

        Timestamp now = Timestamp.from(Instant.now());

        Animal animal = AnimalMapper.toEntity(dto, ong, color, breed, specie, now, user);
        animalRepository.save(animal);

        return ResponseApi.success("Animal created successfully", AnimalMapper.toDto(animal));
    }


    public ResponseApi<?> findAll() {
        List<ResponseAnimalDto> dtos = animalRepository.findAllByStatusTrue().stream()
                .map(AnimalMapper::toDto)
                .toList();
        return ResponseApi.success("Animal found", dtos);
    }

    public ResponseAnimalDto findById(int id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with id: " + id));

        return AnimalMapper.toDto(animal);
    }

    @Transactional
    public ResponseApi<?> update(int id, RequestAnimalDto dto) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with id: " + id));

        Ong ong = ongRepository.findById(dto.ongId())
                .orElseThrow(() -> new EntityNotFoundException("ONG not found with id: " + dto.ongId()));

        Specie specie = getOrCreateSpecie(dto.species().description().toUpperCase());
        Breed breed = getOrCreateBreed(dto.breed().name().toUpperCase(), specie);
        Color color = getOrCreateColor(dto.color().name().toUpperCase());

        animal.setOng(ong);
        animal.setName(dto.name());
        animal.setGender(dto.gender());
        animal.setColor(color);
        animal.setBreed(breed);
        animal.setSpecies(specie);
        animal.setAge(dto.age());
        animal.setSize(dto.size());
        animal.setHealth(dto.health());
        animal.setStatus(dto.status());
        animal.setVaccinated(dto.vaccinated());
        animal.setNeutered(dto.neutered());
        animal.setDewormed(dto.dewormed());
        animal.setTemperament(dto.temperament());
        animal.setAnimalDescription(dto.animalDescription());
        animal.setStatus(dto.solicitationStatus());

        List<AnimalPhotos> existingPhotos = animal.getPhotos();

        for (RequestAnimalPhotosDTO photoDTO : dto.photos()) {
            if (photoDTO.getId() != null) {
                existingPhotos.stream()
                        .filter(p -> p.getId() == photoDTO.getId())
                        .findFirst()
                        .ifPresent(p -> p.setPhotoUrl(photoDTO.getPhotoUrl()));
            } else {
                AnimalPhotos newPhoto = new AnimalPhotos();
                newPhoto.setAnimal(animal);
                newPhoto.setPhotoUrl(photoDTO.getPhotoUrl());
                existingPhotos.add(newPhoto);
            }
        }

        animalRepository.save(animal);

        return ResponseApi.success("Animal updated successfully", AnimalMapper.toDto(animal));
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

    @Transactional
    public ResponseApi<?> updateAnimalStatuses(int id, RequestStatusUpdateDto dto) {
        Optional<Animal> animalOptional = animalRepository.findById(id);
        if (animalOptional.isEmpty()) {
            return ResponseApi.error(404, "Animal não encontrado.");
        }

        Animal animal = animalOptional.get();
        if (dto.status() != null) {
            animal.setStatus(dto.status());
        }
        if (dto.solicitationStatus() != null) {
            animal.setSolicitation_status(dto.solicitationStatus());
        }

        animalRepository.save(animal);
        return ResponseApi.success("Status atualizados com sucesso", null);
    }

    @Transactional
    public ResponseApi<?> deleteById(int id){
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

    public ResponseApi<?> findByState(String state){
        List<Animal> animal = animalRepository.findByOngAddressStateAndStatusTrue(States.valueOf(state.toUpperCase()));
        if(animal.isEmpty())
            return ResponseApi.error(404,"Animals from this state not found.");
        List<ResponseAnimalDto> dto =  animal.stream().map(AnimalMapper::toDto).toList();
        return ResponseApi.success("Success", dto);
    }

    public ResponseApi<?> findByOngId(int id){
        List<Animal> animals = animalRepository.findByOngId(id);

        List<ResponseAnimalDto> dto = animals.stream().map(AnimalMapper :: toDto).toList();
        return ResponseApi.success("Success",dto);
    }

    public ResponseApi<?> updateStatusById(int id){
        int rowsAffected = animalRepository.toggleStatusById(id);
        if (rowsAffected == 0){
            return ResponseApi.error(404,"Not Found");
        }
        return ResponseApi.success("Success",null);
    }

    @Transactional
    public ResponseApi<?> deleteAnimalPhoto(int animalId, int photoId) {
        Optional<AnimalPhotos> photo = animalPhotosRepository.findByIdAndAnimalId(photoId, animalId);

        if (photo.isEmpty()) {
            return ResponseApi.error(404, "Foto não encontrada para este animal.");
        }

        animalPhotosRepository.deleteByIdAndAnimalId(photoId, animalId);

        return ResponseApi.success("Foto deletada com sucesso", null);
    }

    public ResponseApi<?> getAnimal(int id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        if (optionalAnimal.isEmpty()) {
            return ResponseApi.error(404, "Animal not found with id: " + id);
        }

        Animal animal = optionalAnimal.get();
        ResponseAnimalDto dto = AnimalMapper.toDto(animal);

        return ResponseApi.success("Animal found successfully", dto);
    }


    public ResponseApi<?> findAnimalRequest(int ongId) {
        List<ReponseAnimalsRequestDto> dtos =
                animalRepository.findByUserIsNotNullAndSolicitationStatusTrueAndOngId(ongId)
                        .stream()
                        .map(AnimalMapper::toRequestDto)
                        .toList();

        return ResponseApi.success("Animal found", dtos);
    }

    public ResponseApi<?> adoptAnimal(int animalId, int newOwnerId) {
        try {
            Animal animal = animalRepository.findById(animalId).orElse(null);
            User newOwner = userRepository.findById(newOwnerId).orElse(null);

            if (animal == null || newOwner == null) {
                return ResponseApi.error(404, "Animal ou Usuário não encontrado.");
            }

            animal.setUser(newOwner);
            animal.setStatus(false);
            animal.setSolicitation_status(false);

            animalRepository.save(animal);
            return ResponseApi.success("Adoção registrada com sucesso!", AnimalMapper.toDto(animal));
        } catch (Exception e) {
            return ResponseApi.error(500, "Erro ao registrar adoção: " + e.getMessage());
        }
    }

    public ResponseApi<?> findByBreedPaged(String filter, int pageNumber, int pageSize, String sortField, String sortDirection) {
        if (pageNumber < 0 || pageSize <= 0) {
            return ResponseApi.error(400, "Invalid pagination parameters.");
        }

        Sort.Direction direction;
        try {
            direction = Sort.Direction.fromString(sortDirection != null ? sortDirection : "ASC");
        } catch (IllegalArgumentException e) {
            direction = Sort.Direction.ASC; // fallback se vier algo inválido
        }

        Sort sort = Sort.by(direction, sortField != null ? sortField : "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Animal> page = animalRepository.findByBreedName(filter, pageable);

        if (page.isEmpty()) {
            return ResponseApi.error(404, "No animals found for this breed.");
        }

        return ResponseApi.success("Animals found", page.map(AnimalMapper::toDto));
    }
}