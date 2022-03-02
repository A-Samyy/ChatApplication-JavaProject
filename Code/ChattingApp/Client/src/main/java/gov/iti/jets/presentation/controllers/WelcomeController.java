package gov.iti.jets.presentation.controllers;

import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.RememberMeServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    Tooltip aboutTip= new Tooltip("about the app");
    RMIRegister rmiRegister = RMIRegister.getInstance();

    @FXML
    private FontIcon aboutIcon;

    @FXML
    private TextField enterIPTextField;

    @FXML
    private Label serverValidation;

    @FXML
    private Label enterIPButton;

    @FXML
    private Label aboutButton;

    @FXML
    private Label createbutton;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginContent;

    @FXML
    void OnLoginAction(ActionEvent event) {
        stageCoordinator.switchToLoginScreen();
    }

    @FXML
    void OnRegestrationAction(MouseEvent event) {
        stageCoordinator.switchToRegistrationScreen();
    }



    @FXML
    void onAbout(MouseEvent event) {
        stageCoordinator.loadAbout();
    }

    @FXML
    void OnEnterStaticIp(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            System.out.println(enterIPTextField.getText());
            rmiRegister.setHost(enterIPTextField.getText());
            RememberMeServices rememberMeServices = RememberMeServices.getInstance();
            LoginService loginService = new LoginService();
            if (rememberMeServices.getUserInfoValue() == 0) {
//                stageCoordinator.switchToWelcomScreen();
   //             loginService.setUserId(rememberMeServices.getUserInfoValue());
            }
            else {
                loginService.setUserId(rememberMeServices.getUserInfoValue());
        //        if(!RMIRegister.flag){
//                    loginService.setUserId(rememberMeServices.getUserInfoValue());
                    try {
                        loginService.getdata();
                    } catch (RemoteException e) {
//                    e.printStackTrace();
                    }
                    rememberMeServices.registerme();
                    stageCoordinator.switchToGHomePageScreen();
//                }else {
//                    serverValidation.setText("Please Enter valid IP");
//                    serverValidation.setStyle("-fx-text-fill: #FF0000");
//                }

            }
            enterIPTextField.clear();
            loginButton.setDisable(false);
            createbutton.setDisable(false);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setDisable(true);
        createbutton.setDisable(true);
        Tooltip.install(aboutIcon, aboutTip);
        aboutTip.setStyle("-fx-background-color: #2f2a57af; -fx-font-size: 14px;-fx-text-fill:#ffffff ;");
    }
}
