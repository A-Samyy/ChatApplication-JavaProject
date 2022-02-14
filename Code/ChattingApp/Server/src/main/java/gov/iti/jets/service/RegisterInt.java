package gov.iti.jets.service;

import gov.iti.jets.service.dtos.LoginDto;
import gov.iti.jets.service.dtos.RegisterDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

//server
public interface RegisterInt extends Remote {
    Boolean addUser(RegisterDto registerDTO) throws RemoteException;

}
