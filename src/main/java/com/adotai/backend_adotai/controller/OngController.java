package com.adotai.backend_adotai.controller;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.Ong.Request.RequestOngDTO;
import com.adotai.backend_adotai.service.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ongs")
public class OngController {

    @Autowired
    private OngService ongService;

    @PostMapping
    public ResponseEntity<ResponseApi> create(@RequestBody RequestOngDTO dto){
        ResponseApi response = ongService.create(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseApi> findAll(){
        ResponseApi response = ongService.findAll();
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi> findById(@PathVariable int id){
        ResponseApi response = ongService.findById(id);
        return ResponseEntity.status(response.status()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> delete(@PathVariable int id){
        ResponseApi response = ongService.delete(id);
        return ResponseEntity.status(response.status()).body(response);
    }
}
