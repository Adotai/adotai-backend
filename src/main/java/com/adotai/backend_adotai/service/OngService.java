package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.dto.Ong.Request.RequestOngDTO;
import com.adotai.backend_adotai.dto.Ong.Response.ResponseOngDTO;
import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.entitiy.Ong;
import com.adotai.backend_adotai.mapper.OngMapper;
import com.adotai.backend_adotai.repository.AddressRepository;
import com.adotai.backend_adotai.repository.OngRepository;
import com.adotai.backend_adotai.util.ValidationUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OngService {

    private final OngRepository ongRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public OngService(OngRepository ongRepository, AddressRepository addressRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtEncoder jwtEncoder) {
        this.ongRepository = ongRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    public ResponseApi<ResponseOngDTO> create(RequestOngDTO dto){
       if (!ValidationUtils.isValidCnpj(dto.cnpj())){
           return ResponseApi.error(404,"Missing or invalid cnpj.");
       }

       if(!ValidationUtils.isValidPhone(dto.phone())){
           return ResponseApi.error(404,"Missing or invalid phone number.");
       }

       if(!ValidationUtils.isValidEmail(dto.email())){
           return ResponseApi.error(404,"Missing or invalid email.");
       }

       Optional<Address> address = addressRepository.findById(dto.addressId());
       if(address.isEmpty()){
           return ResponseApi.error(404,"Address not found.");
       }

       if(dto.password().isBlank())
            return ResponseApi.error(404,"Missing or invalid password.");

        String encodedPassword = passwordEncoder.encode(dto.password());
        try{
            Ong ong = OngMapper.toEntity(dto,address.get(),encodedPassword);
            ongRepository.save(ong);
            return ResponseApi.success("Success",OngMapper.toDto(ong));
        } catch (Exception e) {
            return ResponseApi.error(500,"Error: " + e.getMessage());
        }
    }

    public ResponseApi<List<ResponseOngDTO>> findAll(){
        List<Ong> ongs = ongRepository.findAll();
        List<ResponseOngDTO> ongDto = ongs.stream().map(OngMapper::toDto).toList();
        return ResponseApi.success("Success",ongDto);
    }

    public ResponseApi<ResponseOngDTO> findById(int id){
        Optional<Ong> ong = ongRepository.findById(id);

        if(ong.isEmpty())
            return ResponseApi.error(404,"Ong not found.");

        ResponseOngDTO dto = OngMapper.toDto(ong.get());
        return ResponseApi.success("Success",dto);
    }

    public ResponseApi<ResponseOngDTO> delete(int id){
        Optional<Ong> ong = ongRepository.findById(id);

        if(ong.isEmpty())
            return ResponseApi.error(404,"Ong not found.");

        try{
            ongRepository.deleteById(id);
            ResponseOngDTO dto = OngMapper.toDto(ong.get());
            return ResponseApi.success("Success",dto);
        } catch (Exception e) {
            return ResponseApi.error(500,"Error: " + e.getMessage());
        }
    }
}
