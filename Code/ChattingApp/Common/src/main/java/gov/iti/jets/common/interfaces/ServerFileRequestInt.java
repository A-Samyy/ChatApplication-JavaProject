package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.FileRequestDto;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerFileRequestInt extends Remote {
    public boolean getNewRequest(FileRequestDto fileRequestDto) throws RemoteException;
    public boolean acceptingFileRequest(FileRequestDto fileRequestDto) throws RemoteException;

    public boolean register(ClientFileRequestInt clientFileRequestInt , int userId) throws RemoteException;
    public boolean unRegister(ClientFileRequestInt clientFileRequestInt , int userId) throws RemoteException;
}