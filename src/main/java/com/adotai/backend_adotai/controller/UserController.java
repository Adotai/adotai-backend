package com.adotai.backend_adotai.controller;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.User.UpdateUserDto;
import com.adotai.backend_adotai.dto.User.request.RequestUserDTO;
import com.adotai.backend_adotai.dto.User.response.ResponseUserDTO;
import com.adotai.backend_adotai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseApi<?>> create(@RequestBody RequestUserDTO dto) {
        ResponseApi<?> response = userService.create(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseApi<?>> updateUser(@RequestBody UpdateUserDto dto) {
        ResponseApi<?> response = userService.updateUser(dto);
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseApi<?>> getAll(){
        ResponseApi<?> response = userService.getUsers();
        return ResponseEntity.status(response.status()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> getById(@PathVariable int id){
        ResponseApi<?> response = userService.getUserById(id);
        return ResponseEntity.status(response.status()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> delete(@PathVariable int id){
        ResponseApi<?> response = userService.deleteUser(id);
        return ResponseEntity.status(response.status()).body(response);
    }
}
