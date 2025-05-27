package com.adotai.backend_adotai.controller;

import com.adotai.backend_adotai.dto.Address.request.RequestAddressDTO;
import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<ResponseApi<?>> create(@RequestBody RequestAddressDTO dto){
       ResponseApi<?> response = addressService.create(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseApi<?>> findAll(){
        ResponseApi<?> response = addressService.findAll();
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> findById(@PathVariable int id){
        ResponseApi<?> response = addressService.findById(id);
        return ResponseEntity.status(response.status()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> deleteById(@PathVariable int id){
        ResponseApi<?> response = addressService.deleteById(id);
        return ResponseEntity.status(response.status()).body(response);
    }
}
