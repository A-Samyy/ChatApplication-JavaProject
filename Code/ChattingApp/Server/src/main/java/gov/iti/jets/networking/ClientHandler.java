package gov.iti.jets.networking;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {


    private Socket client;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;



    public ClientHandler(Socket client) {
        this.client = client;
        try {
            dataInputStream = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            dataOutputStream= new DataOutputStream(client.getOutputStream());
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
