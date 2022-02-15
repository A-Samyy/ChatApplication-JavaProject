package gov.iti.jets.service.services;

import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.dtos.LoginDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LoginService {

    private int userId ;
    LoginInt loginInt = null ;
    LoginDto loginDto = null ;

    public LoginService(){
        Registry reg;
        try {
            reg = LocateRegistry.getRegistry("localhost" , 2022);
            loginInt= (LoginInt) reg.lookup("login");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public int getUserId(String phoneNumber) throws RemoteException {
        this.loginDto=new LoginDto(phoneNumber);
        this.userId=this.loginInt.isPhoneNumberExist(this.loginDto);
        return this.userId;
    }

    public String getPassword() throws RemoteException {
        return this.loginInt.isPasswordValid();
    }





}
