package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.ClientGroupChatDto;
import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.interfaces.AddGroupChatInt;
import gov.iti.jets.common.interfaces.ContactListInt;
import gov.iti.jets.presistance.daos.ContactDao;
import gov.iti.jets.presistance.daos.GroupChatDao;
import gov.iti.jets.presistance.dtos.GroupChatDto;
import gov.iti.jets.presistance.dtos.UserDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AddGroupChatImpl extends UnicastRemoteObject implements AddGroupChatInt {
GroupChatDao groupChatDao =new GroupChatDao();
    public AddGroupChatImpl() throws RemoteException {
    }
    @Override
    public Boolean addGroupChat(ClientGroupChatDto clientGroupChatDto) throws RemoteException {
        return  groupChatDao.addGroup(mapperToClient(clientGroupChatDto));
    }

    private GroupChatDto mapperToClient(ClientGroupChatDto clientGroupChatDto) {
        GroupChatDto groupChatDto=new GroupChatDto();
        groupChatDto.setGroupName(clientGroupChatDto.getGroupName());
        groupChatDto.setUsersId(clientGroupChatDto.getUsersId());
        return groupChatDto;
    }
}
