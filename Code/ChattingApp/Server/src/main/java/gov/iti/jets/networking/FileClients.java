package gov.iti.jets.networking;


import java.io.*;
import java.net.Socket;

public class FileClients extends Thread {

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    Socket clientSocket ;

    public FileClients(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(clientSocket+" connected.");
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            String fileName = dataInputStream.readUTF();
            System.out.println(fileName);
           // receiveFile(fileName);
            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


