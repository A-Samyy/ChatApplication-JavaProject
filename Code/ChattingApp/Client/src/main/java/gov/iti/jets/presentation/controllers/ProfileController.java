package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ProfileController {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    HomePageController home = new HomePageController();
    @FXML
    void backToMainPageOnMouseClick(MouseEvent event) {
        GridPane home = stageCoordinator.getHomepage();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                home.getChildren().removeIf(node -> GridPane.getColumnIndex(node)==0);
                home.add( stageCoordinator.loadSidebar(), 0, 0);
            }
        });

    }

}
