package gov.iti.jets.service.dtos;

import java.io.Serializable;

public class LoginDto implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
    private String phoneNumber;
    private String password;

    public LoginDto(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public  LoginDto(String phoneNumber , String password){
        this(phoneNumber);
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
