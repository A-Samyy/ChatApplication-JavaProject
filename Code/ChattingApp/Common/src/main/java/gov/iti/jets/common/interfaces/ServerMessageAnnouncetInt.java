package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.MessageAnnounceDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerMessageAnnouncetInt extends Remote{
    public boolean getMessage() throws RemoteException;
    public boolean register(ClientAnnounceMessageInt clientAnnounceMessageInt) throws RemoteException;
    public boolean unRegister(ClientAnnounceMessageInt clientAnnounceMessageInt) throws RemoteException;

}
