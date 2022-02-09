package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;



import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class HomePageController implements Initializable{
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    @FXML
    private AnchorPane content;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab userAdd;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      
        ( (AnchorPane)userAdd.getContent()).getChildren().add(stageCoordinator.loadAddUser());
        
           
    }

    

    
    

}
