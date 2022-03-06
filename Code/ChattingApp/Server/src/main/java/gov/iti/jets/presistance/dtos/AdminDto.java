package gov.iti.jets.presistance.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;


public class AdminDto implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
    @Positive
    private int adminID;
    @NotNull
    private String adminName;
    @NotNull
    @Positive
    private String password;



    public AdminDto() {
        //ValidationMaker.getInstance().validate(this);
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminDto{" +
                "adminID=" + adminID +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
