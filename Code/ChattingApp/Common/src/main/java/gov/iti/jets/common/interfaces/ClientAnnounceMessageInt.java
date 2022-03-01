package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.dtos.MessageGroupDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientAnnounceMessageInt extends Remote {
    static final long serialVersionUID = 1420672609912364062L;
    public void reciveMessage(List<String> messageAnnounceDto) throws RemoteException;
}
