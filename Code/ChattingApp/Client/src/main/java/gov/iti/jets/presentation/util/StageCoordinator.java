package gov.iti.jets.presentation.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
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

    // public void switchToGHomePageScreen(){
    // Scene homePageScene = sceneMap.get("homePageScene");
    // if(homePageScene == null){
    // try {
    // //change Path
    // GridPane root =
    // FXMLLoader.load(getClass().getResource("/views/homePage/homePage.fxml"));
    // homePageScene = new Scene(root);
    // sceneMap.put("homePageScene", homePageScene);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // primaryStage.setScene(homePageScene);
    // }
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

    public Parent loadContacts() {
        try {
            System.out.println("before load");
            Parent contactList = FXMLLoader.load(getClass().getResource("/views/contactSection/contactList.fxml"));
            System.out.println("After load");
            return contactList;
        } catch (Exception e) {
            System.out.println("File Not Found Exception");
        }
        System.out.println(" it return nulls");
        return null;
    }

    public void switchToTestScreen() {
        Scene testScene = sceneMap.get("TestScene");
        if (testScene == null) {
            try {
                // change Path
                Parent root = FXMLLoader.load(getClass().getResource("/views/test.fxml"));
                testScene = new Scene(root);
                sceneMap.put("testScene", testScene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        primaryStage.setScene(testScene);
    }
    // public Pane loadAddContact(){
    // try {
    // Pane addContact =
    // FXMLLoader.load(getClass().getResource("/views/contactSection/addContact.fxml"));
    // return addContact;
    // }catch(Exception e){
    // System.out.println("File Not Found Exception");
    // }

    // return null ;
    // }

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

    // public BorderPane loadSettings(){
    // try {
    // BorderPane contactList =
    // FXMLLoader.load(getClass().getResource("/views/settings/settingScreen.fxml"));
    // return contactList;
    // }catch(Exception e){
    // System.out.println("File Not Found Exception");
    // }
    // return null ;
    // }
    // public Pane loadhelp(){
    // try {
    // Pane contactList =
    // FXMLLoader.load(getClass().getResource("/views/settings/helpPane.fxml"));
    // return contactList;
    // }catch(Exception e){
    // System.out.println("File Not Found Exception");
    // }
    // return null ;
    // }
    // public Pane loadTheme(){
    // try {
    // Pane theme =
    // FXMLLoader.load(getClass().getResource("/views/settings/editTheme.fxml"));
    // return theme;
    // }catch(Exception e){
    // System.out.println("File Not Found Exception");
    // }
    // return null ;
    // }
    // public AnchorPane loadMyChat(){
    // try {
    // AnchorPane chatSectiodn =
    // FXMLLoader.load(getClass().getResource("/views/chattingSection/chatSection.fxml"));
    // return chatSectiodn;
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
