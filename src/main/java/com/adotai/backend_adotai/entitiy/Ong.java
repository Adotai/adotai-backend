package com.adotai.backend_adotai.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "ongs")
public class Ong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String phone;
    private String cnpj;
    private String email;
    private String password;
    private String pix;

    @Column(name = "social_statute")
    private String socialStatute;

    @Column(name = "board_meeting")
    private String boardMeeting;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    private boolean status;

    public Ong(String name, String phone, String cnpj, String email, String password, String socialStatute, String boardMeeting, Address address, boolean status) {
        this.name = name;
        this.phone = phone;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.socialStatute = socialStatute;
        this.boardMeeting = boardMeeting;
        this.address = address;
        this.status = status;
    }

    public Ong() {}

    public int getId() {
        return id;
    }

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSocialStatute() {
        return socialStatute;
    }

    public void setSocialStatute(String socialStatute) {
        this.socialStatute = socialStatute;
    }

    public String getBoardMeeting() {
        return boardMeeting;
    }

    public void setBoardMeeting(String boardMeeting) {
        this.boardMeeting = boardMeeting;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ong{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", socialStatute='" + socialStatute + '\'' +
                ", boardMeeting='" + boardMeeting + '\'' +
                ", address=" + address +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ong ong = (Ong) o;
        return id == ong.id && status == ong.status && Objects.equals(name, ong.name) && Objects.equals(phone, ong.phone) && Objects.equals(cnpj, ong.cnpj) && Objects.equals(email, ong.email) && Objects.equals(password, ong.password) && Objects.equals(socialStatute, ong.socialStatute) && Objects.equals(boardMeeting, ong.boardMeeting) && Objects.equals(address, ong.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, cnpj, email, password, socialStatute, boardMeeting, address, status);
    }
}