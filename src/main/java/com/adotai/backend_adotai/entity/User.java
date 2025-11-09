package com.adotai.backend_adotai.entity;

import com.adotai.backend_adotai.entity.PhotosEntities.UserPhotos;
import com.adotai.backend_adotai.entity.enum_types.Gender;
import com.adotai.backend_adotai.entity.enum_types.HouseSize;
import com.adotai.backend_adotai.entity.enum_types.HouseType;
import com.adotai.backend_adotai.entity.enum_types.Role;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User extends Account{

    private String cpf;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String telephone;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "animals_quantity")
    private String animalsQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "house_type")
    private HouseType houseType;

    @Enumerated(EnumType.STRING)
    @Column(name = "house_size")
    private HouseSize houseSize;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPhotos> photos;

    public User() {}

    public User(String name, String cpf, String email, Role role, String password, String telephone, Address address, String description, Date birthDate, Gender gender, String animalsQuantity, HouseType houseType, HouseSize houseSize, List<UserPhotos> photos) {
        super.setName(name);
        super.setEmail(email);
        super.setPassword(password);
        this.cpf = cpf;
        this.role = role;
        this.telephone = telephone;
        this.address = address;
        this.description = description;
        this.birthDate = birthDate;
        this.gender = gender;
        this.animalsQuantity = animalsQuantity;
        this.houseType = houseType;
        this.houseSize = houseSize;
        this.photos = photos;
    }

    public String getCpf() {
        return cpf;
    }



    public Role getRole() {
        return role;
    }




    public String getTelephone() {
        return telephone;
    }

    public Address getAddress() {
        return address;
    }



    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



    public void setRole(Role role) {
        this.role = role;
    }


    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender g) {
        this.gender = g;
    }

    public String getAnimalsQuantity() {
        return animalsQuantity;
    }

    public void setAnimalsQuantity(String animalsQuantity) {
        this.animalsQuantity = animalsQuantity;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public HouseSize getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(HouseSize houseSize) {
        this.houseSize = houseSize;
    }

    public List<UserPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<UserPhotos> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + getEmail() + '\'' +
                ", role='" + role + '\'' +
                ", password='" + getPassword() + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(cpf, user.cpf) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                role == user.role &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(telephone, user.telephone) &&
                Objects.equals(address, user.address) ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), cpf, getEmail(), role, getPassword(), telephone, address);
    }
}
