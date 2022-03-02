package gov.iti.jets;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.LogoutService;
import gov.iti.jets.service.services.MessageService;
import gov.iti.jets.service.services.RememberMeServices;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.net.InetAddress;

public class MainApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    RememberMeServices rememberMeServices = RememberMeServices.getInstance();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stageCoordinator.initStage(primaryStage);


        if (rememberMeServices.getUserInfoValue() == 0) {
            stageCoordinator.switchToWelcomScreen();
        }
        else {
            LoginService loginService = new LoginService();
            loginService.setUserId(rememberMeServices.getUserInfoValue());
            loginService.getdata();
            rememberMeServices.registerme();
            stageCoordinator.switchToGHomePageScreen();
        }

        primaryStage.setTitle("KAT app");
        primaryStage.setOnCloseRequest(e-> Platform.exit());
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        MessageService messageService=MessageService.getInstance();
        messageService.getClient().removeMe();
        super.stop();
        System.exit(0);
    }
}
