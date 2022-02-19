package gov.iti.jets.service;

import gov.iti.jets.service.dtos.MessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerMessageInt extends Remote {
    public boolean getMesssage(MessageDto messageDto) throws RemoteException;
    public boolean register(ClientMesseageInt clientMesseageInt ,int userId) throws RemoteException;
    public boolean unRegister(ClientMesseageInt clientMesseageInt , int userId) throws RemoteException;
}