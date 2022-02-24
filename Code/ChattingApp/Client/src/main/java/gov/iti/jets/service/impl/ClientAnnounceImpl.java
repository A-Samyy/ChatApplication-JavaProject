package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.common.interfaces.ClientAnnounceMessageInt;
import gov.iti.jets.common.interfaces.ServerMessageAnnouncetInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.services.LoginService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientAnnounceImpl extends UnicastRemoteObject implements ClientAnnounceMessageInt {

    RMIRegister rmiRegister=RMIRegister.getInstance();
    ServerMessageAnnouncetInt serverMessageAnnouncetInt;
    public ClientAnnounceImpl() throws RemoteException {
        super();
       serverMessageAnnouncetInt=rmiRegister.serverAnnouncement();
        System.out.println("registerd : "+serverMessageAnnouncetInt.register(this));
    }

    @Override
    public void reciveMessage(MessageAnnounceDto messageAnnounceDto) throws RemoteException {
        System.out.println(messageAnnounceDto.toString());
    }
}