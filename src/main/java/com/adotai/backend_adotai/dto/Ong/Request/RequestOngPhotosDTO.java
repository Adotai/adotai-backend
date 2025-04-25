package com.adotai.backend_adotai.dto.Ong.Request;

public class RequestOngPhotosDTO {
    private String photoUrl;

    public RequestOngPhotosDTO() {
    }

    public RequestOngPhotosDTO(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}