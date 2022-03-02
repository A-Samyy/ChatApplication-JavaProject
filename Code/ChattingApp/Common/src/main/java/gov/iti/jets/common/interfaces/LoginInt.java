package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.LoginDto;
import gov.iti.jets.common.dtos.UserHomePageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

//server
public interface LoginInt extends Remote {
    int isPhoneNumberExist(LoginDto loginDto) throws RemoteException;
    String isPasswordValid() throws  RemoteException;
    UserHomePageDto getUserById(int Id) throws RemoteException;
     void setLoginCounter() throws RemoteException;

}
