package gov.iti.jets;


import gov.iti.jets.networking.FileTransferConnection;
import gov.iti.jets.networking.RMICreateRegister;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presistance.util.Connector;
import gov.iti.jets.service.Impl.ServerGroupChatMessageImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;

public class MainApp extends Application {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    public static void main(String[] arg) throws RemoteException {


//        Connector connector= Connector.getInstance();
//        connector.getConnection();
//       System.out.println("after connection");
        //Marwa merging

//        try {
//            fileTransferConnection.receiveFile("hamada.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        RMICreateRegister rmiCreateRegister= RMICreateRegister.getInstance();
        Application.launch(arg);


    }
    @Override
    public void stop() throws Exception {
        super.stop();
        Connector connector= Connector.getInstance();
        connector.endConnection();
    }
    @Override
    public void start(Stage primaryStage){

        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToLoginScreen();
        primaryStage.show();

        new Thread(() -> {
                try(ServerSocket serverSocket = new ServerSocket(9999)){
                    System.out.println("listening to port:5000");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println(clientSocket+" connected.");
                    dataInputStream = new DataInputStream(clientSocket.getInputStream());
                    dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                    receiveFile("image.jpg");
                    dataInputStream.close();
                    dataOutputStream.close();
                    clientSocket.close();
                } catch (Exception e){
                    e.printStackTrace();
            }
        }).start();
    }

    private static void receiveFile(String fileName) throws Exception{
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
