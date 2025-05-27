package com.adotai.backend_adotai.repository.PhotosRepository;

import com.adotai.backend_adotai.entity.PhotosEntities.AnimalPhotos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AnimalPhotosRepository extends JpaRepository<AnimalPhotos, Integer> {
    Optional<AnimalPhotos> findByIdAndAnimalId(int photoId, int animalId);

    @Modifying
    @Transactional
    void deleteByIdAndAnimalId(int photoId, int animalId);
}
