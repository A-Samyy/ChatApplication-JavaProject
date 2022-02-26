package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.ContactDto;
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

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ContactController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private ContactModel contactModel = new ContactModel();
    ContactDto contactDto;
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

    public  void displayContact(ContactDto contactDto) {
        this.contactDto =contactDto;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                contactName.setText(contactDto.getFriendName());
                try {
                    imageView.setImage(decodeImage(contactDto.getPicture()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pictureOfContact.setFill(new ImagePattern(imageView.getImage()));
                getUserStatus(contactDto.getStatus());
                stageCoordinator.loadChatSection(contactName.getText(),imageView.getImage(),contactDto.getStatus(),contactDto.getId());
            }
        });
    }
    public Image decodeImage(String image) throws Exception {
        Image img ;
        byte[] data = Base64.getDecoder().decode(image.getBytes(StandardCharsets.UTF_8));
        img = new Image(new ByteArrayInputStream(data));
        return img;
    }
    @FXML
    void OpenChatOnClick(MouseEvent event) {
        GridPane home = stageCoordinator.getHomepage();
        home.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1);
        home.add( stageCoordinator.loadChatSection(contactName.getText(),imageView.getImage(),contactDto.getStatus(),contactDto.getId()), 1, 0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    void getUserStatus(String status){
        if(status.equals("ACTIVE")){
            statusOfContact.setFill(Color.GREEN);
        }else if(status.equals("DoNotDisturb")){
            statusOfContact.setFill(Color.YELLOW);
        }else if(status.equals("AWAY")){
            statusOfContact.setFill(Color.RED);
        }else if(status.equals("OFFLINE")){
            statusOfContact.setFill(Color.GRAY);
        }
    }
}