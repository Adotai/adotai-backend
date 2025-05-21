package com.adotai.backend_adotai.entity;

import com.adotai.backend_adotai.entity.PhotosEntities.AnimalPhotos;
import com.adotai.backend_adotai.entity.PhotosEntities.OngPhotos;
import com.adotai.backend_adotai.entity.enum_types.Gender;
import com.adotai.backend_adotai.entity.enum_types.HealthStatus;
import com.adotai.backend_adotai.entity.enum_types.Temperament;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ong_id", nullable = false)
    private Ong ong;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "animal_description")
    private String animalDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id", nullable = false)
    private Specie species;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private HealthStatus health;

    private boolean status;
    private boolean vaccinated;
    private boolean neutered;
    private boolean dewormed;

    @Enumerated(EnumType.STRING)
    private Temperament temperament;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnimalPhotos> photos;

    public Animal() {}

    public Animal(Ong ong, String name, Gender gender, Color color, Breed breed, Specie species, Integer age, HealthStatus health,
                  boolean status, boolean vaccinated, boolean neutered, boolean dewormed, Temperament temperament,
                  Timestamp createdAt, List<AnimalPhotos> photos, String animalDescription) {
        this.ong = ong;
        this.name = name;
        this.gender = gender;
        this.color = color;
        this.breed = breed;
        this.species = species;
        this.age = age;
        this.health = health;
        this.status = status;
        this.vaccinated = vaccinated;
        this.neutered = neutered;
        this.dewormed = dewormed;
        this.temperament = temperament;
        this.createdAt = createdAt;
        this.photos = photos;
        this.animalDescription = animalDescription;
    }

    public int getId() {
        return id;
    }

    public String getAnimalDescription() {
        return animalDescription;
    }

    public void setAnimalDescription(String animalDescription) {
        this.animalDescription = animalDescription;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Specie getSpecies() {
        return species;
    }

    public void setSpecies(Specie species) {
        this.species = species;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public HealthStatus getHealth() {
        return health;
    }

    public void setHealth(HealthStatus health) {
        this.health = health;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public boolean isDewormed() {
        return dewormed;
    }

    public void setDewormed(boolean dewormed) {
        this.dewormed = dewormed;
    }

    public Temperament getTemperament() {
        return temperament;
    }

    public void setTemperament(Temperament temperament) {
        this.temperament = temperament;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public List<AnimalPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<AnimalPhotos> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", ong=" + ong +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", color=" + color +
                ", breed=" + breed +
                ", species=" + species +
                ", age=" + age +
                ", health=" + health +
                ", status=" + status +
                ", vaccinated=" + vaccinated +
                ", neutered=" + neutered +
                ", dewormed=" + dewormed +
                ", temperament=" + temperament +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", photos=" + photos +
                ", animalDescription='" + animalDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && status == animal.status && vaccinated == animal.vaccinated && neutered == animal.neutered && dewormed == animal.dewormed && Objects.equals(ong, animal.ong) && Objects.equals(name, animal.name) && gender == animal.gender && Objects.equals(color, animal.color) && Objects.equals(breed, animal.breed) && Objects.equals(species, animal.species) && Objects.equals(age, animal.age) && health == animal.health && temperament == animal.temperament && Objects.equals(createdAt, animal.createdAt) && Objects.equals(updatedAt, animal.updatedAt) && Objects.equals(photos, animal.photos) && Objects.equals(animalDescription, animal.animalDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ong, name, gender, color, breed, species, age, health, status, vaccinated, neutered, dewormed, temperament, createdAt, updatedAt , photos, animalDescription);
    }
}
