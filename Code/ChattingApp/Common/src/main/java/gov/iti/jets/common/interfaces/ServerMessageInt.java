package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.interfaces.ClientMesseageInt;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerMessageInt extends Remote {
    static final long serialVersionUID = 1421672609912364061L;
    public boolean getMesssage(MessageDto messageDto) throws RemoteException;
    public boolean register(ClientMesseageInt clientMesseageInt , int userId) throws RemoteException;
    public boolean unRegister(ClientMesseageInt clientMesseageInt , int userId) throws RemoteException;
}