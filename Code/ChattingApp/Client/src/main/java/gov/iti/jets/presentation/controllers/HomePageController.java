package gov.iti.jets.presentation.controllers;

import org.kordamp.ikonli.javafx.FontIcon;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class HomePageController {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    @FXML
    private Tab Contacts;

    @FXML
    private Tab Setting;

    @FXML
    private VBox SettingAreaVbox;

    @FXML
    private FontIcon Settings;

    @FXML
    private Tab addContact;

    @FXML
    private VBox addContactsAreaVbox;

    @FXML
    private ScrollPane chatArea;

    @FXML
    private Tab chattingGroup;

    @FXML
    private VBox chattingGroupAreaVbox;

    @FXML
    private VBox chattingSectionVbox;

    @FXML
    private TextField searchBar;

    @FXML
    private VBox sideBar;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField typingTextField;

    @FXML
    public void SettingOnMouseClicked(ActionEvent event) {
        System.out.println(" 222");
  //      SettingAreaVbox.getChildren().add(stageCoordinator.loadSettings());
    }

    @FXML
    void addContactOnMouseClicked(ActionEvent event) {
 //       addContactsAreaVbox.getChildren().add(stageCoordinator.loadContacts());
    }

    @FXML
    void chattingGroupOnMouseClicked(ActionEvent event) {
  //      chattingGroupAreaVbox.getChildren().add(stageCoordinator.loadMyChat());
    }

    @FXML
    void contactsOnMouseClicked(ActionEvent event) {

    }

    @FXML
    void fontStyleChangeOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void onMouseClicked(MouseEvent event) {

    }

    @FXML
    void onTypingEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
       //     addContactsAreaVbox.getChildren().add(stageCoordinator.loadContacts());

        }
    }

    @FXML
    void searchOnKeyTyped(KeyEvent event) {

    }

    @FXML
    void sendButtonClicked(MouseEvent event) {

    }

    @FXML
    void uploadFileOnMouseClicked(MouseEvent event) {

    }

}
