package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.MessageAnnounceDto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageServerController implements Initializable {


    @FXML
    private HBox chatMessage;

    @FXML
    private Label messageBox;

    @FXML
    private Label userName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public  void displayMessage(String message ,String name){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messageBox.setText(message);
                userName.setText(name);

            }
        });

    }
}
