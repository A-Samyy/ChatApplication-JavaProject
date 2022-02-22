package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.common.interfaces.ClientGroupChatMessageInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientGroupChatMessageImpl extends UnicastRemoteObject implements ClientGroupChatMessageInt {
    public ClientGroupChatMessageImpl() throws RemoteException {
    }

    @Override
    public boolean receiveGroupChatMessage(MessageGroupDto messageGroupDto) throws RemoteException {
        System.out.println(messageGroupDto.toString());
        return false;
    }
}
