package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Address.UpdateAddressDto;
import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.User.UpdateUserDto;
import com.adotai.backend_adotai.dto.User.request.RequestUserDTO;
import com.adotai.backend_adotai.dto.User.response.ResponseUserDTO;
import com.adotai.backend_adotai.entity.Address;
import com.adotai.backend_adotai.entity.User;
import com.adotai.backend_adotai.mapper.AddressMapper;
import com.adotai.backend_adotai.mapper.UserMapper;
import com.adotai.backend_adotai.repository.AddressRepository;
import com.adotai.backend_adotai.repository.UserRepository;
import com.adotai.backend_adotai.util.ValidationUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, BCryptPasswordEncoder passwordEncoder, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
    }

    public ResponseApi<ResponseUserDTO> create(RequestUserDTO dto) {

        if ((!ValidationUtils.isValidCpf(dto.cpf())) ||
                (userRepository.existsByCpf(ValidationUtils.formatStrNumber(dto.cpf())))) {
            return ResponseApi.error(400, "CPF invalid or already exists");
        }

        if (!ValidationUtils.isValidPhone(dto.telephone())) {
            return ResponseApi.error(400, "Invalid or empty phone number");
        }

        if (!ValidationUtils.isValidEmail(dto.email())) {
            return ResponseApi.error(400, "Email is invalid");
        }

        if (userRepository.existsByEmailIgnoreCase(dto.email())) {
            return ResponseApi.error(400, "Email already exists");
        }

        Optional<Address> address = addressRepository.findById(dto.addressId());

        if (address.isEmpty()) {
            return ResponseApi.error(404,"Address not found");
        }

        if(dto.password().isBlank()){
            return ResponseApi.error(404,"Invalid or empty password");
        }

        String encodedPassword = passwordEncoder.encode(dto.password());
        User user = UserMapper.toEntity(dto, address.get(), encodedPassword);

        try{
            return ResponseApi.success("Success",UserMapper.toDto(userRepository.save(user)));
        } catch (Exception e) {
            return ResponseApi.error(500,e.getMessage());
        }
    }

    public ResponseApi<ResponseUserDTO> updateUser(UpdateUserDto dto) {
        Optional<User> existingUserOpt = userRepository.findById(dto.id());

        if (existingUserOpt.isEmpty()) {
            return ResponseApi.error(404, "User not found");
        }

        User existingUser = existingUserOpt.get();

        if(dto.name() != null && !dto.name().isBlank()){
            existingUser.setName(dto.name());
        }

        if(dto.cpf() != null && !dto.cpf().isBlank()){
            if(!ValidationUtils.isValidCpf(dto.cpf())){
                return ResponseApi.error(400, "Invalid CPF");
            }
           Optional<User> user = userRepository.findByCpf(ValidationUtils.formatStrNumber(dto.cpf()));

            if (user.isPresent() && (user.get().getId() != dto.id())) {
                return ResponseApi.error(400, "CPF already exists");
            } else {
                existingUser.setCpf(ValidationUtils.formatStrNumber(dto.cpf()));
            }
        }

        if (dto.telephone() != null && !dto.telephone().isBlank()){
            if (!ValidationUtils.isValidPhone(dto.telephone())) {
                return ResponseApi.error(400, "Invalid phone number");
            } else{
                existingUser.setTelephone(dto.telephone());
            }
        }

        if (dto.email() != null && !dto.email().isBlank()) {
            if (!ValidationUtils.isValidEmail(dto.email())) {
                return ResponseApi.error(400, "Invalid email");
            } else if (!existingUser.getEmail().equalsIgnoreCase(dto.email()) && userRepository.existsByEmailIgnoreCase(dto.email())){
                return ResponseApi.error(400, "Email already exists");
            } else {
                existingUser.setEmail(dto.email());
            }
        }

        if (dto.address() != null) {
            UpdateAddressDto updateAddressDto = AddressMapper.toUpdateDto(dto.address());
            ResponseApi<ResponseAddressDTO> addressUpdateResponse = addressService.update(updateAddressDto);

            if (addressUpdateResponse.status() >= 400 || addressUpdateResponse.data() == null) {
                return ResponseApi.error(addressUpdateResponse.status(), addressUpdateResponse.message());
            }

            // Converte o DTO atualizado para a entidade e atribui ao usuário
            Address updatedAddress = AddressMapper.toEntity(addressUpdateResponse.data());
            existingUser.setAddress(updatedAddress);
        }

        if (dto.password() != null && !dto.password().isBlank()) {
            String encodedPassword = passwordEncoder.encode(dto.password());
            existingUser.setPassword(encodedPassword);
        }

        try {
            User updatedUser = userRepository.save(existingUser);
            return ResponseApi.success("User updated successfully", UserMapper.toDto(updatedUser));
        } catch (Exception e) {
            return ResponseApi.error(500, e.getMessage());
        }
    }


    public ResponseApi<List<ResponseUserDTO>> getUsers(){
      List<User> users = userRepository.findAll();
      List<ResponseUserDTO> dto = users.stream().map(UserMapper::toDto).toList();
      return ResponseApi.success("Success",dto);
    }

    public ResponseApi<ResponseUserDTO> getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            return ResponseApi.error(404,"User not found");
        }else{
            ResponseUserDTO dto = UserMapper.toDto(user.get());
            return ResponseApi.success("Success",dto);
        }
    }

    public ResponseApi<ResponseUserDTO> deleteUser(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return ResponseApi.error(404,"User not found");
        }else{
            userRepository.delete(user.get());
            return ResponseApi.success("Success",UserMapper.toDto(user.get()));
        }
    }
}
