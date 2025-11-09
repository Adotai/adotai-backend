package com.adotai.backend_adotai.dto.User.response;

public class ResponseUserPhotosDTO {
    private int id;
    private String photoUrl;
    private int userId;

    public ResponseUserPhotosDTO(int id, String photoUrl, int userId) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
