package gov.iti.jets.service.Impl;


import gov.iti.jets.common.dtos.FileRequestDto;
import gov.iti.jets.common.interfaces.ClientFileRequestInt;
import gov.iti.jets.common.interfaces.ServerFileRequestInt;
import gov.iti.jets.service.services.ServerControlService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServerFileRequestImpl extends UnicastRemoteObject implements ServerFileRequestInt {

    Map<Integer, ClientFileRequestInt> clients =new HashMap<>();


    public ServerFileRequestImpl() throws RemoteException {
    }


    @Override
    public boolean getNewRequest(FileRequestDto fileRequestDto) throws RemoteException {
        if(ServerControlService.flag) {
            if (clients.containsKey(fileRequestDto.getReceiverId())) {
                return sendNewRequest(fileRequestDto);
            }
            return false;
        }
        else{
             return false;
        }
     }

    private boolean sendNewRequest(FileRequestDto fileRequestDto) {
        try {
            return clients.get(fileRequestDto.getReceiverId()).receiveOutSideRequest(fileRequestDto);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // on Accept File Request
    public boolean acceptingFileRequest(FileRequestDto fileRequestDto) throws RemoteException {
        return clients.get(fileRequestDto.getSenderId()).receiveMyRequest(fileRequestDto);
    }

    @Override
    public boolean register(ClientFileRequestInt clientFileRequestInt,int userId) throws RemoteException {
        clients.put(userId,clientFileRequestInt);
        if(clients.containsKey(userId)){
             return true;
        }
        return false;
    }

    @Override
    public boolean unRegister(ClientFileRequestInt clientFileRequestInt , int userId) throws RemoteException {
        return clients.remove(userId, clientFileRequestInt);
    }
}
