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
    public ResponseEntity<ResponseApi<?>> create(@RequestBody RequestAnimalDto dto) {
        ResponseApi<?> response = animalService.save(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseApi<?>> getAll() {
        ResponseApi<?> response = animalService.findAll();
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping("/{state}")
    public ResponseEntity<ResponseApi<?>> getByState(@PathVariable String state) {
        ResponseApi<?> response = animalService.findByState(state);
        return ResponseEntity.status(response.status()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> deleteById(@PathVariable int id){
        ResponseApi<?> response = animalService.deleteById(id);
        return ResponseEntity.status(response.status()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> update(@PathVariable int id, @RequestBody RequestAnimalDto dto) {
        ResponseApi<?> response = animalService.update(id, dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping("/ong/{id}")
    public ResponseEntity<ResponseApi<?>> findByOngId(@PathVariable int id){
        ResponseApi<?> response = animalService.findByOngId(id);
        return ResponseEntity.status(response.status()).body(response);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ResponseApi<?>> updateStatusById(@PathVariable int id){
        ResponseApi<?> response = animalService.updateStatusById(id);
        return ResponseEntity.status(response.status()).body(response);
    }

    @DeleteMapping("/photo/{animalId}/{photoId}")
    public ResponseEntity<ResponseApi<?>> deletePhotoById(@PathVariable int animalId, @PathVariable int photoId){
        ResponseApi<?> response = animalService.deleteAnimalPhoto(animalId, photoId);
        return ResponseEntity.status(response.status()).body(response);
    }
}
