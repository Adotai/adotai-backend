package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
}
