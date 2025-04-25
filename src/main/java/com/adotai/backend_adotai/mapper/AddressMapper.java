package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Address.request.RequestAddressDTO;
import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.entity.Address;

public class AddressMapper {
    public static Address toEntity(RequestAddressDTO dto) {
        return new Address(
                dto.street(),
                dto.number(),
                dto.city(),
                dto.state(),
                dto.zipCode()
        );
    }

    public static ResponseAddressDTO toDto(Address address){
        return new ResponseAddressDTO(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getCity(),
                address.getState(),
                address.getZipCode()
        );
    }
}
