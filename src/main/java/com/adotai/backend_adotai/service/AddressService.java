package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Address.request.RequestAddressDTO;
import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.mapper.AddressMapper;
import com.adotai.backend_adotai.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address create(RequestAddressDTO dto){
        Address address = AddressMapper.toEntity(dto);
        return addressRepository.save(address);
    }
}
