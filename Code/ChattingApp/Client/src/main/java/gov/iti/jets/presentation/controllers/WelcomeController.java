package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class WelcomeController {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();

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

}
