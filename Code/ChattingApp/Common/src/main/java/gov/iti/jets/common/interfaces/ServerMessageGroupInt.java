package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.dtos.MessageGroupDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerMessageGroupInt extends Remote {

    public boolean getMesssage(MessageGroupDto messageGroupDto) throws RemoteException;
    public boolean fillGroupInfo(int groupId) throws RemoteException;
}
