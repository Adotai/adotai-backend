package com.adotai.backend_adotai.controller;

import com.adotai.backend_adotai.dto.Address.request.RequestAddressDTO;
import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.mapper.AddressMapper;
import com.adotai.backend_adotai.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<ResponseAddressDTO> create(@RequestBody RequestAddressDTO dto){
        Address address = addressService.create(dto);
        return ResponseEntity.ok(AddressMapper.toDto(address));
    }
}
