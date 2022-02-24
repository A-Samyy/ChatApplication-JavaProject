package gov.iti.jets.presistance.dtos;

import java.io.Serializable;
import java.util.Date;


public class AdminDto implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
    private int adminID;
    private String adminName;
    private String password;



    public AdminDto() {
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
