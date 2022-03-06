package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.impl.ClientAnnounceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MessageAdminController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    @FXML
    private AnchorPane adminMessage;

    @FXML
    void onMessageAdminClicked(MouseEvent event) {

        try {
            stageCoordinator.loadAdminMessageContainer(ClientAnnounceImpl.mapMessageFromAdmin);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
