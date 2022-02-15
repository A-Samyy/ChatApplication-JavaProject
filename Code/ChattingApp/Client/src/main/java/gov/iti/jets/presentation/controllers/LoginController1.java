package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.dtos.LoginDto;
import gov.iti.jets.service.impl.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController1 implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    LoginService loginService = new LoginService();

    @FXML
    private Button createbutton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    void OnLoginAction(ActionEvent event) throws RemoteException{

        int userId = loginService.getUserId(userModel.getPhoneNumber());
//        String userPass = loginService.getPassword();
//
//        System.out.println(userPass);
//        String pass = "951357";

        if ((userId > -1)){
            stageCoordinator.switchToPasswordScreen();
        }else {
            System.out.println("Please register Or youPass is invalid");
        }

    }

    @FXML
    void OnRegestrationAction(ActionEvent event) {
        stageCoordinator.switchToRegistrationScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        phoneNumberTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());

    }
}
