package gov.iti.jets;


import gov.iti.jets.networking.RMICreateRegister;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presistance.util.Connector;
import gov.iti.jets.service.Impl.ServerGroupChatMessageImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class MainApp extends Application {
    Connector connector= Connector.getInstance();
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    public static void main(String[] arg) throws RemoteException {
        RMICreateRegister rmiCreateRegister= RMICreateRegister.getInstance();
        Application.launch(arg);
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        connector.endConnection();
    }
    @Override
    public void start(Stage primaryStage){
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToLoginScreen();
        primaryStage.setTitle("KAT app");
        primaryStage.setOnCloseRequest(e-> Platform.exit());

        primaryStage.show();
    }
}
