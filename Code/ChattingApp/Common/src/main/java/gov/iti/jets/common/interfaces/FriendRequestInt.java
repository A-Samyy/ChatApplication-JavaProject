package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.ClientFriendRequestDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

//server
public interface FriendRequestInt extends Remote {
    Boolean sendFriendRequest(ClientFriendRequestDto clientFriendRequestDto) throws RemoteException;
}
