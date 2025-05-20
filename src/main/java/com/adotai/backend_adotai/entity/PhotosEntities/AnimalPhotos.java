package com.adotai.backend_adotai.entity.PhotosEntities;

import com.adotai.backend_adotai.entity.Animal;
import jakarta.persistence.*;

@Entity
@Table(name = "animal_photos")
public class AnimalPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    private String photoUrl;

    public AnimalPhotos() {}

    public AnimalPhotos(Integer id, Animal animal, String photoUrl) {
        this.id = id;
        this.animal = animal;
        this.photoUrl = photoUrl;
    }

    public AnimalPhotos(Animal animal, String photoUrl) {
        this.animal = animal;
        this.photoUrl = photoUrl;
    }


    public Integer getId() {return id;}
    public Animal getAnimal() {return animal;}
    public String getPhotoUrl() {return photoUrl;}
    public void setAnimal(Animal animal) {this.animal = animal;}
    public void setPhotoUrl(String photoUrl) {this.photoUrl = photoUrl;}



}
