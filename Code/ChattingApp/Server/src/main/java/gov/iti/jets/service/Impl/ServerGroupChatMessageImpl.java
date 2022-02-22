package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.common.interfaces.ClientGroupChatMessageInt;
import gov.iti.jets.common.interfaces.ServerGroupChatMessageInt;
import gov.iti.jets.presistance.daos.GroupChatUsersDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerGroupChatMessageImpl extends UnicastRemoteObject implements ServerGroupChatMessageInt {

    //map<groupId , List Of clients in this group>>
//    Map<Integer, List<ClientMesseageInt>> groupClients = new HashMap<>();
    GroupChatUsersDao groupChatUsersDao = new GroupChatUsersDao();
    //    ClientMesseageInt clientMesseageInt ;
    // List<ClientGroupChatMessageInt> clientGroupChatMessageInts = new ArrayList<>();
    Map<Integer, ClientGroupChatMessageInt> clients = new HashMap<>();
    //ClientGroupChatMessageInt clientGroupChatMessageInts;
    List<Integer> userIds = new ArrayList<>();

    public ServerGroupChatMessageImpl() throws RemoteException {
    }


    @Override
    public boolean sendGroupChatMessage(MessageGroupDto messageGroupDto) throws RemoteException {

        userIds = groupChatUsersDao.getAllUsersIdFromGroupId(messageGroupDto.getGroupId());
        for (int userId : userIds) {
            try {
                if (userId != messageGroupDto.getSenderId()) {
                    sendMessage(messageGroupDto, userId);
                }
            } catch (Exception e) {
                System.out.println(userId + " user is offline");
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean register(ClientGroupChatMessageInt clientGroupChatMessageInt, int userId) throws RemoteException {
        for (int i=0 ; i<userIds.size();i++) {
            clients.put(userId, clientGroupChatMessageInt);
        }
        return true;
    }

    @Override
    public boolean unregister(ClientGroupChatMessageInt clientGroupChatMessageInt) throws RemoteException {
        return false;
    }


    private void sendMessage(MessageGroupDto messageGroupDto, int userId) {
        try {
            clients.get(messageGroupDto.getSenderId()).receiveGroupChatMessage(messageGroupDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
