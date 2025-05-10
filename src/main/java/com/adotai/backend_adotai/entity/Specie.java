package com.adotai.backend_adotai.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "species")
public class Specie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String description;

    private boolean status;

    public Specie() {
    }

    public Specie(String description, boolean status) {
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Specie{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specie specie = (Specie) o;
        return id == specie.id && status == specie.status && Objects.equals(description, specie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status);
    }
}

