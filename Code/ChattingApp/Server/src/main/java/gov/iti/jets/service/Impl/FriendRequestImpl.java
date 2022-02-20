package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.ClientFriendRequestDto;
import gov.iti.jets.common.interfaces.FriendRequestInt;
import gov.iti.jets.presistance.daos.FriendRequestDao;
import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.presistance.dtos.FriendRequestDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FriendRequestImpl extends UnicastRemoteObject implements FriendRequestInt {
    FriendRequestDao friendRequestDao = new FriendRequestDao();
    UserDao userDao = new UserDao();

    public FriendRequestImpl() throws RemoteException {
    }


    @Override
    public Boolean sendFriendRequest(ClientFriendRequestDto clientFriendRequestDto) throws RemoteException {
        FriendRequestDto friendRequestDto;
        friendRequestDto = mapper(clientFriendRequestDto);
        int i = friendRequestDao.addFriendRequestDto(friendRequestDto);
        if (i == 1) {
            System.out.println("Request is added");
            return true;
        }
        if (i == 0) {
            System.out.println("they tried to add each other and they are friends now");
            return true;
        } else {
            System.out.println("nothing happened");
            return false;
        }
    }


    private FriendRequestDto mapper(ClientFriendRequestDto clientFriendRequestDto) {
        FriendRequestDto friendRequestDto = new FriendRequestDto();
        friendRequestDto.setUserId(clientFriendRequestDto.getUserId());
        friendRequestDto.setFriendId(userDao.getUserIdByPhoneNumber(clientFriendRequestDto.getFriendPhoneNumber()));
        return friendRequestDto;
    }
}
