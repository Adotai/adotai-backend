package com.adotai.backend_adotai.repository;

import com.adotai.backend_adotai.entitiy.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
