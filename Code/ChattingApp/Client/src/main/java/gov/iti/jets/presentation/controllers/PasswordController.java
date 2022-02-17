package gov.iti.jets.presentation.controllers;

import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    LoginService loginService = new LoginService();

    @FXML
    private Label passwordError;

    @FXML
    private TextField passwordTextField;

    @FXML
    void OnBackAction(MouseEvent event) {
        stageCoordinator.switchToLoginScreen();
    }
    RMIRegister rmiRegister = RMIRegister.getInstance();

    @FXML
    void OnLoginAction(ActionEvent event) throws RemoteException {
        String userPassword = userModel.getPassword();
        String userPass = loginService.getPassword();
        if(userPassword.equals(userPass)) {
            loginService.getdata();
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
