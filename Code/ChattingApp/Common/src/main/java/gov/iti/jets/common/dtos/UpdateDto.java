package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

public class UpdateDto implements Serializable {
    private static final long serialVersionUID = 1420672669912364060L;
//    @Positive
    private int Id;
//    @NotEmpty
//    @NotNull
    private String password;
//    @Size(min = 11,max = 11)
//    @NotNull
    private String phoneNumber;
//    @NotNull
    private String name;
//    @Email
    private String email;
//    @NotNull
    private String picture;
//    @NotNull
    private String bio;
//    @NotNull
    private String status;

    public UpdateDto() {
        //ValidationMaker.getInstance().validate(this);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateDto{" +
                "Id=" + Id +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", bio='" + bio + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
