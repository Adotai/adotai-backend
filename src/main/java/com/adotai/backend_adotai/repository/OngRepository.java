package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entity.Ong;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OngRepository extends JpaRepository<Ong,Integer> {
    Optional<Ong> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Ong o SET o.status = NOT o.status WHERE o.id = :id")
    int toggleStatusById(@Param("id") int id);
}
