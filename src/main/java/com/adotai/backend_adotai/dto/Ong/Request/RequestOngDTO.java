package com.adotai.backend_adotai.dto.Ong.Request;

import com.adotai.backend_adotai.entity.Documents;
import com.adotai.backend_adotai.entity.PhotosEntities.OngPhotos;

import java.util.List;

public record RequestOngDTO(String name,
                            String phone,
                            String cnpj,
                            String email,
                            String password,
                            String pix,
                            Documents documents,
                            List<RequestOngPhotosDTO> photos,
                            Integer addressId,
                            boolean status) {
}
