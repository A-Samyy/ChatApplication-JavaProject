package gov.iti.jets.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerMessageAnnouncetInt extends Remote{
    boolean getMessage() throws RemoteException;
    boolean register(ClientAnnounceMessageInt clientAnnounceMessageInt) throws RemoteException;
    boolean unRegister(ClientAnnounceMessageInt clientAnnounceMessageInt) throws RemoteException;
    void removeList() throws  RemoteException;
}
