package gov.iti.jets.presentation.controllers;

import gov.iti.jets.service.daos.MessageDao;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {

//    MessageDao messageDao = new MessageDao();

    public MessageController(){

    }

    @FXML
    private Label messageBox;

    @FXML
    private Label userName;

    @FXML
    private Circle userPicture;

    @FXML
    private Circle userStatus;
    @FXML
    private Label text;

    @FXML
    private Label username;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public  void displayMessage(String message ,String userName){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                username.setText(userName);
                text.setText(message);
            }
        });

    }

    public  void message(){

       System.out.println("a7aaaaaaaa");
    }
}
