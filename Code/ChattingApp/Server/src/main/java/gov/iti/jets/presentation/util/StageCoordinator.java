package gov.iti.jets.presentation.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageCoordinator {
    private static final StageCoordinator stageCoordinator = new StageCoordinator();

    private Stage primaryStage;

    private final Map<String,Scene> sceneMap = new HashMap<>();
    private final Map<String,Node> nodeMap = new HashMap<>();

    private StageCoordinator(){}

    public static StageCoordinator getInstance(){
        return stageCoordinator;
    } 

    public void initStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

   public void switchToLoginScreen(){
       Scene loginScene = sceneMap.get("loginScene");
       if(loginScene == null){
           try {
               //change Path
               Parent root  = FXMLLoader.load(getClass().getResource("/views/loginView/loginView.fxml"));
               root.getStylesheets().add(getClass().getResource("/views/loginView/loginStyle.css").toString());
               loginScene = new Scene(root);
               sceneMap.put("loginScene", loginScene);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       primaryStage.setScene(loginScene);
   }
   public void switchToGHomePageScreen(){
       Scene homePageScene = sceneMap.get("homePageScene");
       if(homePageScene == null){
           try {
               //change Path

               System.out.println("marwa");
               Parent root  = FXMLLoader.load(getClass().getResource("/views/homeView/homePage.fxml"));
               root.getStylesheets().add(getClass().getResource("/views/homeView/homePageStyle.css").toString());

               homePageScene = new Scene(root);
               sceneMap.put("homePageScene", homePageScene);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       primaryStage.setScene(homePageScene);
   }
   public Node loadAddUser(){
       Node addUserNode = nodeMap.get("AddUser");
       if(addUserNode == null){
           try {
               //change Path
               Parent root  = FXMLLoader.load(getClass().getResource("/views/addView/addView.fxml"));
               addUserNode = root;
               nodeMap.put("AddUser", root);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       return addUserNode;
   }
}
