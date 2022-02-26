package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.FileRequestDto;
import gov.iti.jets.common.dtos.MessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerFileRequesInt extends Remote {
    public boolean getNewRequest(FileRequestDto fileRequestDto) throws RemoteException;
    public boolean getResponseRequest(FileRequestDto fileRequestDto) throws RemoteException;

    public boolean register() throws RemoteException;
    public boolean unRegister() throws RemoteException;
}