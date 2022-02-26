package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.dtos.FriendRequestSenderDto;
import gov.iti.jets.common.dtos.GroupDto;
import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.interfaces.ServerMessageAnnouncetInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.models.ContactModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class SidebarController implements Initializable {
    static public Map<Integer , ObservableList<MessageDto>> observableListMap = new TreeMap<>();
    ObservableList list ;
    RMIRegister rmiRegister = RMIRegister.getInstance();
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    ContactListService contactListService = new ContactListService();
    FriendRequestService friendRequestService = new FriendRequestService();
    GroupListService groupListService = new GroupListService();
    MessageDto messageDto=new MessageDto();
    ServerMessageAnnouncetInt serverMessageAnnouncetInt = rmiRegister.serverMessageAnnouncetInt();
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
    ContactModel contactModel;

    public SidebarController() throws RemoteException {

    }
    @FXML
    void OnAddingGroup(MouseEvent event) {
        stageCoordinator.loadAddGroup();
    }


    @FXML
    void logoutOnMouseClick(MouseEvent event) {
        stageCoordinator.switchToLoginScreen();
    }

    @FXML
    void OnChangingTab(MouseEvent event) {
        if(tabPane.getSelectionModel().getSelectedIndex()==0){
            getContact();
        }else if(tabPane.getSelectionModel().getSelectedIndex()==1){
            getGroup();
        }else if(tabPane.getSelectionModel().getSelectedIndex()==3){
            getNotification();
        }
    }
    void getContact(){
        chattingSectionVbox.getChildren().clear();
        if(!contactListService.getListOfContact((LoginService.getId())).isEmpty()){
            for (ContactDto contactDto : contactListService.getListOfContact(LoginService.getId())) {
                list = FXCollections.observableArrayList();
                observableListMap.put(contactDto.getId(),list);
                chattingSectionVbox.getChildren().add(stageCoordinator.loadContacts(contactDto));
            }
        }
    }
    void getGroup(){
        chattingGroupAreaVbox.getChildren().clear();
        if(!groupListService.getListOfGroup(LoginService.getId()).isEmpty()){
            for (GroupDto groupDto : groupListService.getListOfGroup(LoginService.getId())) {
//            contactModel = new ContactModel();
                chattingGroupAreaVbox.getChildren().add(stageCoordinator.loadGroups(groupDto));
            }
        }
    }
    void getNotification(){
        SettingAreaVbox.getChildren().clear();
        try {
            if(!friendRequestService.getFriendRequestsNotifcation().isEmpty()){
                for(FriendRequestSenderDto friendRequestSenderDto : friendRequestService.getFriendRequestsNotifcation()){
                    System.out.println(friendRequestSenderDto);
                    SettingAreaVbox.getChildren().add(stageCoordinator.loadFriendRequest(friendRequestSenderDto.getSenderName(),friendRequestSenderDto));

                }
            }
            if(serverMessageAnnouncetInt.getMessage()){
               SettingAreaVbox.getChildren().add(stageCoordinator.loadAdminMessageNotification());
            }


        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void OnAddingContact(MouseEvent event) {
        stageCoordinator.loadAddContact();
    }

    @FXML
    void openProfileOnMouseClick(MouseEvent event) {
        stageCoordinator.switchToProfileScreen();
    }

    @FXML
    void searchOnKeyTyped(KeyEvent event) {

    }




//    @FXML
//    void OnAddContactMouseClick(MouseEvent event) {
//        stageCoordinator.loadAddContact();
//    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ;
        img = new ImageView();
        img.imageProperty().bindBidirectional(userModel.imageProperty());
        profilePic.setFill(new ImagePattern(img.getImage()));
        getUserStatus(userModel.getStatus());
        bio.textProperty().bindBidirectional(userModel.bioProperty());
        userName.textProperty().bindBidirectional(userModel.userNameProperty());
        if(!contactListService.getListOfContact((LoginService.getId())).isEmpty()){
            for (ContactDto contactDto : contactListService.getListOfContact(LoginService.getId())) {
                list = FXCollections.observableArrayList();
                observableListMap.put(contactDto.getId(),list);
                chattingSectionVbox.getChildren().add(stageCoordinator.loadContacts(contactDto));
            }
        }




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


}
