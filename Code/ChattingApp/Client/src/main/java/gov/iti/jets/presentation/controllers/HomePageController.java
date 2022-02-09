package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.kordamp.ikonli.javafx.FontIcon;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HomePageController implements Initializable {
  StageCoordinator stageCoordinator = StageCoordinator.getInstance();
  @FXML
  private Tab Contacts;

  @FXML
  private GridPane gridPane;

  @FXML
  private Tab Setting;

  @FXML
  private VBox SettingAreaVbox;

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

  public VBox getSideBar() {
    return sideBar;
  }

  public GridPane getGridPane() {
    return gridPane;
  }

  @FXML
  void openProfileOnMouseClick(MouseEvent event) {
    gridPane.getChildren().remove(sideBar);
    gridPane.add( stageCoordinator.loadProfile(), 0, 0);
    // sideBar.getChildren().add(stageCoordinator.loadProfile());
  }

  @FXML
  public void SettingOnMouseClicked(ActionEvent event) {
    System.out.println(" 222");
    // SettingAreaVbox.getChildren().add(stageCoordinator.loadSettings());
  }

  @FXML
  void addContactOnMouseClicked(ActionEvent event) {
    // addContactsAreaVbox.getChildren().add(stageCoordinator.loadContacts());
  }

  @FXML
  void chattingGroupOnMouseClicked(ActionEvent event) {
    // chattingGroupAreaVbox.getChildren().add(stageCoordinator.loadMyChat());
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
      // addContactsAreaVbox.getChildren().add(stageCoordinator.loadContacts());

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

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    addContactsAreaVbox.getChildren().add(stageCoordinator.loadContacts());
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
