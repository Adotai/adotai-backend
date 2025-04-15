package com.adotai.backend_adotai.mapper;

import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.dto.Ong.Request.RequestOngDTO;
import com.adotai.backend_adotai.dto.Ong.Response.ResponseOngDTO;
import com.adotai.backend_adotai.entitiy.Address;
import com.adotai.backend_adotai.entitiy.Documents;
import com.adotai.backend_adotai.entitiy.Ong;
import com.adotai.backend_adotai.util.ValidationUtils;

public class OngMapper {

    public static Ong toEntity(RequestOngDTO dto, Address address, String econdedPassword){
        return new Ong(dto.name(),
                ValidationUtils.formatStrNumber(dto.phone()),
                ValidationUtils.formatStrNumber(dto.cnpj()),
                dto.email(),
                econdedPassword,
                dto.documents().getSocialStatute(),
                dto.documents().getBoardMeeting(),
                address,
                dto.status());
    }

    public static ResponseOngDTO toDto(Ong ong){
        Address address = ong.getAddress();
        ResponseAddressDTO addressDto = address == null ? null : AddressMapper.toDto(address);
        Documents documents = new Documents(ong.getBoardMeeting(), ong.getSocialStatute());
        return new ResponseOngDTO(
                ong.getId(),
                ong.getName(),
                ong.getPhone(),
                ong.getCnpj(),
                ong.getEmail(),
                ong.getPix(),
                documents,
                addressDto ,
                ong.isStatus());
    }
}
