package gov.iti.jets.service.Impl;

import gov.iti.jets.presistance.daos.ContactDao;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.service.ContactListInt;
import gov.iti.jets.service.dtos.ContactDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ContactListImpl extends UnicastRemoteObject implements ContactListInt {
    ContactDao contactDao=  new ContactDao();
    public ContactListImpl() throws RemoteException {
    }


    @Override
    public List<ContactDto> getListContact(int id) throws RuntimeException {
       return mappingUserDtoToContactDto(contactDao.getAllUserFriendsById(id));
    }

    private List<ContactDto> mappingUserDtoToContactDto(List<UserDto> allUserFriendsById) {
        List<ContactDto> contactDtoList = null;
        for(UserDto user : allUserFriendsById){
            ContactDto contactDto = new ContactDto();
            contactDto.setFriendName(user.getName());
            contactDto.setPicture(user.getPicture());
            contactDto.setStatus(user.getStatus().toString());
            contactDtoList.add(contactDto);
        }
        return contactDtoList;
    }
}
