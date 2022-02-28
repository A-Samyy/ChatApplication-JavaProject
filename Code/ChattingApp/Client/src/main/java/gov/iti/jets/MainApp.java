package gov.iti.jets;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.MessageService;
import gov.iti.jets.service.services.RememberMeServices;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    RememberMeServices rememberMeServices = RememberMeServices.getInstance();
    LoginService loginService = new LoginService();

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stageCoordinator.initStage(primaryStage);
        if (rememberMeServices.getUserInfoValue() == 0) {
            stageCoordinator.switchToWelcomScreen();
        } else {
            loginService.setUserId(rememberMeServices.getUserInfoValue());
            loginService.getdata();
            stageCoordinator.switchToGHomePageScreen();
        }
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
//        MessageService messageService = MessageService.getInstance();
//        messageService.getClient().removeMe();
        LogoutService logoutService =new LogoutService();
        logoutService.logout();
//        MessageService messageService = MessageService.getInstance();
//        messageService.getClient().removeMe();
//    //    rememberMeServices.removeUserInfo();
        super.stop();
        System.exit(0);

    }
}
