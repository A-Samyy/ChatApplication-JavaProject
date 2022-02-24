package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.ServerControlService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    ServerControlService serverControlService = ServerControlService.getInstance();
    @FXML
    private AnchorPane content;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab userAdd;


    @FXML
    void onCloseConnectionMouseClick(MouseEvent event) {
        serverControlService.closeConnection();
    }
    @FXML
    void onOpenConnectionMouseClick(MouseEvent event) {
        serverControlService.openConnection();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        // ( (AnchorPane)userAdd.getContent()).getChildren().add(stageCoordinator.loadAddUser());
        content.getChildren().add(stageCoordinator.loadAddUser());

    }


}
