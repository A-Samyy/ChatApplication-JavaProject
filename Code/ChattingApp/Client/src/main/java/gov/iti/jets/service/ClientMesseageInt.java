package gov.iti.jets.service;

import gov.iti.jets.service.dtos.MessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientMesseageInt extends Remote {
    public String reciveMessage(MessageDto messageDto) throws RemoteException;
}
