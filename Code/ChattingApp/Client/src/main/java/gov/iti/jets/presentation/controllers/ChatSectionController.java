package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.ClientMesseageInt;
import gov.iti.jets.service.ServerMessageInt;
import gov.iti.jets.service.daos.MessageDao;
import gov.iti.jets.service.dtos.MessageDto;
import gov.iti.jets.service.impl.ClientMessageImpl;
import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.MessageService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ChatSectionController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    MessageDto  messageDto= new MessageDto();
    MessageDao messageDao=new MessageDao(messageDto);
    UserModel userModel = modelFactory.getUserModel();
    MessageService messageService = MessageService.getInstance();
    private ObservableList<HBox> messageObservableList;

    @FXML
    private AnchorPane bottomBar;

    @FXML
    private ListView<HBox> chatContainer;

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

    public ChatSectionController() throws RemoteException {
    }

    @FXML
    private Label userName;
    int id;

    @FXML
    void onTypingEnter(KeyEvent event) {

    }
    ImageView imageView = new ImageView();

    public  void display(String name, Image image, String status,int id) {
        this.id = id;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userName.setText(name);
                imageView.setImage(image);
                profilePicture.setFill(new ImagePattern(imageView.getImage()));
                System.out.println(status);
                getUserStatus(status);
            }
        });
    }

    @FXML
    void sendButtonClicked(MouseEvent event) throws RemoteException {
        messageDao.setMessage(messageTextField.getText());
        messageTextField.setText("");
        messageDao.setUserName(userModel.getUserName());
        messageDao.setUserID();
        messageDao.getMessageDto().setFriendId(id);
        System.out.println(messageDao.getMessageDto());

        messageService.sendMessageDto(messageDao.getMessageDto());

        createMessage();

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

    private void createMessage(){


        messageObservableList.add(stageCoordinator.loadMessage(messageDao));
        chatContainer.setItems(messageObservableList);


    }
    public void displayMessage(){
        if(!ClientMessageImpl.list.isEmpty()){

            chatContainer.setItems(ClientMessageImpl.list);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatContainer.setCellFactory(studentListView -> new MessageListViewCell());
        messageObservableList = FXCollections.observableArrayList();


    }

    private class MessageListViewCell extends ListCell<HBox> {

       private Pane messageCellContainer=new Pane();
        public MessageListViewCell() {


        }

        @Override
        protected void updateItem(HBox item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter

                item.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                messageCellContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                messageCellContainer.getChildren().add(item);
                setGraphic(messageCellContainer);
            } else {
                setGraphic(null);
            }
        }
    }



}