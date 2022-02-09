package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ContactListController {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    @FXML
    void addNewContactOnMouseClick(MouseEvent event) {
        stageCoordinator.loadAddContact();
    }

}
