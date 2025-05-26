package com.adotai.backend_adotai.dto.Animal.Request;

public class RequestAnimalPhotosDTO {

    private Integer id; // adicione este campo
    private String photoUrl;

    public RequestAnimalPhotosDTO() {}

    public RequestAnimalPhotosDTO(Integer id, String photoUrl) {
        this.id = id;
        this.photoUrl = photoUrl;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}
