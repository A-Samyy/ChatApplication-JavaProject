package gov.iti.jets.presistance.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Date;


public class UserDto implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
//    @Positive
    private int userID;
//    @Size(min = 11,max = 11)
//    @NotNull
//    @NotEmpty
    private String phoneNumber;
//    @NotNull
//    @NotEmpty
    private String password;
//    @NotNull
//    @NotEmpty
    private String name;
//    @NotNull
//    @NotEmpty
    private String gender;
//    @Email
    private String email;
//    @NotNull
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private Date dateOfBirth;
//    @NotNull
//    @NotEmpty
    private String picture;
//    @NotNull
//    @NotEmpty

    private String country;
//    @NotNull
//    @NotEmpty
    private String bio;
//    @NotNull
//    @NotEmpty
    private Status status;
    
    public UserDto() {
        //ValidationMaker.getInstance().validate(this);
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Status getStatus() {
        return status;
    }
    public int getStatusNumber() {
        return status.number;
    }
    public void setStatus(Status status) {
        System.out.println("insideUsedDTO "+status);
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userID=" + userID +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", picture='" + picture + '\'' +
                ", country='" + country + '\'' +
                ", bio='" + bio + '\'' +
                ", status=" + status +
                '}';
    }
}
