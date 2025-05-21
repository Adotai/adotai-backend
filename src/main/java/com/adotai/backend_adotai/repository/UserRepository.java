package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);

    @Query("SELECT u FROM User u WHERE UPPER(u.email) = UPPER(:email)")
    Optional<User> findByEmailIgnoreCase(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE UPPER(u.email) = UPPER(:email)")
    Boolean existsByEmailIgnoreCase(@Param("email") String email);


    Optional<User> findById(int id);
}
