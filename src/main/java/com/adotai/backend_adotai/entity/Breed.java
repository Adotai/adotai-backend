package com.adotai.backend_adotai.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "breeds")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "species_id", nullable = false)
    private Specie species;

    private String name;

    public Breed() {}

    public Breed(Specie species, String name) {
        this.species = species;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Specie getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "id=" + id +
                ", species=" + species +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Breed breed = (Breed) o;
        return id == breed.id && Objects.equals(species, breed.species) && Objects.equals(name, breed.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, species, name);
    }
}
