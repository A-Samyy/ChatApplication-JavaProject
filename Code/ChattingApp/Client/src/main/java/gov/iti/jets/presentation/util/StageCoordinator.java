package gov.iti.jets.presentation.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();

    private Stage primaryStage;

    private final Map<String, Scene> sceneMap = new HashMap<>();
    private final Map<String, Node> nodeMap = new HashMap<>();

    private StageCoordinator() {
    }

    public static StageCoordinator getInstance() {
        return stageCoordinator;
    }

    public void initStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void switchToLoginScreen() {
        Scene loginScene = sceneMap.get("loginScene");
        if (loginScene == null) {
            try {
                // change Path
                Parent root = FXMLLoader.load(getClass().getResource("/views/LoginSection/LoginView.fxml"));
                loginScene = new Scene(root);
                sceneMap.put("loginScene", loginScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(loginScene);
    }

    public void switchToGHomePageScreen() {
        Scene homePageScene = sceneMap.get("homePageScene");
        if (homePageScene == null) {
            try {
                // change Path
                GridPane root = FXMLLoader.load(getClass().getResource("/views/homePage/homePage.fxml"));
                homePageScene = new Scene(root);
                sceneMap.put("homePageScene", homePageScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(homePageScene);
    }

    public void switchToRegistrationScreen() {
        Scene registrationScene = sceneMap.get("registrationScene");
        if (registrationScene == null) {
            try {
                // change Path
                Parent root = FXMLLoader.load(getClass().getResource("/views/RegisterSection/RegisterView.fxml"));
                registrationScene = new Scene(root);
                sceneMap.put("registrationScene", registrationScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(registrationScene);
    }

    public Node loadContacts() {
        Node contactList = nodeMap.get("contactList");
        if (contactList == null) {
            try {
                contactList = FXMLLoader.load(getClass().getResource("/views/contactSection/contactList.fxml"));
                nodeMap.put("contactList", contactList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return contactList;
    }

    public Node loadProfile() {
        Node profile = nodeMap.get("profile");
        if (profile == null) {
            try {
                profile = FXMLLoader.load(getClass().getResource("/views/profile/profileView.fxml"));
                nodeMap.put("profile", profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profile;
    }

    public void loadAddContact() {
        Stage addNewContact = new Stage();
        Pane addContact = null;
        try {
            addContact = FXMLLoader.load(getClass().getResource("/views/contactSection/addContact.fxml"));
        } catch (Exception e) {
            System.out.println("File Not Found Exception");
        }
        Scene scene = new Scene(addContact);
        addNewContact.setScene(scene);
        addNewContact.setTitle("Add New Contact");
        addNewContact.show();
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
            System.out.println("File Not Found Exception");
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
            System.out.println("File Not Found Exception");
        }
        Scene scene = new Scene(theme);
        themeStage.setScene(scene);
        themeStage.setTitle("Choose Theme");
        themeStage.show();

    }

    public AnchorPane loadMyChat() {
        AnchorPane chatSectiodn = null;
        try {
            chatSectiodn = FXMLLoader.load(getClass().getResource("/views/chattingSection/chatSection.fxml"));
        } catch (Exception e) {
            System.out.println("File Not Found Exception");
        }
        return chatSectiodn;
    }

    // public Pane loadUserPane(){
    // try {
    // Pane userPane =
    // FXMLLoader.load(getClass().getResource("/views/contactSection/userPane.fxml"));
    // return userPane;
    // }catch(Exception e){
    // System.out.println("File Not Found Exception");
    // }

    // return null ;
    // }

    // public VBox loadMessage(){
    // try {
    // VBox message =
    // FXMLLoader.load(getClass().getResource("/views/message/messageView.fxml"));
    // return message;
    // }catch(Exception e){
    // System.out.println("File Not Found Exception");
    // }
    // return null ;
    // }
}
