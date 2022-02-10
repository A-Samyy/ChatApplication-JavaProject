package gov.iti.jets.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import gov.iti.jets.presentation.util.StageCoordinator;

public class RegisterController {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    @FXML
    private Label female;

    @FXML
    private Label male;

    @FXML
    private Button regbutton;

    @FXML
    void registerOnMouseClick(MouseEvent event) {
        stageCoordinator.switchToLoginScreen();
    }

}