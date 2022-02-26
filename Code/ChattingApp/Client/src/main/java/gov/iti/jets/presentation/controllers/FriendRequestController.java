package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.FileRequestDto;
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
    FileRequestDto fileRequestDto;

    public FriendRequestController() throws RemoteException {
    }

    @FXML
    void OnAccept(MouseEvent event) {
        System.out.println("onAccept");
        try {
            friendRequestService.acceptingFriendRequest(this.friendRequestSenderDto);
        } catch (RemoteException e) {

        }
    }

    @FXML
    void OnReject(MouseEvent event) {
        System.out.println("onReject");

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
        public void dispalyFileReq(String name, FileRequestDto fileRequestDto) {
        FriendSendName.setText(name);
        this.fileRequestDto = fileRequestDto;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
