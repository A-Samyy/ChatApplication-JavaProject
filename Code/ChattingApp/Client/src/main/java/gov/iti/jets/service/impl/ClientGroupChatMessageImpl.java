package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.LoginDto;
import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.common.interfaces.ClientGroupChatMessageInt;
import gov.iti.jets.common.interfaces.ServerGroupChatMessageInt;
import gov.iti.jets.common.interfaces.ServerMessageGroupInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.services.LoginService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientGroupChatMessageImpl extends UnicastRemoteObject implements ClientGroupChatMessageInt {

    private RMIRegister rmiRegister=RMIRegister.getInstance();
    private ServerGroupChatMessageInt serverGroupChatMessageInt;

    public ClientGroupChatMessageImpl() throws RemoteException {
        super();
        serverGroupChatMessageInt=rmiRegister.groupChatMessageService();
        serverGroupChatMessageInt.register(this, LoginService.getId());
    }

    @Override
    public boolean receiveGroupChatMessage(MessageGroupDto messageGroupDto) throws RemoteException {
        System.out.println(messageGroupDto.toString());
        return false;
    }
}
