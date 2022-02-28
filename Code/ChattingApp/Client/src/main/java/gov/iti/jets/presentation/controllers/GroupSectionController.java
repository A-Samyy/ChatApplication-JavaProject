package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.GroupDto;
import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.common.interfaces.ServerGroupChatMessageInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.daos.MessageDao;
import gov.iti.jets.service.impl.ClientGroupChatMessageImpl;
import gov.iti.jets.service.services.LoginService;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class GroupSectionController implements Initializable {
    RMIRegister rmiRegister = RMIRegister.getInstance();
    ServerGroupChatMessageInt serverGroupChatMessage = rmiRegister.groupChatMessageService();
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();

    @FXML
    private AnchorPane bottomBar;

    @FXML
    private ListView<MessageGroupDto> chatContainer;

    @FXML
    private FontIcon filesButton;

    @FXML
    private SplitMenuButton htmlEditor;

    @FXML
    private TextField messageTextField;

    @FXML
    private FontIcon sendButton;

    @FXML
    private AnchorPane topBar;

    @FXML
    private Label userName;
    private GroupDto groupDto;
    MessageGroupDto messageGroupDto;

    @FXML
    void onTypingEnter(KeyEvent event) {

    }

    public  void displayGroup(GroupDto groupDto) {
        this.groupDto = groupDto;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userName.setText(groupDto.getGroupName());
            }
        });
    }

    @FXML
    void sendButtonClicked(MouseEvent event) {
        messageGroupDto = new MessageGroupDto();
        messageGroupDto.setGroupId(groupDto.getId());
        messageGroupDto.setSenderId(LoginService.getId());
        messageGroupDto.setMessageContent(messageTextField.getText());
        messageGroupDto.setSenderName(userModel.getUserName());
        messageTextField.setText("");
        try {
            serverGroupChatMessage.sendGroupChatMessage(messageGroupDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        createMessage();
    }
    private void createMessage(){
        ObservableList observableList;
        observableList= SidebarController.observableListMapForGroup.get(messageGroupDto.getGroupId());
        observableList.add(messageGroupDto);
        chatContainer.setItems(observableList);
    }


    public  void displayGroupMessage(int groupId , List<MessageGroupDto> messages) {
        ObservableList list;
        if(ClientGroupChatMessageImpl.mapForGroup.get(groupId) != null){
                list = SidebarController.observableListMapForGroup.get(groupId);
            for (MessageGroupDto messageGroupDto: ClientGroupChatMessageImpl.mapForGroup.get(groupId)) {
                list.add(messageGroupDto);
            }
            chatContainer.setItems(list);
            ClientGroupChatMessageImpl.groupMessages.clear();
            ClientGroupChatMessageImpl.mapForGroup.clear();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatContainer.setCellFactory(messageListView -> new MessageListViewCell());
    }

    private class MessageListViewCell extends ListCell<MessageGroupDto> {

        private Pane messageCellContainer=new Pane();
        public MessageListViewCell() {

        }

        @Override
        protected void updateItem(MessageGroupDto item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {
                if(LoginService.getId() == item.getSenderId()){
                    messageCellContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    HBox hbox = stageCoordinator.loadMessage(new MessageDao(item),1);
                    hbox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    messageCellContainer.getChildren().add(hbox);
                }else {
                    messageCellContainer.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    HBox hBox = stageCoordinator .loadMessage(new MessageDao(item),1);
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
