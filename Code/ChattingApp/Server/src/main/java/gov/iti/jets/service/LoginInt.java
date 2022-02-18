package gov.iti.jets.service;

import gov.iti.jets.service.dtos.LoginDto;
import gov.iti.jets.service.dtos.UserHomePageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

//server
public interface LoginInt extends Remote {
    int isPhoneNumberExist(LoginDto loginDto) throws RemoteException;
    String isPasswordValid() throws  RemoteException;
    UserHomePageDto getUserById(int Id) throws RemoteException;
}
