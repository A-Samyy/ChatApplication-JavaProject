package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.ContactDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ContactListInt extends Remote {
    List<ContactDto> getListContact (int id) throws RemoteException;
}