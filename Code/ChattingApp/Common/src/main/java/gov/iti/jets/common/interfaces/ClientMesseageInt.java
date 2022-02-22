package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.dtos.MessageGroupDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientMesseageInt extends Remote {
    public String reciveMessage(MessageDto messageDto) throws RemoteException;
    public void reciveGroupMessage(MessageGroupDto messageGroupDto, int userId) throws RemoteException;
}
