package gov.iti.jets.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class FileTransferConnection extends Thread {

    private final static FileTransferConnection fileTransferConnection=new FileTransferConnection(7777);
    //Map<Integer,ClientHandler> clientsList=new TreeMap<>();
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static FileTransferConnection getInstance(){
        return fileTransferConnection;
    }

    ServerSocket serverSocket;


    public FileTransferConnection(int port) {
        try {

            serverSocket = new ServerSocket(port);

            this.start();
            System.out.println("FileTransfer Server is initialized");


        } catch (Exception e) {

            e.getStackTrace();
        }

    }

    public void run() {

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
//                ClientHandler newClient = new ClientHandler(clientSocket);
                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                System.out.println("new client");


                receiveFile("NewFile1.pdf");
//                receiveFile("NewFile2.pdf");



               dataInputStream.close();
               dataOutputStream.close();
               clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public  void receiveFile(String fileName) throws Exception{
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4*1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }
}


