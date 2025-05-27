package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entity.Animal;
import com.adotai.backend_adotai.entity.enum_types.States;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findByOngAddressState(States state);
    List<Animal> findByOngId(int id);

    @Modifying
    @Transactional
    @Query("UPDATE Animal o SET o.status = CASE WHEN o.status = TRUE THEN FALSE ELSE TRUE END WHERE o.id = :id")
    int toggleStatusById(@Param("id") int id);
}
