package gov.iti.jets;


import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.Impl.LoginImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainApp extends Application {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    public static void main(String[] arg) throws RemoteException {

        LoginImpl loginService = new LoginImpl();
        Registry register = LocateRegistry.createRegistry(3000);
        register.rebind("login",loginService);
        Application.launch(arg);
    }

    @Override
    public void start(Stage primaryStage){
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToLoginScreen();
        primaryStage.show();
    }
}
