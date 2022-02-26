package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.GroupDto;
import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.common.interfaces.ServerGroupChatMessageInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.services.LoginService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class GroupSectionController implements Initializable {
    RMIRegister rmiRegister = RMIRegister.getInstance();
    ServerGroupChatMessageInt serverGroupChatMessage = rmiRegister.groupChatMessageService();

    @FXML
    private AnchorPane bottomBar;

    @FXML
    private ListView<?> chatContainer;

    @FXML
    private FontIcon filesButton;

    @FXML
    private SplitMenuButton htmlEditor;

    @FXML
    private TextField messageTextField;

    @FXML
    private FontIcon sendButton;

    @FXML
    private AnchorPane topBar;

    @FXML
    private Label userName;
    private GroupDto groupDto;

    @FXML
    void onTypingEnter(KeyEvent event) {

    }

    @FXML
    void sendButtonClicked(MouseEvent event) {
        MessageGroupDto messageGroupDto = new MessageGroupDto();
        messageGroupDto.setGroupId(groupDto.getId());
        messageGroupDto.setSenderId(LoginService.getId());
        messageGroupDto.setMessageContent(messageTextField.getText());
        System.out.println(messageGroupDto.toString());
        try {
            serverGroupChatMessage.sendGroupChatMessage(messageGroupDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public  void displayGroup(GroupDto groupDto) {
        this.groupDto = groupDto;
        System.out.println(groupDto.getGroupName());

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userName.setText(groupDto.getGroupName());
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
