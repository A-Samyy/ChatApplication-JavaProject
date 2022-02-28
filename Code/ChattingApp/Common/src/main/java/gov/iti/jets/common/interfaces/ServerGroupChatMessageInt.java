package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.MessageGroupDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerGroupChatMessageInt extends Remote {

    public boolean sendGroupChatMessage(MessageGroupDto messageGroupDto) throws RemoteException;
    public boolean register(ClientGroupChatMessageInt clientGroupChatMessageInt , int userId)throws RemoteException;
    public boolean unregister(ClientGroupChatMessageInt clientGroupChatMessageInt,int userid)throws RemoteException;

    }
