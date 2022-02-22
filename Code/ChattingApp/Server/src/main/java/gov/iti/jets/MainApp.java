package gov.iti.jets;


import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.networking.RMICreateRegister;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presistance.util.Connector;
import gov.iti.jets.service.Impl.ServerGroupChatMessageImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class MainApp extends Application {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    public static void main(String[] arg) throws RemoteException {
//        Connector connector= Connector.getInstance();
//        connector.getConnection();
 //       System.out.println("after connection");
        //Marwa merging
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

    }
}
