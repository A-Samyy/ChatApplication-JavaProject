package gov.iti.jets.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class ChatSectionController {

    @FXML
    private AnchorPane bottomBar;

    @FXML
    private FontIcon filesButton;

    @FXML
    private SplitMenuButton htmlEditor;

    @FXML
    private TextField messageTextFiel;

    @FXML
    private FontIcon sendButton;

    @FXML
    private AnchorPane topBar;

    @FXML
    void onTypingEnter(KeyEvent event) {

    }

    @FXML
    void sendButtonClicked(MouseEvent event) {

    }

}