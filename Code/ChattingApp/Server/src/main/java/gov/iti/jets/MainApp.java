package gov.iti.jets;


import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    public static void main(String[] arg){
        Application.launch(arg);
    }

    @Override
    public void start(Stage primaryStage){
        stageCoordinator.initStage(primaryStage);
        stageCoordinator.switchToLoginScreen();
        primaryStage.show();
    }
}
