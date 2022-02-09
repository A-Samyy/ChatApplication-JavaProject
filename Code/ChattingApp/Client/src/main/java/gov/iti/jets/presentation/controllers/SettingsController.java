package gov.iti.jets.presentation.controllers;



import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SettingsController {
    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    @FXML
    void helpOnMouseClicked(MouseEvent event) {
        stageCoordinator.loadhelp();
    }

    @FXML
    void themeOnMouseClicked(MouseEvent event) {
        stageCoordinator.loadTheme();
    }

}