package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ProfileController {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    HomePageController home = new HomePageController();
    @FXML
    void backToMainPageOnMouseClick(MouseEvent event) {
        stageCoordinator.switchToGHomePageFromProfileScreen();
        // System.out.println("samy");
        // Platform.runLater(new Runnable() {

        //     @Override
        //     public void run() {
        //         // TODO Auto-generated method stub
        //         home.getGridPane().add(home.getSideBar(), 0, 0);
        //     }
            
        // });
        
    }

}
