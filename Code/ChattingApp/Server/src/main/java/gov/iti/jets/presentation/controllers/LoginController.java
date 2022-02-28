package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presistance.dtos.AdminDto;
import gov.iti.jets.service.services.LoginAdminService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    AdminDto adminDto = new AdminDto();
    LoginAdminService loginAdminService = new LoginAdminService();

    @FXML
    private TextField AdminIDTextField;

    @FXML
    private PasswordField AdminPasswordTextField;

    @FXML
    private GridPane grid;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginPaneContent;

    @FXML
    private Label phoneNumberError;

    @FXML
    private Label welcome;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }



    @FXML
    void OnLoginAction(ActionEvent event) {
        adminDto.setAdminID(Integer.parseInt(AdminIDTextField.getText()));
        adminDto.setPassword(AdminPasswordTextField.getText());
        boolean check = loginAdminService.checkAdminLogin(adminDto);
        if(check){
            stageCoordinator.switchToGHomePageScreen();
        }
    }


}
