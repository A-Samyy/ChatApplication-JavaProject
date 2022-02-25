package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.common.interfaces.ClientAnnounceMessageInt;
import gov.iti.jets.common.interfaces.ClientMesseageInt;
import gov.iti.jets.common.interfaces.ServerMessageAnnouncetInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerMessageAnnounceImpl extends UnicastRemoteObject implements ServerMessageAnnouncetInt {

    private List<ClientAnnounceMessageInt> clientAnnounceMessageIntList = new ArrayList<>();
    MessageAnnounceDto messageAnnounceDto1 ;

    public ServerMessageAnnounceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean getMessage() throws RemoteException {
        sendMessage(messageAnnounceDto1);
        return false;
    }

    @Override
    public boolean register(ClientAnnounceMessageInt clientAnnounceMessageInt) throws RemoteException {
        clientAnnounceMessageIntList.add(clientAnnounceMessageInt);
        return true;
    }

    @Override
    public boolean unRegister(ClientAnnounceMessageInt clientAnnounceMessageInt) throws RemoteException {
        return false;
    }

    public boolean sendMessage(MessageAnnounceDto messageAnnounceDto){

            for(ClientAnnounceMessageInt client : clientAnnounceMessageIntList){
                try {
                    client.reciveMessage(messageAnnounceDto);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        return true ;
    }
    /*
    public void retMessage(MessageAnnounceDto messageAnnounceDto){
        this.messageAnnounceDto1=messageAnnounceDto;
    }
    */
}
