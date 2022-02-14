package gov.iti.jets.service;

import gov.iti.jets.service.dtos.LoginDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

//server
public interface LoginInt extends Remote {
    Boolean isPhoneNumberExist(LoginDto loginDto) throws RemoteException;
    Boolean isPasswordValid(LoginDto loginDto) throws  RemoteException;
}
