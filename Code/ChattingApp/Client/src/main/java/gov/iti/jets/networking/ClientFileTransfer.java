package gov.iti.jets.networking;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientFileTransfer  {

    private Socket socket;

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    private static ClientFileTransfer staticClient;



    public static ClientFileTransfer getInstance(int port) {
        if (staticClient == null) {
            staticClient = new ClientFileTransfer("localhost", 7777);
        }
        return staticClient;
    }

    public ClientFileTransfer(String hostName, int port) {


            try {
                socket = new Socket(hostName, port);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());



//                sendFile("path/to/file1.pdf");
//                sendFile("path/to/file2.pdf");
//
//                dataInputStream.close();
//                dataInputStream.close();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

       }


    public static void sendFile(String path) throws Exception{
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);

        // send file size
        dataOutputStream.writeLong(file.length());
        // break file into chunks
        byte[] buffer = new byte[4*1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }


    }
