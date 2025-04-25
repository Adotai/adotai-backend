package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findByZipCodeAndNumber(String zipCode, int number);
}
