package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LoginController implements  Initializable{

    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();

    @FXML
    Button loginButton;
    @FXML
    Button registrationButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
    public LoginController(){}

    @FXML
    void OnLoginAction(ActionEvent event) {

       stageCoordinator.switchToGHomePageScreen();
    }

    @FXML
    void OnRegestrationAction(ActionEvent event) {
       stageCoordinator.switchToRegistrationScreen();
    }

    
}
