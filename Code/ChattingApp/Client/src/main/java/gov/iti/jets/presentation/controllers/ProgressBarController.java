package gov.iti.jets.presentation.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressBarController implements Initializable {

    @FXML
    private FontIcon doneIcon;

    @FXML
    private Label fileReceivedLabel;

    @FXML
    private Button doneButton;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    void OnAddNewGroupClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
