package com.adotai.backend_adotai.entity;


import com.adotai.backend_adotai.entity.PhotosEntities.OngPhotos;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ongs")
public class Ong extends Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String name;
//    private String email;
//    private String password;

    private String phone;
    private String cnpj;
    private String pix;

    @Column(name = "social_statute")
    private String socialStatute;

    @Column(name = "board_meeting")
    private String boardMeeting;

    @Column(name = "description")
    private String description;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
    private boolean status;

    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OngPhotos> photos;

    public Ong(String name, String phone,String pix ,String cnpj, String email, String password, String socialStatute, String boardMeeting, Address address, boolean status, String description,List<OngPhotos> photos) {
        super.setName(name);
        super.setEmail(email);
        super.setPassword(password);
        this.phone = phone;
        this.pix = pix;
        this.cnpj = cnpj;
        this.socialStatute = socialStatute;
        this.boardMeeting = boardMeeting;
        this.address = address;
        this.status = status;
        this.photos = photos;
        this.description = description;
    }

    public Ong() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OngPhotos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<OngPhotos> photos) {
        this.photos = photos;
    }



    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
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
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", phone='" + phone + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", socialStatute='" + socialStatute + '\'' +
                ", boardMeeting='" + boardMeeting + '\'' +
                ", address=" + address +
                ", status=" + status +
                ", photos=" + photos +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ong ong = (Ong) o;
        return status == ong.status &&
                Objects.equals(getId(), ong.getId()) &&
                Objects.equals(getName(), ong.getName()) &&
                Objects.equals(phone, ong.phone) &&
                Objects.equals(cnpj, ong.cnpj) &&
                Objects.equals(getEmail(), ong.getEmail()) &&
                Objects.equals(getPassword(), ong.getPassword()) &&
                Objects.equals(socialStatute, ong.socialStatute) &&
                Objects.equals(boardMeeting, ong.boardMeeting) &&
                Objects.equals(address, ong.address) &&
                Objects.equals(photos, ong.photos) &&
                Objects.equals(description, ong.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), phone, cnpj, getEmail(), getPassword(), socialStatute, boardMeeting, address, status, photos, description);
    }

}