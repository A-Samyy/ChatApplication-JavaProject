package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.UpdateDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpdateUserInt extends Remote {
    Boolean updateUser (UpdateDto updateDto) throws RemoteException;
}
