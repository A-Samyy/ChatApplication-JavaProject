package gov.iti.jets.presentation.controllers;


import gov.iti.jets.common.dtos.ClientGroupChatDto;
import gov.iti.jets.service.services.AddGroupChatService;
import gov.iti.jets.service.services.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class AddGroupChatController {
    AddGroupChatService addGroupChatService = new AddGroupChatService();
    List<String> contactsPhoneNumbers = new ArrayList<>();
    boolean isAdded;
    @FXML
    private TextField ContactNumberTextArea;

    @FXML
    private TextField GroupNameTextArea;

    @FXML
    void OnAddNewGroupClicked(MouseEvent event) {
        if (GroupNameTextArea.getText() != null && contactsPhoneNumbers.size() > 1) {
            ClientGroupChatDto clientGroupChatDto = new ClientGroupChatDto();
            clientGroupChatDto.setGroupName(GroupNameTextArea.getText());
            clientGroupChatDto.setUsersId(contactsPhoneNumbers);
            clientGroupChatDto.setGroupCreatorId(LoginService.getId());
            try {
                isAdded = addGroupChatService.addGroupChat(clientGroupChatDto);
                if (isAdded) {
                    GroupNameTextArea.setText("");
                    ContactNumberTextArea.setText("");
                    ValidatePhoneNumber.setText("Done.");
                    ValidatePhoneNumber.setTextFill(Color.GREEN);
                } else {
                    //set new label
                    ValidatePhoneNumber.setText("Please add at least 2 existing contacts");
                    ValidatePhoneNumber.setTextFill(Color.RED);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private Label ValidatePhoneNumber;


    @FXML
    void onAddingMoreContact(MouseEvent event) {
        if (ContactNumberTextArea.getText() != null) {
            contactsPhoneNumbers.add(ContactNumberTextArea.getText());
            ContactNumberTextArea.setText("");
        }
    }

}
