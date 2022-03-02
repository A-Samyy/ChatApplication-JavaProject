package gov.iti.jets;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.MessageService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MainApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToWelcomScreen();
        primaryStage.setTitle("KAT app");
        primaryStage.setOnCloseRequest(e -> Platform.exit());

        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (LoginService.getId() != 0) {
            MessageService messageService = MessageService.getInstance();
            messageService.getClient().removeMe();

        }

        System.exit(0);


    }
}
