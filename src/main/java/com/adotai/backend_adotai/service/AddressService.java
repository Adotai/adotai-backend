package com.adotai.backend_adotai.service;

import com.adotai.backend_adotai.dto.Address.request.RequestAddressDTO;
import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.dto.Api.ResponseApi;
import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.mapper.AddressMapper;
import com.adotai.backend_adotai.repository.AddressRepository;
import com.adotai.backend_adotai.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public ResponseApi<ResponseAddressDTO> create(RequestAddressDTO dto){
        if(!ValidationUtils.isValidCep(dto.zipCode())){
            return ResponseApi.error(404,"Invalid Zip Code");
        }
        Optional<Address> optionalAddress = addressRepository.findByZipCodeAndNumber(
                dto.zipCode(),dto.number());

        if(optionalAddress.isPresent()){
            ResponseAddressDTO responseDto = AddressMapper.toDto(optionalAddress.get());
            return ResponseApi.success("Success",responseDto);
        }
        try{
            Address address = addressRepository.save(AddressMapper.toEntity(dto));

            return ResponseApi.success("Success",AddressMapper.toDto(address));
        } catch (Exception e) {
           return ResponseApi.error(500,e.getMessage());
        }
    }

    public ResponseApi<List<ResponseAddressDTO>> findAll(){
        List<Address> addresses = addressRepository.findAll();
        List<ResponseAddressDTO> dto = addresses.stream().map(AddressMapper::toDto).toList();
        return ResponseApi.success("Success",dto);
    }

    public ResponseApi<ResponseAddressDTO> findById(int id){
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()){
            return ResponseApi.error(400,"Address not found");
        }
        return ResponseApi.success("Success",AddressMapper.toDto(address.get()));
    }

    public ResponseApi<ResponseAddressDTO> deleteById(int id){
        Optional<Address> address = addressRepository.findById(id);
        if(address.isEmpty()){
            return ResponseApi.error(404,"Address not found");
        }
        try{
            addressRepository.deleteById(id);
            return ResponseApi.success("Success",AddressMapper.toDto(address.get()));
        }catch (Exception e){
            return ResponseApi.error(500, "Error: " + e.getMessage());
        }
    }

}
