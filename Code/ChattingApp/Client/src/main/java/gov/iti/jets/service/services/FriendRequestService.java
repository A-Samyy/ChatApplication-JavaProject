package gov.iti.jets.service.services;


import gov.iti.jets.common.dtos.ClientFriendRequestDto;
import gov.iti.jets.common.dtos.FriendRequestSenderDto;
import gov.iti.jets.common.interfaces.FriendRequestInt;
import gov.iti.jets.networking.RMIRegister;

import java.rmi.RemoteException;
import java.util.List;

public class FriendRequestService {
    RMIRegister rmiRegister = RMIRegister.getInstance();
    FriendRequestInt friendRequestInt = rmiRegister.friendRequestService();


    public FriendRequestService() throws RemoteException {
    }

    public Boolean friendRequest(ClientFriendRequestDto clientFriendRequestDto) throws RemoteException {
        return friendRequestInt.sendFriendRequest(clientFriendRequestDto);
    }

    public List<FriendRequestSenderDto> getFriendRequestsNotifcation() throws RemoteException {
        return friendRequestInt.getFriendRequest(LoginService.getId());
    }

    public boolean acceptingFriendRequest(FriendRequestSenderDto friendRequestSenderDto) throws RemoteException {
        return   friendRequestInt.acceptingFriendRequest(friendRequestSenderDto);
    }

    public boolean rejectingFriendRequest(FriendRequestSenderDto friendRequestSenderDto) throws RemoteException {
        return friendRequestInt.rejectingFriendRequest(friendRequestSenderDto);
    }
}
