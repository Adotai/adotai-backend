package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entity.Animal;
import com.adotai.backend_adotai.entity.enum_types.States;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    List<Animal> findByOngAddressState(States state);
}
