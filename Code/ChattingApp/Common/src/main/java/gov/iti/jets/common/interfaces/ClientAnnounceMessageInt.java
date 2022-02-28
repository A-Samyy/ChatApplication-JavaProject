package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.dtos.MessageGroupDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientAnnounceMessageInt extends Remote {
    public void reciveMessage(List<String> messageAnnounceDto) throws RemoteException;
}
