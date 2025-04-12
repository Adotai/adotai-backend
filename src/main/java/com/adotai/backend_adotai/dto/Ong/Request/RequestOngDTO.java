package com.adotai.backend_adotai.dto.Ong.Request;

import com.adotai.backend_adotai.entitiy.Documents;

public record RequestOngDTO(String name,
                            String phone,
                            String cnpj,
                            String email,
                            String password,
                            String pix,
                            Documents documents,
                            Integer addressId,
                            boolean status) {
}
