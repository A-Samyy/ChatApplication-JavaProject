package gov.iti.jets.service.services;


import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.FriendRequestInt;
import gov.iti.jets.service.RegisterInt;
import gov.iti.jets.service.dtos.ClientFriendRequestDto;
import gov.iti.jets.service.dtos.RegisterDto;

import java.rmi.RemoteException;

public class FriendRequestService {
    RMIRegister rmiRegister = RMIRegister.getInstance();
    FriendRequestInt friendRequestInt= rmiRegister.friendRequestService();


    public FriendRequestService() throws RemoteException {
    }

    public Boolean friendRequest(ClientFriendRequestDto clientFriendRequestDto) throws RemoteException {

         System.out.println(friendRequestInt.sendFriendRequest(clientFriendRequestDto));
         return true;
    }
}
