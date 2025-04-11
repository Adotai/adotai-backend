package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Address.request.RequestAddressDTO;
import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.mapper.AddressMapper;
import com.adotai.backend_adotai.repository.AddressRepository;
import com.adotai.backend_adotai.util.ValidationUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public ResponseApi create(RequestAddressDTO dto){
        if(!ValidationUtils.isValidCep(dto.zipCode())){
            return ResponseApi.error(404,"Invalid Zip Code");
        }
        Optional<Address> optionalAddress = addressRepository.findByZipCodeAndNumber(
                dto.zipCode(),dto.number());

        if(optionalAddress.isPresent()){
            return ResponseApi.success("Success",optionalAddress.get());
        }
        try{
            return ResponseApi.success("Success",addressRepository.save(AddressMapper.toEntity(dto)));
        } catch (Exception e) {
           return ResponseApi.error(500,e.getMessage());
        }
    }
}
