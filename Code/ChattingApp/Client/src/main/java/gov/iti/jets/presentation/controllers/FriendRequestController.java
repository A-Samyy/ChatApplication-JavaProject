package gov.iti.jets.presentation.controllers;

import gov.iti.jets.service.services.FriendRequestService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class FriendRequestController implements Initializable {
    FriendRequestService friendRequestService = new FriendRequestService();
    @FXML
    private Label FriendSendName;

    public FriendRequestController() throws RemoteException {
    }

    @FXML
    void OnAccept(MouseEvent event) {
//        friendRequestService.acceptingFriendRequest();
    }

    @FXML
    void OnReject(MouseEvent event) {

    }
    public void dispalyFriendReq(String name){
        FriendSendName.setText(name);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
