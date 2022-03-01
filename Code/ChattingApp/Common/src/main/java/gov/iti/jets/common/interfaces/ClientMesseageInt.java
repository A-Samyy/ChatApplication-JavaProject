package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.dtos.MessageGroupDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientMesseageInt extends Remote {
    static final long serialVersionUID = 1420672609912364065L;
    public String reciveMessage(MessageDto messageDto) throws RemoteException;
    public void reciveGroupMessage(MessageGroupDto messageGroupDto, int userId) throws RemoteException;
}
