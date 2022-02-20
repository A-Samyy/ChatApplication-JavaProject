package gov.iti.jets.common.interfaces;


import gov.iti.jets.common.dtos.RegisterDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

//server
public interface RegisterInt extends Remote {
    Boolean addUser(RegisterDto registerDTO) throws RemoteException;

}
