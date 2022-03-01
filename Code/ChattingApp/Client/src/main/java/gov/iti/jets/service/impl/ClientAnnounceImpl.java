package gov.iti.jets.service.impl;

import gov.iti.jets.common.interfaces.ClientAnnounceMessageInt;
import gov.iti.jets.common.interfaces.ServerMessageAnnouncetInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.services.LoginService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientAnnounceImpl extends UnicastRemoteObject implements ClientAnnounceMessageInt {


    transient RMIRegister rmiRegister = RMIRegister.getInstance();
    transient ServerMessageAnnouncetInt serverMessageAnnouncetInt;
    transient public static Map<Integer,List<String>> mapMessageFromAdmin = new HashMap<>();
    transient public static List<String> messagesFromAdmin = new ArrayList<>();
    transient static ClientAnnounceImpl clientAnnounce;

    public static ClientAnnounceImpl getClientAnnounce() {
        return clientAnnounce;
    }

    public ClientAnnounceImpl() throws RemoteException {
        super();
        clientAnnounce = this;
    }

    public void registerAnnouncetInt() {
        try {
            serverMessageAnnouncetInt = rmiRegister.serverMessageAnnouncetInt();
            serverMessageAnnouncetInt.register(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reciveMessage(List<String> messageAnnounceDto) throws RemoteException {
        int count = 0;
        messagesFromAdmin.clear();
        mapMessageFromAdmin.clear();
        for (String message : messageAnnounceDto) {
            System.out.println((message + " a7san " + count));
            messagesFromAdmin.add(message);
            mapMessageFromAdmin.put(LoginService.getId(),messageAnnounceDto);
            count++;
        }

    }
}
