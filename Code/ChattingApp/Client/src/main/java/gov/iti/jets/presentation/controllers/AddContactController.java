package gov.iti.jets.presentation.controllers;


import gov.iti.jets.common.dtos.ClientFriendRequestDto;
import gov.iti.jets.service.services.FriendRequestService;
import gov.iti.jets.service.services.LoginService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AddContactController implements Initializable {
    boolean statusOfFriendRequest;
    @FXML
    private Label ValidatePhoneNumber;
    @FXML
    private TextField AddingFriendTextArea;
    @FXML
    void OnAddNewContactClicked(MouseEvent event) {
        System.out.println("dakhlt gwa el add function");
        if (AddingFriendTextArea.getText() != null) {
//            if (event.getCode().equals(KeyCode.ENTER)) {
                System.out.println(AddingFriendTextArea.getText());
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FriendRequestService friendRequestService = new FriendRequestService();
                            ClientFriendRequestDto clientFriendRequestDto = new ClientFriendRequestDto();
                            clientFriendRequestDto.setFriendPhoneNumber(AddingFriendTextArea.getText());
                            clientFriendRequestDto.setUserId(LoginService.getId());
                            System.out.println(clientFriendRequestDto.getFriendPhoneNumber());
                            statusOfFriendRequest= friendRequestService.friendRequest(clientFriendRequestDto);
                            if(statusOfFriendRequest){
                                ValidatePhoneNumber.setText("Done");
                                AddingFriendTextArea.setText("");
                                ValidatePhoneNumber.setTextFill(Color.BLUE);
                            }
                            else {
                                ValidatePhoneNumber.setText("There is no Contact with This number");
                                ValidatePhoneNumber.setTextFill(Color.RED);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
//        }
    }

//    @FXML
//    void OnEnterPressed(KeyEvent event) {
//        if (AddingFriendTextArea.getText() != null) {
//            if (event.getCode().equals(KeyCode.ENTER)) {
//                System.out.println(AddingFriendTextArea.getText());
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            FriendRequestService friendRequestService = new FriendRequestService();
//                            ClientFriendRequestDto clientFriendRequestDto = new ClientFriendRequestDto();
//                            clientFriendRequestDto.setFriendPhoneNumber(AddingFriendTextArea.getText());
//                            clientFriendRequestDto.setUserId(LoginService.getId());
//                            System.out.println(clientFriendRequestDto.getFriendPhoneNumber());
//
//                            friendRequestService.friendRequest(clientFriendRequestDto);
//                        } catch (RemoteException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//            }
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializeeeeeeeee");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AddingFriendTextArea.setText("");
                ValidatePhoneNumber.setText("");
            }
        });
    }
}
