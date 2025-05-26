package com.adotai.backend_adotai.dto.Ong;

import com.adotai.backend_adotai.dto.Ong.Request.RequestOngPhotosDTO;
import com.adotai.backend_adotai.entity.Address;

import java.util.List;

public record UpdateOngDto(
        Integer id,
        String name,
        String phone,
        String cnpj,
        String email,
        String password,
        List<UpdateOngPhotosDTO> photos,
        String description,
        Address address
) {
}
