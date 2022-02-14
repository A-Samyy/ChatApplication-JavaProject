package gov.iti.jets.service;

import gov.iti.jets.service.dtos.LoginDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginInt extends Remote {
    int isPhoneNumberExist(LoginDto loginDto) throws RemoteException;
    String isPasswordValid(LoginDto loginDto) throws  RemoteException;
}
