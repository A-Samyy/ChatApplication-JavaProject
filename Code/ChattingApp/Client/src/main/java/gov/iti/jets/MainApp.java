package gov.iti.jets;

import gov.iti.jets.networking.FileServer;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.MessageService;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToWelcomScreen();
        primaryStage.show();
        //new FileServer().start();
    }

    @Override
    public void stop() throws Exception {
        MessageService messageService = MessageService.getInstance();
        messageService.getClient().removeMe();
        super.stop();
        System.exit(0);

    }
}
