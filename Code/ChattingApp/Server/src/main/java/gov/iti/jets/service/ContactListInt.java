package gov.iti.jets.service;

import gov.iti.jets.service.dtos.ContactDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ContactListInt extends Remote {
    List<ContactDto> getListContact (int id) throws RemoteException;
}