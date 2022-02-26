package gov.iti.jets;


import gov.iti.jets.networking.FileClients;
import gov.iti.jets.networking.RMICreateRegister;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presistance.util.Connector;
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

                try(ServerSocket serverSocket = new ServerSocket(5555)){
                    System.out.println("listening to port:5000");
                    while(true){
                        Socket clientSocket = serverSocket.accept();
                        new FileClients(clientSocket).start();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
        }).start();
    }
}
