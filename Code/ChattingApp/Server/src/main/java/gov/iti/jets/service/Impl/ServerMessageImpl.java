package gov.iti.jets.service.Impl;

import gov.iti.jets.common.interfaces.ClientGroupChatMessageInt;
import gov.iti.jets.common.interfaces.ClientMesseageInt;
import gov.iti.jets.common.interfaces.ServerMessageInt;
import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.service.services.ServerControlService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServerMessageImpl extends UnicastRemoteObject implements  ServerMessageInt{

    Map<Integer,ClientMesseageInt> clients =new HashMap<>();

    public ServerMessageImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean getMesssage(MessageDto messageDto) throws RemoteException {
        System.out.println("mwgd?"+clients.containsKey(messageDto.getFriendId()));
        if(ServerControlService.flag) {
            if (clients.containsKey(messageDto.getFriendId())) {
                System.out.println("friendId " + messageDto.getFriendId());
                sendMessage(messageDto);
                return true;
            }
            return false;
        }
        else{
            System.out.println("system is offline");
            return false;
        }

    }

    @Override
    public boolean register(ClientMesseageInt clientMesseageInt ,int userId) throws RemoteException {
        clients.put(userId,clientMesseageInt);
        if(clients.containsKey(userId)){
            System.out.println("regestred");
            return true;
        }
        return false;
    }

    @Override
    public boolean unRegister(ClientMesseageInt clientMesseageInt ,int userId) throws RemoteException {
        if(clients.remove(userId,clientMesseageInt))
            return true;
        return false;
    }

    public boolean sendMessage(MessageDto messageDto){
        try {
            System.out.println("dakhlt feh el heta ely byndahg feha el reciev"+messageDto.getFriendId());
            clients.get(messageDto.getFriendId()).reciveMessage(messageDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<Integer, ClientMesseageInt> clientsOnline(){
        return this.clients;
    }
}
