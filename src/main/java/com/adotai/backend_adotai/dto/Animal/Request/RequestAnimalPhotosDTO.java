package com.adotai.backend_adotai.dto.Animal.Request;

public class RequestAnimalPhotosDTO {

    private String photoUrl;

    public RequestAnimalPhotosDTO() {
    }

    public RequestAnimalPhotosDTO(String photoUrl) {

        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {

        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {

        this.photoUrl = photoUrl;
    }
}
