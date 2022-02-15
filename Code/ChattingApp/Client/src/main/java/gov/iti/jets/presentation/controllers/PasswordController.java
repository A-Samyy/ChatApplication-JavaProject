package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.impl.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    LoginService loginService = new LoginService();

    @FXML
    private Button backButton;

    @FXML
    private Button forgetbutton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    void OnBackAction(ActionEvent event) {
        stageCoordinator.switchToLoginScreen();
    }

    @FXML
    void OnLoginAction(ActionEvent event) throws RemoteException {
        String userPassword = userModel.getPassword();
        String userPass = loginService.getPassword();
        if(userPassword.equals(userPass)) {
            stageCoordinator.switchToGHomePageScreen();
        }else{
            System.out.println("Password is not Correct");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordTextField.textProperty().bindBidirectional(userModel.passwordProperty());
    }
}
