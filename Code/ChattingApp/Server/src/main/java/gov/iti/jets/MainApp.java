package gov.iti.jets;


import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.Impl.RegisterImpl;
import gov.iti.jets.service.RegisterInt;
import javafx.application.Application;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainApp extends Application {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    public static void main(String[] arg) throws RemoteException {
        RegisterInt register = new RegisterImpl();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("RegisterService",register);

        Application.launch(arg);
    }

    @Override
    public void start(Stage primaryStage){
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToLoginScreen();
        primaryStage.show();
    }
}
