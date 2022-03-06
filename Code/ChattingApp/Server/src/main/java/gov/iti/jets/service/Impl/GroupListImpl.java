package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.GroupDto;
import gov.iti.jets.common.interfaces.GroupListInt;
import gov.iti.jets.presistance.daos.GroupChatDao;
import gov.iti.jets.presistance.daos.GroupChatUsersDao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class GroupListImpl extends UnicastRemoteObject implements GroupListInt {
    GroupChatUsersDao groupChatUsersDao = new GroupChatUsersDao();
    GroupChatDao groupChatDao = new GroupChatDao();

    public GroupListImpl() throws RemoteException {
    }

    @Override
    public List<GroupDto> getListGroup(int id) throws RemoteException {
        List<Integer> listOfGroupId = groupChatUsersDao.getAllGroupsIdforUser(id);
        List<GroupDto> listOfGroup = new ArrayList<>();
        for (Integer idOfUser : listOfGroupId) {
            GroupDto groupDto = new GroupDto();
            groupDto.setId(idOfUser);
            groupDto.setGroupName(groupChatDao.getGroupsNameById(idOfUser));
            listOfGroup.add(groupDto);
        }
        return listOfGroup;
    }
}
