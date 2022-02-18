package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.daos.MessageDao;
import gov.iti.jets.service.dtos.MessageDto;
import gov.iti.jets.service.services.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class ChatSectionController {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    MessageDao messageDao=new MessageDao();
    UserModel userModel = modelFactory.getUserModel();



    @FXML
    private AnchorPane chatBox;

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
        messageDao.setMessage(messageTextField.getText());
        messageTextField.setText("");
        messageDao.setUserName(userModel.getUserName());
        messageDao.setUserID();

        System.out.println(messageDao.getMessageDto());

        chatBox.getChildren().add(stageCoordinator.loadMessage());
    }

}