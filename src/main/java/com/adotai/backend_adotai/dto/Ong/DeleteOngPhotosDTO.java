package com.adotai.backend_adotai.dto.Ong;

import java.util.List;

public record DeleteOngPhotosDTO(
        Integer ongId,
        List<Integer> photoIdsToDelete
) {}

