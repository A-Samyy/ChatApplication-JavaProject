package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class LoginDto implements Serializable {
    private static final long serialVersionUID = 1420672609912367060L;
    @NotNull(message = "phone number is required")
    @Size(min = 11,max = 11,message = "uncorrected phone number")
    @NotEmpty(message = "phone can't be empty")
    private String phoneNumber ;
    @NotNull(message = "password is required")
    @Size(min = 5,max=20,message = "invalid password")
    private String password;

    public LoginDto(String phoneNumber){
        ValidationMaker.getInstance().validate(this);

        this.phoneNumber=phoneNumber;
    }
    public  LoginDto(String phoneNumber , String password){
        this(phoneNumber);
        ValidationMaker.getInstance().validate(this);

        this.password=password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
