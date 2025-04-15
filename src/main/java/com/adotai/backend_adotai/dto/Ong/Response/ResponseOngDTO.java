package com.adotai.backend_adotai.dto.Ong.Response;

import com.adotai.backend_adotai.dto.Address.response.ResponseAddressDTO;
import com.adotai.backend_adotai.entitiy.Documents;

public record ResponseOngDTO(int id,
                             String name,
                             String phone,
                             String cnpj,
                             String email,
                             String pix,
                             Documents documents,
                             ResponseAddressDTO address,
                             boolean status) {
}
