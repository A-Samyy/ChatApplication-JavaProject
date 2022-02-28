package gov.iti.jets.presentation.controllers;

import gov.iti.jets.service.impl.ClientAnnounceImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminMessageContainerController implements Initializable {


    @FXML
    private ListView<String> adminMessageListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void displayMessage(List<String > messagesFromAdmin){

        for (String message:messagesFromAdmin) {
            adminMessageListView.getItems().add(message);
        }
    }
    public void shutdown() {
        System.out.println("Stop");
        ClientAnnounceImpl.messagesFromAdmin.clear();
        adminMessageListView.getItems().clear();
    }
}
