package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.common.interfaces.ClientMesseageInt;
import gov.iti.jets.common.interfaces.ServerMessageGroupInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerMessageGroupImpl extends UnicastRemoteObject implements ServerMessageGroupInt {

    //map<groupId , List Of clients in this group>>
    Map<Integer, List<ClientMesseageInt>> groupClients =new HashMap<>();

    protected ServerMessageGroupImpl() throws RemoteException {
    }

    @Override
    public boolean getMesssage(MessageGroupDto messageGroupDto) throws RemoteException {
        if(groupClients.containsKey(messageGroupDto.getGroupId()) && (groupClients.get(messageGroupDto.getGroupId()) != null)){
                sendMessage(messageGroupDto);
        }

        return false;
    }

    @Override
    public boolean fillGroupInfo(int groupId) throws RemoteException {
        //groupClients.put(groupId , groupDao.getListOfuser);
        return false;
    }


    private void sendMessage(MessageGroupDto messageGroupDto) {
        for(ClientMesseageInt clientMesseageInt : groupClients.get(messageGroupDto.getGroupId())){
            try {
                clientMesseageInt.reciveGroupMessage(messageGroupDto);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
