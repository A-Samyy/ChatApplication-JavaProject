package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.FileRequestDto;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientFileRequestInt extends Remote {
    static final long serialVersionUID = 1420672609912364063L;
    public boolean receiveMyRequest(FileRequestDto fileRequestDto) throws RemoteException;
    public boolean receiveOutSideRequest(FileRequestDto fileRequestDto) throws RemoteException;

   }
