package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.FileRequestDto;
import gov.iti.jets.common.interfaces.ClientFileRequestInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientFileRequestImpl extends UnicastRemoteObject implements ClientFileRequestInt {

    protected ClientFileRequestImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean receiveMyRequest(FileRequestDto fileRequestDto) throws RemoteException {
        return false;
    }

    @Override
    public boolean receiveOutSideRequest(FileRequestDto fileRequestDto) throws RemoteException {
        return false;
    }

    public boolean sendMyRequest(){

        return false;
    }

    public boolean sendResponseRequest(){

        return false;
    }
}
