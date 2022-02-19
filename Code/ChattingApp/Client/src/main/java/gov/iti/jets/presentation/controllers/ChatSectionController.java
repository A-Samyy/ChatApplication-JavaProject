package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.daos.MessageDao;
import gov.iti.jets.service.dtos.MessageDto;
import gov.iti.jets.service.services.LoginService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

public class ChatSectionController {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    MessageDto  messageDto= new MessageDto();
    MessageDao messageDao=new MessageDao(messageDto);
    UserModel userModel = modelFactory.getUserModel();



    @FXML
    private AnchorPane bottomBar;

    @FXML
    private VBox chatContainer;

    @FXML
    private FontIcon filesButton;

    @FXML
    private SplitMenuButton htmlEditor;

    @FXML
    private TextField messageTextField;

    @FXML
    private Circle profilePicture;

    @FXML
    private FontIcon sendButton;

    @FXML
    private Circle status;

    @FXML
    private AnchorPane topBar;

    @FXML
    private Label userName;

    @FXML
    void onTypingEnter(KeyEvent event) {

    }
    ImageView imageView = new ImageView();

    public  void display(String name, Image image, String status) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userName.setText(name);
                imageView.setImage(image);
                profilePicture.setFill(new ImagePattern(imageView.getImage()));
                getUserStatus(status);
            }
        });
    }

        @FXML
    void sendButtonClicked(MouseEvent event) {
        messageDao.setMessage(messageTextField.getText());
        messageTextField.setText("");
        messageDao.setUserName(userModel.getUserName());
        messageDao.setUserID();

        //System.out.println(messageDao.getMessageDto());

        chatContainer.getChildren().add(stageCoordinator.loadMessage(messageDao));
    }

    void getUserStatus(String statusCond){
        if(statusCond.equals("ACTIVE")){
            status.setFill(Color.BLUE);
        }else if(statusCond.equals("DoNotDisturb")){
            status.setFill(Color.YELLOW);
        }else if(statusCond.equals("AWAY")){
            status.setFill(Color.RED);
        }
    }

}