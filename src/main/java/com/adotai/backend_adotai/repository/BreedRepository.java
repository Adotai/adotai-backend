package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreedRepository extends JpaRepository<Breed, Integer> {
    Optional<Breed> findByName(String description);
}
