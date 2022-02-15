package gov.iti.jets.service.services;

import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.dtos.LoginDto;
import java.rmi.RemoteException;

public class LoginService {
    private int userId;
    RMIRegister rmiRegister = RMIRegister.getInstance();
    LoginInt loginInt = rmiRegister.loginService();
    LoginDto loginDto = null;

    public LoginService() {

    }

    public int getUserId(String phoneNumber) throws RemoteException {

        this.loginDto = new LoginDto(phoneNumber);
        this.userId = this.loginInt.isPhoneNumberExist(this.loginDto);
        return this.userId;
    }

    public String getPassword() throws RemoteException {
        return this.loginInt.isPasswordValid();
    }


}
