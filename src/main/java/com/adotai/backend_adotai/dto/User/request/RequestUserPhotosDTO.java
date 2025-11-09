package com.adotai.backend_adotai.dto.User.request;

public class RequestUserPhotosDTO {
    private String photoUrl;

    public RequestUserPhotosDTO() {
    }

    public RequestUserPhotosDTO(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
