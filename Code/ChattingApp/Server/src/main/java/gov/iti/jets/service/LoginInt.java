package gov.iti.jets.service;

import gov.iti.jets.service.dtos.LoginDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

//server
public interface LoginInt extends Remote {
    int isPhoneNumberExist(LoginDto loginDto) throws RemoteException;
    String isPasswordValid() throws  RemoteException;
}
