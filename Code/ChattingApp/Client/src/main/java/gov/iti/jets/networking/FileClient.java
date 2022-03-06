package gov.iti.jets.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FileClient extends Thread {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    Socket socket ;
    public FileClient(Socket socket){
        this.socket=socket;
    }

    public void run(){
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataInputStream.close();
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
