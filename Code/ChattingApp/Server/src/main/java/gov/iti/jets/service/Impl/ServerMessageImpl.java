package gov.iti.jets.service.Impl;

import gov.iti.jets.service.ClientMesseageInt;
import gov.iti.jets.service.ServerMessageInt;
import gov.iti.jets.service.dtos.MessageDto;

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
        if (clients.containsKey(messageDto.getFriendId())){
            sendMessage(messageDto);
            return true;
        }
        return false;
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
        if (clients.remove(userId,clientMesseageInt))
            return true;
        return false;
    }

    public boolean sendMessage(MessageDto messageDto){
        try {
            System.out.println(messageDto.getFriendId());
            clients.get(messageDto.getFriendId()).reciveMessage(messageDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
