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
    @Query("SELECT o FROM Ong o WHERE UPPER(o.email) = UPPER(:email)")
    Optional<Ong> findByEmailIgnoreCase(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Ong o WHERE UPPER(o.email) = UPPER(:email)")
    Boolean existsByEmailIgnoreCase(@Param("email") String email);
    @Modifying
    @Transactional
    @Query("UPDATE Ong o SET o.status = CASE WHEN o.status = TRUE THEN FALSE ELSE TRUE END WHERE o.id = :id")
    int toggleStatusById(@Param("id") int id);
}
