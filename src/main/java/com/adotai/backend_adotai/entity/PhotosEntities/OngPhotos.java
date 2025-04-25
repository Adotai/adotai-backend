package com.adotai.backend_adotai.entity.PhotosEntities;


import com.adotai.backend_adotai.entity.Ong;
import jakarta.persistence.*;


@Entity
@Table(name = "ong_photos")
public class OngPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ong_id", nullable = false)
    private Ong ong;

    private String photoUrl;


    public OngPhotos() {}

    public OngPhotos(Integer id, Ong ong, String photoUrl) {
        this.id = id;
        this.ong = ong;
        this.photoUrl = photoUrl;
        }

    public OngPhotos(Ong ong, String photoUrl) {
        this.ong = ong;
        this.photoUrl = photoUrl;
    }


    public Integer getId() {return id;}
    public Ong getOng() {return ong;}
    public String getPhotoUrl() {return photoUrl;}
    public void setOng(Ong ong) {this.ong = ong;}
    public void setPhotoUrl(String photoUrl) {this.photoUrl = photoUrl;}
}