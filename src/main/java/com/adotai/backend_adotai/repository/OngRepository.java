package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entity.Ong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OngRepository extends JpaRepository<Ong,Integer> {
    Optional<Ong> findByEmail(String email);
}
