package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.FileRequestDto;
import gov.iti.jets.common.interfaces.ClientFileRequestInt;
import gov.iti.jets.common.interfaces.ServerFileRequestInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.services.FileTransferService;
import gov.iti.jets.service.services.LoginService;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ClientFileRequestImpl extends UnicastRemoteObject implements ClientFileRequestInt {

    private RMIRegister rmiRegister = RMIRegister.getInstance();
    private ServerFileRequestInt serverFileRequestInt = rmiRegister.serverFileRequestService();
    private FileRequestDto fileRequestDto=new FileRequestDto();
    FileTransferService fileTransferService = new FileTransferService();
    boolean outsideRequestResponse;
    public static List<FileRequestDto> fileRequestDtos = new ArrayList<>();


    public ClientFileRequestImpl() throws RemoteException {
        super();
        serverFileRequestInt.register(this, LoginService.getId());
    }

    @Override
    public boolean receiveMyRequest(FileRequestDto fileRequestDto) throws RemoteException {
        try {
            System.out.println("FileTransferService to send the file last stop before sending file");
            //FileTransferService.sendFile(fileRequestDto.getFilePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean receiveOutSideRequest(FileRequestDto fileRequestDto) throws RemoteException {
        //send to the Gui to accept or refuse Controller by StageCoordinetor
        fileRequestDtos.add(fileRequestDto);
        System.out.println(fileRequestDto.toString());
        return true;
    }

    public boolean sendMyRequest(FileRequestDto fileRequestDto) {
        try {
            serverFileRequestInt.getNewRequest(fileRequestDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sendResponseRequest(FileRequestDto fileRequestDto) {
        // //ONlY on accepting request from controller
        try {
            fileRequestDtos.remove(fileRequestDto);
            //FileTransferService.receiveFile(fileRequestDto.getFileName());
            serverFileRequestInt.acceptingFileRequest(fileRequestDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
