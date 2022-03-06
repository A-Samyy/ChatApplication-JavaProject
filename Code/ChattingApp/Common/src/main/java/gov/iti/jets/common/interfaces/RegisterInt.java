package gov.iti.jets.common.interfaces;


import gov.iti.jets.common.dtos.RegisterDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

//server
public interface RegisterInt extends Remote {
    static final long serialVersionUID = 1420672609912364010L;
    Boolean addUser(RegisterDto registerDTO) throws RemoteException;

}
