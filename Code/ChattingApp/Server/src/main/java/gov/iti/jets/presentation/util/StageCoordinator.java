package gov.iti.jets.presentation.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.presentation.controllers.MessageServerController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();

    private Stage primaryStage;

    private final Map<String,Parent> parentMap = new HashMap<>();
    private final Map<String,Node> nodeMap = new HashMap<>();

    private StageCoordinator(){}

    public static StageCoordinator getInstance(){
        return stageCoordinator;
    } 

    public void initStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

   public void switchToLoginScreen(){
       Parent loginRoot = parentMap.get("loginScene");
       if(loginRoot == null){
           try {
               Parent root  = FXMLLoader.load(getClass().getResource("/views/loginView/loginView2.fxml"));
               root.getStylesheets().add(getClass().getResource("/views/loginView/loginStyle.css").toString());
               parentMap.put("loginRoot", root);
               loginRoot = root;
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       primaryStage.setScene(new Scene(loginRoot));
   }
   public void switchToGHomePageScreen(){
       Parent homePageRoot = parentMap.get("homePageScene");
       Parent root ;
       if(homePageRoot == null){
           try {
               root  = FXMLLoader.load(getClass().getResource("/views/homeView/homePage.fxml"));
               root.getStylesheets().add(getClass().getResource("/views/homeView/homePageStyle.css").toString());
               parentMap.put("homePageRoot",root);
               homePageRoot = root;
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       primaryStage.getScene().setRoot(homePageRoot);
   }
   public Node loadAddUser(){
       Node addUserNode = nodeMap.get("AddUser");
       if(addUserNode == null){
           try {
               Parent root  = FXMLLoader.load(getClass().getResource("/views/addView/addView.fxml"));
               addUserNode = root;
               nodeMap.put("AddUser", root);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       return addUserNode;
   }


    public HBox loadMessage(MessageAnnounceDto messageAnnounceDto) {
        try {
       FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/messageView/messageView.fxml"));

            HBox message = loader.load();
            MessageServerController messageController = (MessageServerController) loader.getController();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    messageController.displayMessage(messageAnnounceDto.getMessageContent(), messageAnnounceDto.getMessageSender());
                }
            });
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }}