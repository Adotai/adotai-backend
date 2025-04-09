package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.User.request.RequestUserDTO;
import com.adotai.backend_adotai.dto.User.response.ResponseUserDTO;
import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.entitiy.User;
import com.adotai.backend_adotai.mapper.UserMapper;
import com.adotai.backend_adotai.repository.AddressRepository;
import com.adotai.backend_adotai.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseUserDTO createUser(RequestUserDTO dto) {
        Address address = addressRepository.findById(dto.addressId())
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        String encodedPassword = passwordEncoder.encode(dto.password());

        User user = UserMapper.toEntity(dto, address, encodedPassword);
        userRepository.save(user);

        return UserMapper.toDto(user);
    }
}
