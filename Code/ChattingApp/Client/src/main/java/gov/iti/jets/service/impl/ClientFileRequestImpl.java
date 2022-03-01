package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.FileRequestDto;
import gov.iti.jets.common.interfaces.ClientFileRequestInt;
import gov.iti.jets.common.interfaces.ServerFileRequestInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.models.FileCounterModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.FileTransferService;
import gov.iti.jets.service.services.LoginService;
import javafx.application.Platform;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ClientFileRequestImpl extends UnicastRemoteObject implements ClientFileRequestInt {

    private RMIRegister rmiRegister = RMIRegister.getInstance();
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private ServerFileRequestInt serverFileRequestInt = rmiRegister.serverFileRequestService();
    private FileRequestDto fileRequestDto = new FileRequestDto();
    FileTransferService fileTransferService = new FileTransferService();
    transient private static DataOutputStream dataOutputStream = null;
    transient private static DataInputStream dataInputStream = null;
    transient boolean outsideRequestResponse;

    private ModelFactory modelFactory = ModelFactory.getInstance();
    private FileCounterModel fileCounterModel = modelFactory.getFileCounterModel();
    public static List<FileRequestDto> fileRequestDtos = new ArrayList<>();
    static ClientFileRequestImpl clientFileRequest;

    public static ClientFileRequestImpl getClientFileRequest() {
        return clientFileRequest;
    }


    public ClientFileRequestImpl() throws RemoteException {
        super();
    }


    public void registerFileRequestInt() {
        try {
            serverFileRequestInt.register(this, LoginService.getId());
            clientFileRequest = this;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean receiveMyRequest(FileRequestDto fileRequestDto) throws RemoteException {
        try {
            System.out.println("FileTransferService to send the file last stop before sending file");
            try (Socket socket = new Socket("localhost", 8877)) {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                System.out.println("sending file ");


                sendFile(fileRequestDto.getFilePath());


                System.out.println("File sended");
                dataInputStream.close();
                dataInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        fileRequestDtos.remove(fileRequestDto);
        try {
            FileChooser openFileChooser = new FileChooser();
            File file = openFileChooser.showSaveDialog(null);
            System.out.println(file.getPath());
            stageCoordinator.loadProgressBar();

            new Thread(() -> {
                try (ServerSocket serverSocket = new ServerSocket(8877)) {
                    System.out.println("listening to port:5000");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println(clientSocket + " connected.");
                    dataInputStream = new DataInputStream(clientSocket.getInputStream());
                    dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());


                    receiveFile(file.getPath() + " " + fileRequestDto.getFileName());


                    dataInputStream.close();
                    dataOutputStream.close();
                    clientSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            serverFileRequestInt.acceptingFileRequest(fileRequestDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void sendFile(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        // send file size
        dataOutputStream.writeLong(file.length());
        // break file into chunks
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer)) != -1) {

            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }

        fileInputStream.close();
    }

    public void receiveFile(String fileName) throws IOException {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4 * 1024];
        fileCounterModel.setNumber(0.0);
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;      // read upto file size

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    fileCounterModel.setNumber(fileCounterModel.getNumber() + 0.1);
                }
            });


        }


        fileOutputStream.close();
    }
}
