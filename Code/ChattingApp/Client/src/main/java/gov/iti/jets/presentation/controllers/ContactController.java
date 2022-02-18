package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private ContactModel contactModel = new ContactModel();
    @FXML
    private AnchorPane chatBox;

    @FXML
    private Label contactName;

    @FXML
    private Circle pictureOfContact;

    @FXML
    private Circle statusOfContact;
    ImageView imageView = new ImageView();
    String statusCond;

    public  void displayContact(String userName,Image image, String status) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                contactName.setText(userName);
                imageView.setImage(image);
                pictureOfContact.setFill(new ImagePattern(imageView.getImage()));
                statusCond = status;
                getUserStatus(status);
            }
        });
    }

    @FXML
    void OpenChatOnClick(MouseEvent event) {
        GridPane home = stageCoordinator.getHomepage();
        home.add( stageCoordinator.loadChatSection(contactName.getText(),imageView.getImage(),statusCond), 1, 0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    void getUserStatus(String status){
        if(status.equals("ACTIVE")){
            statusOfContact.setFill(Color.BLUE);
        }else if(status.equals("DoNotDisturb")){
            statusOfContact.setFill(Color.YELLOW);
        }else if(status.equals("AWAY")){
            statusOfContact.setFill(Color.RED);
        }
    }
}