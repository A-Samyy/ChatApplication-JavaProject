package gov.iti.jets.presentation.util;

import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.dtos.FileRequestDto;
import gov.iti.jets.common.dtos.FriendRequestSenderDto;
import gov.iti.jets.common.dtos.GroupDto;
import gov.iti.jets.presentation.controllers.*;

import javafx.application.Platform;
import java.io.File;
import java.io.IOException;
import java.util.*;

import gov.iti.jets.presentation.controllers.MessageController;
import gov.iti.jets.service.daos.MessageDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();
    private Stage primaryStage;
    private GridPane homepage;
    private ChatSectionController chatSectionController;
    private GroupSectionController groupSectionController;


    private final Map<String, Parent> parentMap = new HashMap<>();
    private final Map<String, Node> nodeMap = new HashMap<>();
    private final Map<Integer, Node> chatSectionMap = new HashMap<>();
    private final Map<Integer, ChatSectionController> chatSectionControllerMap = new HashMap<>();

    private final Map<Integer, Node> groupSectionMap = new HashMap<>();
    private final Map<Integer, GroupSectionController> groupSectionControllerMap = new HashMap<>();

    private StageCoordinator() {
    }

    public static StageCoordinator getInstance() {
        return stageCoordinator;
    }

    public void initStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setHomepage(GridPane gridPane) {
        homepage = gridPane;
    }

    public GridPane getHomepage() {
        return homepage;
    }

    public String openFile() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pic Chooser");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            return file.getPath();
        } else {
            return null;
        }
    }

    public void switchToWelcomScreen() {
        Parent welcomeParent = parentMap.get("welcomeParent");
        if (welcomeParent == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/WelcomePage/LoginView.fxml"));
                welcomeParent = root;
                parentMap.put("welcomeParent", root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        Win_primary.getScene()
        primaryStage.setScene(new Scene(welcomeParent));
    }

    public void switchToLoginScreen() {
        Parent loginParent = parentMap.get("loginParent");
        if (loginParent == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/LoginSection1/LoginView2.fxml"));
                loginParent = root;
                parentMap.put("loginParent", root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.getScene().setRoot(loginParent);
    }

    public void switchToPasswordScreen() {
        Parent passwordParent = parentMap.get("passwordParent");
        if (passwordParent == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/passwordsection/passwordView.fxml"));
                passwordParent = root;
                parentMap.put("passwordScene", root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.getScene().setRoot(passwordParent);
    }

    public void switchToGHomePageScreen() {
        Parent homePageParent = null;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/HomePageStructure/homePage.fxml"));
            homePageParent = root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(homePageParent);
    }

    public void switchToProfileScreen() {
        Parent profileParent = null;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/profile/editProfileSection.fxml"));
            profileParent = root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.getScene().setRoot(profileParent);
    }

    public void switchToRegistrationScreen() {
        Parent registrationParent = parentMap.get("registrationParent");
        if (registrationParent == null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/views/RegisterSection/RegisterView.fxml"));
                registrationParent = root;
                parentMap.put("registrationParent", root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.getScene().setRoot(registrationParent);
    }

    public Node loadContacts(ContactDto contactDto) {
        Node contactList = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/chattingSection/chatSection.fxml"));
            contactList = loader.load();
            ContactController contactController =loader.getController();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        contactController.displayContact(contactDto);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }

        return contactList;
    }

    public Node loadGroups(GroupDto groupDto) {
        Node grouplist = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/contactSection/userPane.fxml"));
            grouplist = loader.load();
            GroupListController groupListController =loader.getController();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        groupListController.displayGroup(groupDto);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();

        }

        return grouplist;
    }

    public void loadAddGroup() {
        Stage addNewGroup = new Stage();
        Pane addGroup = null;
        try {
            addGroup = FXMLLoader.load(getClass().getResource("/views/contactSection/addGroup.fxml"));
        } catch (Exception e) {
        }
        Scene scene = new Scene(addGroup);
        addNewGroup.setScene(scene);
        addNewGroup.setTitle("Add New Group");
        addNewGroup.show();
    }

    public Node loadSidebar() {
        Node sidebar = null;
        try {
            sidebar = FXMLLoader.load(getClass().getResource("/views/SidebarSection/sideBar.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sidebar;
    }

    public Node loadDefault(){
        Node defaultbar = null;
        try {
            defaultbar = FXMLLoader.load(getClass().getResource("/views/HomePageSection1/defaultHomeScreen1.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultbar;
    }

    public Node loadAdminMessageNotification(){
        Node adminNotification = null;
        try {
            adminNotification = FXMLLoader.load(getClass().getResource("/views/AdminMessageNotification/adminMessageNotification.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminNotification;
    }

    public Node loadChatSection(String name, Image pic , String status , int id){
        Node chatSection = chatSectionMap.get(id);
        if(chatSection == null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/HomePageSection2/homePageSection2.fxml"));
            try {
                chatSection = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            chatSectionController = loader.getController();
            chatSectionMap.put(id,chatSection);
            chatSectionControllerMap.put(id,chatSectionController);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chatSectionControllerMap.get(id).display(name,pic,status , id);
            }
        });

        return chatSection;
    }

    public Node loadChatSectionForGroup(GroupDto groupDto){
        Node groupSection = groupSectionMap.get(groupDto.getId());
        if(groupSection == null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/HomePageSection2/groupChatSection.fxml"));
            try {
                groupSection = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            groupSectionController = loader.getController();
            groupSectionMap.put(groupDto.getId(),groupSection);
            groupSectionControllerMap.put(groupDto.getId(),groupSectionController);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                groupSectionControllerMap.get(groupDto.getId()).displayGroup(groupDto);
            }
        });

        return groupSection;
    }
    public Map<Integer,ChatSectionController> getChatSectionController(){
        return chatSectionControllerMap;
    }
    public Map<Integer,GroupSectionController> getGroupChatSectionController(){
        return groupSectionControllerMap;
    }

    public Node loadFriendRequest(String name, FriendRequestSenderDto friendRequestSenderDto){
        Node friendReq = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/contactSection/friendReq.fxml"));
            friendReq = loader.load();
            FriendRequestController friendRequestCont= loader.getController();
            friendRequestCont.dispalyFriendReq(name , friendRequestSenderDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return friendReq;
    }

    public Node loadFileRequest(String name, FileRequestDto fileRequestDto){
        Node fileReq = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/contactSection/fileRequest.fxml"));
//            friendReq = FXMLLoader.load(getClass().getResource("/views/contactSection/friendReq.fxml"));
            fileReq = loader.load();
           FileRequestController fileRequestController= loader.getController();
            fileRequestController.dispalyFileReq(fileRequestDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileReq;
    }
    public void loadAddContact() {
        Stage addNewContact = new Stage();
        Pane addContact = null;
        try {
            addContact = FXMLLoader.load(getClass().getResource("/views/contactSection/addContact.fxml"));
        } catch (Exception e) {
        }
        Scene scene = new Scene(addContact);
        addNewContact.setScene(scene);
        addNewContact.setTitle("Add New Contact");
        addNewContact.show();
    }

    public void loadAdminMessageContainer(List<String> messagesFromAdmin) throws IOException {
        System.out.println(messagesFromAdmin.size());
        Stage addAdminMessageContainer = new Stage();
        AdminMessageContainerController adminMessageController ;
        FXMLLoader loader = new FXMLLoader();
        Parent addAdminMessage = null;
        try {


            loader.setLocation(getClass().getResource("/views/AdminMessageNotification/adminMessages.fxml"));
        } catch (Exception e) {
        }
            addAdminMessage = loader.load();
            adminMessageController = ( AdminMessageContainerController) loader.getController();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("imhere");
                    adminMessageController.displayMessage(messagesFromAdmin);
                }
            });

        Scene scene = new Scene(addAdminMessage);
        addAdminMessageContainer.setScene(scene);
        addAdminMessageContainer.setTitle("Add New Contact");
        addAdminMessageContainer.setTitle("Add New Contact");
        addAdminMessageContainer.setOnHidden(e -> {
            adminMessageController.shutdown();
        });
        addAdminMessageContainer.show();
    }


    public Node loadSettings() {
        Node settings = nodeMap.get("settings");
        if (settings == null) {
            try {
                settings = FXMLLoader.load(getClass().getResource("/views/settings/settingScreen.fxml"));
                nodeMap.put("settings", settings);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return settings;
    }

    public void loadhelp() {
        Stage helpStage = new Stage();
        Pane help = null;
        try {
            help = FXMLLoader.load(getClass().getResource("/views/settings/helpPane.fxml"));
        } catch (Exception e) {
        }
        Scene scene = new Scene(help);
        helpStage.setScene(scene);
        helpStage.setTitle("Help Me");
        helpStage.show();
    }

    public void loadTheme() {
        Stage themeStage = new Stage();
        Pane theme = null;
        try {
            theme = FXMLLoader.load(getClass().getResource("/views/settings/editTheme.fxml"));
        } catch (Exception e) {
        }
        Scene scene = new Scene(theme);
        themeStage.setScene(scene);
        themeStage.setTitle("Choose Theme");
        themeStage.show();

    }

     public HBox loadMessage( MessageDao messageDao , int flag) {


         try {
            FXMLLoader loader = new FXMLLoader();
             loader.setLocation(getClass().getResource("/views/message/messageView.fxml"));

             HBox message = loader.load();
             MessageController messageController = (MessageController) loader.getController();
             Platform.runLater(new Runnable() {
                 @Override
                 public void run() {
                     if(flag == 0 ){
                         messageController.displayMessage(messageDao.getMessageContent(), messageDao.getMessageUserName());

                     }else{
                         messageController.displayMessage(messageDao.getMessageGroupContent(), messageDao.getMessageGroupSenderName());

                     }
                 }
             });
             return message;
         } catch (Exception e) {
         }
         return null;

     }}
