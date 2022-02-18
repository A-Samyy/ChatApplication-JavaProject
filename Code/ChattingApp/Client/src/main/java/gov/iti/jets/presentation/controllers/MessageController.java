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

    MessageDao messageDao = new MessageDao();

    public MessageController(){
        System.out.println("hendddddd");
    }

    @FXML
    private Label messageBox;

    @FXML
    private Label userName;

    @FXML
    private Circle userPicture;

    @FXML
    private Circle userStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Marwaaaaaaa");
    }

    public  void displayMessage(String message ,String userName){

        messageBox.setText(message);
        this.userName.setText(userName);
    }

    public  void message(){

       System.out.println("a7aaaaaaaa");
    }
}
