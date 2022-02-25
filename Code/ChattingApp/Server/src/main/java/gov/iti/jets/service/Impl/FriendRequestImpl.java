package gov.iti.jets.service.Impl;


import gov.iti.jets.common.dtos.ClientFriendRequestDto;
import gov.iti.jets.common.dtos.FriendRequestSenderDto;
import gov.iti.jets.common.interfaces.FriendRequestInt;
import gov.iti.jets.presistance.daos.ContactDao;
import gov.iti.jets.presistance.daos.FriendRequestDao;
import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.presistance.dtos.ContactDto;
import gov.iti.jets.presistance.dtos.FriendRequestDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestImpl extends UnicastRemoteObject implements FriendRequestInt {
    FriendRequestDao friendRequestDao = new FriendRequestDao();
    UserDao userDao = new UserDao();

    public FriendRequestImpl() throws RemoteException {
    }


    @Override
    public Boolean sendFriendRequest(ClientFriendRequestDto clientFriendRequestDto) throws RemoteException {
        FriendRequestDto friendRequestDto;
        friendRequestDto = mapperToFriendRequestDto(clientFriendRequestDto);
        if (isUserExist(clientFriendRequestDto)) { //check if the phone number actually exist in the database
            if (!areTheyalreadyFriends(friendRequestDto)) {// Check if they are already friends from the contacts table before trying to add them
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
            } else
                return false;
        } else
            return false;
    }

    private boolean isUserExist(ClientFriendRequestDto clientFriendRequestDto) {
        return userDao.checkUserByPhoneNumber(clientFriendRequestDto.getFriendPhoneNumber());
    }

    @Override
    public List<FriendRequestSenderDto> getFriendRequest(int userId) throws RemoteException {
        List<FriendRequestDto> friendRequestDtoList = new ArrayList<>();
        friendRequestDtoList = friendRequestDao.getAllFriendRequestsForUser(userId);
        return mapperToFriendRequestSenderDto(friendRequestDtoList);
    }

    @Override
    public Boolean acceptingFriendRequest(FriendRequestSenderDto friendRequestSenderDto) throws RemoteException {
        ContactDao contactDao = new ContactDao();
        boolean check1 = contactDao.addFriendDto(mapperToContactDto(friendRequestSenderDto));
        boolean check2 = friendRequestDao.deleteFriendRequest(mapperToFriendRequestDto(friendRequestSenderDto));
        return check1 && check2;
    }

    @Override
    public Boolean rejectingFriendRequest(FriendRequestSenderDto friendRequestSenderDto) throws RemoteException {
        return friendRequestDao.deleteFriendRequest(mapperToFriendRequestDto(friendRequestSenderDto));
    }

    private boolean areTheyalreadyFriends(FriendRequestDto friendRequestDto) {
        ContactDao contactDao = new ContactDao();
        ContactDto contactDto = mapperToContactDto(friendRequestDto);
        return contactDao.checkIfUserHasFriend(contactDto);
    }


    private List<FriendRequestSenderDto> mapperToFriendRequestSenderDto(List<FriendRequestDto> friendRequestDtoList) {
        List<FriendRequestSenderDto> friendRequestSenderDtoList = new ArrayList<>();
        for (FriendRequestDto friendRequestDto : friendRequestDtoList) {
            FriendRequestSenderDto friendRequestSenderDto = new FriendRequestSenderDto();
            friendRequestSenderDto.setSenderId(friendRequestDto.getUserId());
            friendRequestSenderDto.setSenderName(userDao.getUserNameById(friendRequestDto.getUserId()));
            friendRequestSenderDto.setUserId(friendRequestDto.getFriendId());
            friendRequestSenderDtoList.add(friendRequestSenderDto);
        }
        return friendRequestSenderDtoList;
    }

    private FriendRequestDto mapperToFriendRequestDto(ClientFriendRequestDto clientFriendRequestDto) {
        FriendRequestDto friendRequestDto = new FriendRequestDto();
        friendRequestDto.setUserId(clientFriendRequestDto.getUserId());
        friendRequestDto.setFriendId(userDao.getUserIdByPhoneNumber(clientFriendRequestDto.getFriendPhoneNumber()));
        return friendRequestDto;
    }

    private FriendRequestDto mapperToFriendRequestDto(FriendRequestSenderDto friendRequestSenderDto) {
        FriendRequestDto friendRequestDto = new FriendRequestDto();
        friendRequestDto.setUserId(friendRequestSenderDto.getSenderId());
        friendRequestDto.setFriendId(friendRequestSenderDto.getUserId());
        return friendRequestDto;
    }

    private ContactDto mapperToContactDto(FriendRequestDto friendRequestDto) {
        ContactDto contactDto = new ContactDto();
        contactDto.setUserId(friendRequestDto.getUserId());
        contactDto.setFriendId(friendRequestDto.getFriendId());
        return contactDto;
    }

    private ContactDto mapperToContactDto(FriendRequestSenderDto friendRequestSenderDto) {
        ContactDto contactDto = new ContactDto();
        contactDto.setUserId(friendRequestSenderDto.getUserId());
        contactDto.setFriendId(friendRequestSenderDto.getSenderId());
        return contactDto;
    }
}
