package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.service.dtos.MessageDto;
import gov.iti.jets.service.services.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class ChatSectionController {

    MessageDto messageDto=new MessageDto();
    ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    @FXML
    private AnchorPane bottomBar;

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
    void onTypingEnter(KeyEvent event) {

    }

    @FXML
    void sendButtonClicked(MouseEvent event) {

        messageDto.setMessageContent(messageTextField.getText());
        messageTextField.setText("");
        messageDto.setUserName(userModel.getUserName());
        messageDto.setUserId(LoginService.getId());
        System.out.println(messageDto);
    }

}