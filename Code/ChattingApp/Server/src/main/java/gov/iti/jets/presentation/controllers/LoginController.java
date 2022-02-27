package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presistance.dtos.AdminDto;
import gov.iti.jets.service.services.LoginAdminService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    AdminDto adminDto = new AdminDto();
    LoginAdminService loginAdminService = new LoginAdminService();

    @FXML
    private TextField AdminIDTextField;

    @FXML
    private TextField AdminPasswordTextField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    @FXML
    private Button loginButton;

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
