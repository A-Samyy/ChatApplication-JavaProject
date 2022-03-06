package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.FriendRequestSenderDto;
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
    FriendRequestSenderDto friendRequestSenderDto;

    public FriendRequestController() throws RemoteException {
    }

    @FXML
    void OnAccept(MouseEvent event) {
        try {
            friendRequestService.acceptingFriendRequest(this.friendRequestSenderDto);
        } catch (RemoteException e) {

        }
    }

    @FXML
    void OnReject(MouseEvent event) {
        try {
            friendRequestService.rejectingFriendRequest(this.friendRequestSenderDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void dispalyFriendReq(String name, FriendRequestSenderDto friendRequestSenderDto){
        FriendSendName.setText(name);
        this.friendRequestSenderDto = friendRequestSenderDto;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
