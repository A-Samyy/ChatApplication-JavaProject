package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    @FXML
    private Tab Contacts;

    @FXML
    private Tab Setting;

    @FXML
    private VBox SettingAreaVbox;

    @FXML
    private Tab addContact;

    @FXML
    private VBox addContactsAreaVbox;

    @FXML
    private AnchorPane anchorPaneOfContacts;

    @FXML
    private Tab chattingGroup;

    @FXML
    private VBox chattingGroupAreaVbox;

    @FXML
    private VBox chattingSectionVbox;

    @FXML
    private TextField searchBar;

    @FXML
    private GridPane sideBar;

    @FXML
    private TabPane tabPane;

    @FXML
    void logoutOnMouseClick(MouseEvent event) {
        stageCoordinator.switchToLoginScreen();
    }

    @FXML
    void onMouseClicked(MouseEvent event) {

    }

    @FXML
    void openProfileOnMouseClick(MouseEvent event) {
        GridPane home = stageCoordinator.getHomepage();
        home.getChildren().removeIf(node -> GridPane.getColumnIndex(node)==0);
        home.add( stageCoordinator.loadProfile(), 0, 0);
    }

    @FXML
    void searchOnKeyTyped(KeyEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        anchorPaneOfContacts.getChildren().add(stageCoordinator.loadContacts());
        SettingAreaVbox.getChildren().add(stageCoordinator.loadSettings());

        chattingSectionVbox.getChildren().add(stageCoordinator.loadMyChat());
        chattingSectionVbox.getChildren().add(stageCoordinator.loadMyChat());
        chattingSectionVbox.getChildren().add(stageCoordinator.loadMyChat());

        chattingGroupAreaVbox.getChildren().add(stageCoordinator.loadMyChat());
        chattingGroupAreaVbox.getChildren().add(stageCoordinator.loadMyChat());
        chattingGroupAreaVbox.getChildren().add(stageCoordinator.loadMyChat());
        chattingGroupAreaVbox.getChildren().add(stageCoordinator.loadMyChat());
    }

}
