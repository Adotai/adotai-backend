package com.adotai.backend_adotai.controller;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.Ong.DeleteOngPhotosDTO;
import com.adotai.backend_adotai.dto.Ong.Request.RequestOngDTO;
import com.adotai.backend_adotai.dto.Ong.UpdateOngDto;
import com.adotai.backend_adotai.dto.User.UpdateUserDto;
import com.adotai.backend_adotai.service.OngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ong")
public class OngController {

    @Autowired
    private OngService ongService;

    @PostMapping
    public ResponseEntity<ResponseApi<?>> create(@RequestBody RequestOngDTO dto){
        ResponseApi<?> response = ongService.create(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseApi<?>> updateOng(@RequestBody UpdateOngDto dto) {
        ResponseApi<?> response = ongService.updateOng(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @PostMapping("/delete-photo")
    public ResponseEntity<ResponseApi<String>> deletePhotos(@RequestBody DeleteOngPhotosDTO dto) {
        ResponseApi<String> response = ongService.deleteOngPhotos(dto.ongId(), dto.photoIdsToDelete());
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseApi<?>> findAll(){
        ResponseApi<?> response = ongService.findAll();
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> findById(@PathVariable int id){
        ResponseApi<?> response = ongService.findById(id);
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<ResponseApi<?>> findByState(@PathVariable String state){
        ResponseApi<?> response = ongService.findByState(state);
        return ResponseEntity.status(response.status()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> delete(@PathVariable int id){
        ResponseApi<?> response = ongService.delete(id);
        return ResponseEntity.status(response.status()).body(response);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ResponseApi<?>> updateStatusById(@PathVariable int id){
        ResponseApi<?> response = ongService.updateStatusById(id);
        return ResponseEntity.status(response.status()).body(response);
    }
}
