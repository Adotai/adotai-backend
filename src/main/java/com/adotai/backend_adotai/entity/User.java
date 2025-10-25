package com.adotai.backend_adotai.entity;

import com.adotai.backend_adotai.entity.enum_types.Role;
import jakarta.persistence.*;

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

    public User() {}

    public User(String name, String cpf, String email, Role role, String password, String telephone, Address address) {
        super.setName(name);
        super.setEmail(email);
        super.setPassword(password);
        this.cpf = cpf;
        this.role = role;
        this.telephone = telephone;
        this.address = address;
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
                Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), cpf, getEmail(), role, getPassword(), telephone, address);
    }
}
