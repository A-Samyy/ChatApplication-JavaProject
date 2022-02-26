package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.daos.MessageDao;
import gov.iti.jets.service.impl.ClientMessageImpl;
import gov.iti.jets.service.services.ContactListService;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.controlsfx.validation.ValidationSupport;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.xml.validation.Validator;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;
import java.util.Map;

public class ChatSectionController implements Initializable {

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    ContactListService contactListService = new ContactListService();
    MessageDto  messageDto= new MessageDto();
    MessageDao messageDao=new MessageDao(messageDto);
    UserModel userModel = modelFactory.getUserModel();
    MessageService messageService = MessageService.getInstance();
    private ObservableList<HBox> messageObservableList;
    private Boolean messageReceived=false;
    @FXML
    private AnchorPane bottomBar;

    @FXML
    private ListView<MessageDto> chatContainer;

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
    int friendId;

    @FXML
    void onTypingEnter(KeyEvent event) {

    }
    ImageView imageView = new ImageView();

    public  void display(String name, Image image, String status,int id) {
//        if(observableListMap.get(id) == null){
//            list = FXCollections.observableArrayList();
//            observableListMap.put(id,list);
//        }
        this.friendId = id;
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

    public  void displayGroup(String name) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(name);
                status.setVisible(false);
                profilePicture.setVisible(false);
                userName.setText(name);
            }
        });
    }

    @FXML
    void sendButtonClicked(MouseEvent event) throws RemoteException {
        messageDao.setMessage(messageTextField.getText());
        messageTextField.setText("");
        messageDao.setUserName(userModel.getUserName());
        messageDao.setUserID();
        messageDao.getMessageDto().setFriendId(friendId);
        messageService.sendMessageDto(messageDao.getMessageDto());

        createMessage();

    }

    void getUserStatus(String statusCond){
        if(statusCond.equals("ACTIVE")){
            status.setFill(Color.GREEN);
        }else if(statusCond.equals("DoNotDisturb")){
            status.setFill(Color.YELLOW);
        }else if(statusCond.equals("AWAY")){
            status.setFill(Color.RED);
        }else if(statusCond.equals("OFFLINE")){
            status.setFill(Color.GRAY);
        }
    }

    private void createMessage(){
        ObservableList observableList;

            observableList= SidebarController.observableListMap.get(messageDao.getMessageDto().getFriendId());
        observableList.add(messageDao.getMessageDto());
        chatContainer.setItems(observableList);
    }
    public void displayMessage(int id){
        ObservableList list;
        if(ClientMessageImpl.map.get(id) != null){
                list = SidebarController.observableListMap.get(id);
            for (MessageDao messageDao: ClientMessageImpl.map.get(id)) {
                list.add(messageDao.getMessageDto());
            }
            chatContainer.setItems(list);
            ClientMessageImpl.list.clear();
            ClientMessageImpl.map.clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatContainer.setCellFactory(messageListView -> new MessageListViewCell());
    }

    private class MessageListViewCell extends ListCell<MessageDto> {

       private Pane messageCellContainer=new Pane();
        public MessageListViewCell() {

        }

        @Override
        protected void updateItem(MessageDto item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
                if(LoginService.getId() == item.getUserId()){
                    messageCellContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    HBox hbox = stageCoordinator.loadMessage(new MessageDao(item));
                    hbox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    messageCellContainer.getChildren().add(hbox);
                }else {
                    messageCellContainer.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    HBox hBox = stageCoordinator .loadMessage(new MessageDao(item));
                    hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    messageCellContainer.getChildren().add(hBox);
                }

                setGraphic(messageCellContainer);
            } else {
                setGraphic(null);
            }
        }
    }



}