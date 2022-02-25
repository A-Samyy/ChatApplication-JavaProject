package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.ClientGroupChatDto;
import gov.iti.jets.common.interfaces.AddGroupChatInt;
import gov.iti.jets.presistance.daos.GroupChatDao;
import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.presistance.dtos.GroupChatDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AddGroupChatImpl extends UnicastRemoteObject implements AddGroupChatInt {
    GroupChatDao groupChatDao = new GroupChatDao();
    GroupChatDto groupChatDto = new GroupChatDto();
    UserDao userDao = new UserDao();
    List<Integer> userIds = new ArrayList<>();


    public AddGroupChatImpl() throws RemoteException {
    }

    @Override
    public Boolean addGroupChat(ClientGroupChatDto clientGroupChatDto) throws RemoteException {
        groupChatDto = mapperToClient(clientGroupChatDto);
        if (groupChatDto.getUsersId().size() > 1) {
            return groupChatDao.addGroup(groupChatDto);
        }
        return false;
    }

    private GroupChatDto mapperToClient(ClientGroupChatDto clientGroupChatDto) {
        GroupChatDto groupChatDto = new GroupChatDto();
        groupChatDto.setGroupName(clientGroupChatDto.getGroupName());
        userIds=mapperToUserIDs(clientGroupChatDto.getUsersId());
        userIds.add(clientGroupChatDto.getGroupCreatorId());
        groupChatDto.setUsersId(userIds);
        return groupChatDto;
    }

    private List<Integer> mapperToUserIDs(List<String> usersNumber) {
        List<Integer> usersIds = new ArrayList<>();
        for (String number : usersNumber) {
            if (userDao.checkUserByPhoneNumber(number)) {
                usersIds.add(userDao.getUserIdByPhoneNumber(number));
            }
        }
        return usersIds;
    }
}
