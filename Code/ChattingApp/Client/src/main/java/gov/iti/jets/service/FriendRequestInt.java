package gov.iti.jets.service;

import gov.iti.jets.service.dtos.ClientFriendRequestDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

//server
public interface FriendRequestInt extends Remote {
    Boolean sendFriendRequest(ClientFriendRequestDto clientFriendRequestDto) throws RemoteException;
}
