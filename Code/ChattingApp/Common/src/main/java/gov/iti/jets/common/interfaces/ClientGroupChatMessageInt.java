package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.MessageGroupDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientGroupChatMessageInt extends Remote {
    static final long serialVersionUID = 1420672609912364064L;
    public boolean receiveGroupChatMessage(MessageGroupDto messageGroupDto) throws RemoteException;

    }
