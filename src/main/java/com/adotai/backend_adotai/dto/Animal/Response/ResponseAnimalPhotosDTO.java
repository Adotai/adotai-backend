package com.adotai.backend_adotai.dto.Animal.Response;

public class ResponseAnimalPhotosDTO {
    private Integer id;
    private String photoUrl;
    private Integer animalId;

    public ResponseAnimalPhotosDTO(Integer id, String photoUrl, Integer animalId) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.animalId = animalId;
    }

    public Integer getId() {
        return id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Integer getAnimalId() {
        return animalId;
    }
}
