package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();

    @FXML
    private RadioButton active;
    @FXML
    private RadioButton away;
    @FXML
    private RadioButton busy;
    @FXML
    private TextField bio;
    @FXML
    private Circle pofilePic;
    @FXML
    private TextField username;

    private ToggleGroup toggleGroup;
    RadioButton selectedRadioButton;
    GridPane homeGrid;
    ImageView img;

    @FXML
    void backToMainPageOnMouseClick(MouseEvent event) {
        homeGrid = stageCoordinator.getHomepage();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                homeGrid.getChildren().removeIf(node -> GridPane.getColumnIndex(node)==0);
                homeGrid.add( stageCoordinator.loadSidebar(), 0, 0);
            }
        });

    }

    @FXML
    void OnChoosingStatus(ActionEvent event) {
        toggleGroup = new ToggleGroup();
        active.setToggleGroup(toggleGroup);
        busy.setToggleGroup(toggleGroup);
        away.setToggleGroup(toggleGroup);selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        userModel.setGender(selectedRadioButton.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        img = new ImageView();
        img.imageProperty().bindBidirectional(userModel.imageProperty());
        pofilePic.setFill(new ImagePattern(img.getImage()));
        getUserStatus();
        bio.textProperty().bindBidirectional(userModel.bioProperty());
        username.textProperty().bindBidirectional(userModel.userNameProperty());

    }
    void getUserStatus(){
        if(userModel.getStatus().equals("ACTIVE")){
            active.setSelected(true);
        }else if(userModel.getStatus().equals("DoNotDisturb")){
            busy.setSelected(true);
        }else if(userModel.getStatus().equals("AWAY")){
            away.setSelected(true);
        }
    }
}
