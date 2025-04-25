package com.adotai.backend_adotai.dto.Ong.Response;

public class ResponseOngPhotosDTO {
    private int id;
    private String photoUrl;
    private int ongId;

    public ResponseOngPhotosDTO(int id, String photoUrl, int ongId) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.ongId = ongId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getOngId() {
        return ongId;
    }

    public void setOngId(int ongId) {
        this.ongId = ongId;
    }
}