package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController implements  Initializable{

    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    LoginService loginService = new LoginService();

    @FXML
    Button loginButton;
    @FXML
    Button registrationButton;
    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        phoneNumberTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());
        passwordTextField.textProperty().bindBidirectional(userModel.passwordProperty());

    }
    public LoginController(){}

    @FXML
    void OnLoginAction(ActionEvent event) throws RemoteException {

        int userId = loginService.getUserId("333333355");
        String userPass = loginService.getPassword();
        String pass = "951357";

        if ((userId > -1) && (userPass.equals(pass))){
            stageCoordinator.switchToGHomePageScreen();
        }else {
            System.out.println("Please register Or youPass is invalid");
        }
    }

    @FXML
    void OnRegestrationAction(ActionEvent event) {
       stageCoordinator.switchToRegistrationScreen();
    }

    
}
