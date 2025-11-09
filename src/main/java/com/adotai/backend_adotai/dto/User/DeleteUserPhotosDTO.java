package com.adotai.backend_adotai.dto.User;

import java.util.List;

public record DeleteUserPhotosDTO(Integer id, List<Integer> photoIdsToDelete) {
}
