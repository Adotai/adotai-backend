package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);

    Optional<User> findById(int id);
}
