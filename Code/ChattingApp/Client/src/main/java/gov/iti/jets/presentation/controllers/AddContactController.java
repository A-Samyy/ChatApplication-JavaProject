package gov.iti.jets.presentation.controllers;


import gov.iti.jets.common.dtos.ClientFriendRequestDto;
import gov.iti.jets.service.services.FriendRequestService;
import gov.iti.jets.service.services.LoginService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class AddContactController implements Initializable {
    boolean statusOfFriendRequest;
    @FXML
    private TextField AddingFriendTextArea;

    @FXML
    private Label ValidatePhoneNumber;

    @FXML
    private FontIcon addContact;
    @FXML
    void OnAddNewContactClicked(MouseEvent event) {
        if (AddingFriendTextArea.getText() != null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            FriendRequestService friendRequestService = new FriendRequestService();
                            ClientFriendRequestDto clientFriendRequestDto = new ClientFriendRequestDto();
                            clientFriendRequestDto.setFriendPhoneNumber(AddingFriendTextArea.getText());
                            clientFriendRequestDto.setUserId(LoginService.getId());
                            statusOfFriendRequest= friendRequestService.friendRequest(clientFriendRequestDto);
                            if(statusOfFriendRequest){
                                ValidatePhoneNumber.setText("Done");
                                AddingFriendTextArea.setText("");
                                ValidatePhoneNumber.setTextFill(Color.GREEN);
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
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                AddingFriendTextArea.setText("");
                ValidatePhoneNumber.setText("");
            }
        });
    }
}
