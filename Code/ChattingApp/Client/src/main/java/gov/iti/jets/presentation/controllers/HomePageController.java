package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class HomePageController implements Initializable {
  private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
  private final ModelFactory modelFactory = ModelFactory.getInstance();
  UserModel userModel = modelFactory.getUserModel();

  @FXML
  public GridPane gridPane;



  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    stageCoordinator.setHomepage(gridPane);
    gridPane.add( stageCoordinator.loadSidebar(), 0, 0);


  }

}
