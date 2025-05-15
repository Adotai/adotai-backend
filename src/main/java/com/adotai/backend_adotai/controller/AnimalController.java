package com.adotai.backend_adotai.controller;

import com.adotai.backend_adotai.dto.Animal.Request.RequestAnimalDto;
import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/animal")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<ResponseApi> create(@RequestBody RequestAnimalDto dto) {
        ResponseApi response = animalService.save(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseApi> getAll() {
        ResponseApi response = animalService.findAll();
        return ResponseEntity.status(response.status()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi> deleteById(@PathVariable int id){
        ResponseApi response = animalService.deleteById(id);
        return ResponseEntity.status(response.status()).body(response);
    }
}
