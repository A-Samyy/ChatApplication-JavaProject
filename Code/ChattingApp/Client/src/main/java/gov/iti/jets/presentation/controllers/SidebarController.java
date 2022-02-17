package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();

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
    private Label bio;

    @FXML
    private Tab chattingGroup;

    @FXML
    private VBox chattingGroupAreaVbox;

    @FXML
    private VBox chattingSectionVbox;

    @FXML
    private Circle profilePic;

    @FXML
    private TextField searchBar;

    @FXML
    private GridPane sideBar;

    @FXML
    private Circle status;

    @FXML
    private TabPane tabPane;

    @FXML
    private Label userName;

//    private String imageAsString;
    ImageView img;

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
        img = new ImageView();
        img.imageProperty().bindBidirectional(userModel.imageProperty());
        profilePic.setFill(new ImagePattern(img.getImage()));
        if(userModel.getStatus().equals("ACTIVE")){
            status.setFill(Color.RED);
        }


        bio.textProperty().bindBidirectional(userModel.bioProperty());
        userName.textProperty().bindBidirectional(userModel.userNameProperty());
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

//    public Image decodeImage(String image) throws Exception {
//        Image img ;
//        byte[] data = Base64.getDecoder().decode(image.getBytes(StandardCharsets.UTF_8));
//        img = new Image(new ByteArrayInputStream(data));
//        return img;
//    }

}
