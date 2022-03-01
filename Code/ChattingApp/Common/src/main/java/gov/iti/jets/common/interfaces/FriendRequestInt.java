package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.ClientFriendRequestDto;
import gov.iti.jets.common.dtos.FriendRequestSenderDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

//server
public interface FriendRequestInt extends Remote {
    static final long serialVersionUID = 1420672609912364067L;
    Boolean sendFriendRequest(ClientFriendRequestDto clientFriendRequestDto) throws RemoteException;
    List<FriendRequestSenderDto> getFriendRequest(int userId) throws RemoteException;
    Boolean acceptingFriendRequest(FriendRequestSenderDto friendRequestSenderDto)throws RemoteException;
    Boolean rejectingFriendRequest(FriendRequestSenderDto friendRequestSenderDto) throws RemoteException;
}
