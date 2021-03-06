package gov.iti.jets.presentation.util;

import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.dtos.FileRequestDto;
import gov.iti.jets.common.dtos.FriendRequestSenderDto;
import gov.iti.jets.common.dtos.GroupDto;
import gov.iti.jets.presentation.controllers.*;
import gov.iti.jets.service.daos.MessageDao;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.*;

import static java.lang.System.out;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();
    private Stage primaryStage;
    private GridPane homepage;
    private ChatSectionController chatSectionController;
    private GroupSectionController groupSectionController;

    public static void main(String args[]) throws SocketException, UnknownHostException {

//        out.println(InetAddress.getLocalHost());
//        out.println(Inet4Address.getLocalHost());

/*        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)){
            out.printf("Display name: %s\n", netint.getDisplayName());
            out.printf("Name: %s\n", netint.getName());
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                out.printf("InetAddress: %s\n", inetAddress);
            }
            out.printf("\n");
        }*/
    }

    private final Map<String, Parent> parentMap = new HashMap<>();
    private final Map<String, Stage> stageMap = new HashMap<>();

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
        Parent loginParent = null; // parentMap.get("loginParent");
        try {
            out.println("in switch to logout");
            Parent root = FXMLLoader.load(getClass().getResource("/views/LoginSection1/LoginView2.fxml"));
            loginParent = root;

        } catch (IOException e) {
            e.printStackTrace();
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
        if(primaryStage.getScene() == null){
            primaryStage.setScene(new Scene(homePageParent));
        }
        else{
            primaryStage.getScene().setRoot(homePageParent);
        }
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
            ContactController contactController = loader.getController();
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
            GroupListController groupListController = loader.getController();
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
        Stage addNewGroup = stageMap.get("addGroup");
        if(addNewGroup == null){
            addNewGroup = new Stage();
            stageMap.put("addGroup",addNewGroup);
        }
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

    public void loadAbout() {
        Stage aboutStage = new Stage();
        AnchorPane aboutPane = null;
        try {
            aboutPane = FXMLLoader.load(getClass().getResource("/views/about/about.fxml"));
        } catch (Exception e) {
        }
        Scene scene = new Scene(aboutPane);
        aboutStage.setScene(scene);
        aboutStage.setTitle("KAT app");
        aboutStage.show();
    }

    public void loadProgressBar() {
        Stage progressBarStage= stageMap.get("progressbar");
        if(progressBarStage == null){
            progressBarStage = new Stage();
            stageMap.put("progressbar",progressBarStage);
        }
        Pane progessBarPane = null;
        try {
            progessBarPane = FXMLLoader.load(getClass().getResource("/views/progressBar/progressBar.fxml"));
        } catch (Exception e) {
        }
        Scene scene = new Scene(progessBarPane);
        progressBarStage.setScene(scene);
        progressBarStage.setTitle("File transfer progress");
        progressBarStage.show();
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

    public Node loadDefault() {
        Node defaultbar = null;
        try {
            defaultbar = FXMLLoader.load(getClass().getResource("/views/HomePageSection1/defaultHomeScreen1.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultbar;
    }

    public Node loadAdminMessageNotification() {
        Node adminNotification = null;
        try {
            adminNotification = FXMLLoader.load(getClass().getResource("/views/AdminMessageNotification/adminMessageNotification.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminNotification;
    }

    public Node loadChatSection(String name, Image pic, String status, int id) {
        Node chatSection = chatSectionMap.get(id);
        if (chatSection == null) {
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
                chatSectionControllerMap.get(id).display(name, pic, status, id);
            }
        });

        return chatSection;
    }

    public Node loadChatSectionForGroup(GroupDto groupDto) {
        Node groupSection = groupSectionMap.get(groupDto.getId());
        if (groupSection == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/HomePageSection2/groupChatSection.fxml"));
            try {
                groupSection = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            groupSectionController = loader.getController();
            groupSectionMap.put(groupDto.getId(), groupSection);
            groupSectionControllerMap.put(groupDto.getId(), groupSectionController);
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
        Stage addNewContact= stageMap.get("addContact");
        if(addNewContact == null){
            addNewContact = new Stage();
            stageMap.put("addContact",addNewContact);
        }
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

    public void loadAdminMessageContainer(Map<Integer,List<String>> messagesFromAdmin) throws IOException {
        Stage addAdminMessageContainer= stageMap.get("addAdminMessageContainer");
        if(addAdminMessageContainer == null){
            addAdminMessageContainer = new Stage();
            stageMap.put("addAdminMessageContainer",addAdminMessageContainer);
        }
        AdminMessageContainerController adminMessageController ;
        FXMLLoader loader = new FXMLLoader();
        Parent addAdminMessage = null;
        try {


            loader.setLocation(getClass().getResource("/views/AdminMessageNotification/adminMessages.fxml"));
        } catch (Exception e) {
        }
        addAdminMessage = loader.load();
        adminMessageController = (AdminMessageContainerController) loader.getController();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                out.println("imhere");
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
        addAdminMessageContainer.setTitle("Admin Messages");
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


    public StylingController loadStyling() {
        Stage stylingStage= stageMap.get("stylingStage");
        if(stylingStage == null){
            stylingStage = new Stage();
            stageMap.put("stylingStage",stylingStage);
        }
        Pane styling = null;
        FXMLLoader loader = new FXMLLoader();

        try {
            loader.setLocation(getClass().getResource("/views/style/styleView.fxml"));
            styling = loader.load();
        } catch (Exception e) {
        }
        StylingController stylingController= ( StylingController) loader.getController();

        Scene scene = new Scene(styling);
        stylingStage.setScene(scene);
        stylingStage.setTitle("Choose Style");
        stylingStage.show();
        return stylingController;
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
                         messageController.displayMessage(messageDao.getMessageContent(), messageDao.getMessageUserName() , messageDao.getMessageColor());

                     }else{
                         messageController.displayMessage(messageDao.getMessageGroupContent(), messageDao.getMessageGroupSenderName() , messageDao.getMessageGroupStyle());

                    }
                }
            });
            return message;
        } catch (Exception e) {
        }
        return null;

    }
//    public static void main(String args[]) throws SocketException, UnknownHostException {
//        out.println(InetAddress.getLocalHost().getHostAddress());
//        out.println(Inet4Address.getLocalHost().getHostAddress());
//
//        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
//        for (NetworkInterface netint : Collections.list(nets))
//            displayInterfaceInformation(netint);
//    }
//
//    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
//        out.printf("Display name: %s\n", netint.getDisplayName());
//        out.printf("Name: %s\n", netint.getName());
//        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
//        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
//            out.printf("InetAddress: %s\n", inetAddress);
//        }
//        out.printf("\n");
//    }
}
