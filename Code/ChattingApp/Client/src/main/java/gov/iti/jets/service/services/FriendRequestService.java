package gov.iti.jets.service.services;


import gov.iti.jets.common.dtos.ClientFriendRequestDto;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.common.interfaces.FriendRequestInt;
import java.rmi.RemoteException;
//import gov.iti.jets.common.dtos.;


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
