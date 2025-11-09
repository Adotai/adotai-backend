package com.adotai.backend_adotai.entity.PhotosEntities;

import com.adotai.backend_adotai.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_photos")
public class UserPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String photoUrl;

    public UserPhotos() {}

    public UserPhotos(Integer id, User user, String photoUrl) {
        this.id = id;
        this.user = user;
        this.photoUrl = photoUrl;
    }

    public UserPhotos(Integer id, String photoUrl) {
        this.id = id;
        this.photoUrl = photoUrl;
    }

    public UserPhotos(User user, String photoUrl) {
        this.user = user;
        this.photoUrl = photoUrl;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}