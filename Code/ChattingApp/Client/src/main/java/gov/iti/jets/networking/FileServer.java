package gov.iti.jets.networking;

import java.net.ServerSocket;
import java.net.Socket;

public class FileServer extends Thread{

    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(8877)) {
            System.out.println("listening to port:8877");
            Socket clientSocket = serverSocket.accept();
            new FileClient(clientSocket).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
