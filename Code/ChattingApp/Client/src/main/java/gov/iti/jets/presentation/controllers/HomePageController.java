package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.kordamp.ikonli.javafx.FontIcon;

import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HomePageController implements Initializable {
  StageCoordinator stageCoordinator = StageCoordinator.getInstance();

  @FXML
  public GridPane gridPane;



  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    stageCoordinator.setHomepage(gridPane);
    gridPane.add( stageCoordinator.loadSidebar(), 0, 0);
  }

}
