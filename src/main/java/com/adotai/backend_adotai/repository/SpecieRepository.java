package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entity.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecieRepository extends JpaRepository<Specie, Integer> {
    Optional<Specie> findByDescription(String description);
}
