package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.MessageGroupDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientGroupChatMessageInt extends Remote {

    public boolean receiveGroupChatMessage(MessageGroupDto messageGroupDto) throws RemoteException;

    }
