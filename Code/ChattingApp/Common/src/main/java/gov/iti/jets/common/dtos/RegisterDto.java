package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;

public class RegisterDto implements Serializable {
    private static final long serialVersionUID = 1420672669912364060L;
//    @NotNull
//    @NotEmpty
//    @Size(min = 11,max = 11)
    private String phoneNumber;
//    @NotNull
//    @NotEmpty
    private String password;
//    @NotNull
//    @NotEmpty
    private String name;
//    @NotNull
    private String gender;
//    @Email
    private String email;
    private Date dateOfBirth;
//    @NotNull
    private String picture;
//    @NotEmpty
    private String country;
//    @NotEmpty
    private String bio;

    public RegisterDto() {
        //ValidationMaker.getInstance().validate(this);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", picture='" + picture + '\'' +
                ", country='" + country + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}
