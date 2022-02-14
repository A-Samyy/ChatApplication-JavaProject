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

public class LoginController implements  Initializable{

    private StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    LoginService loginService = new LoginService();



    @FXML
    Button loginButton;
    @FXML
    Button registrationButton;
    @FXML
    private TextField phone_number_text_field;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        phone_number_text_field.textProperty().bindBidirectional(userModel.phoneNumberProperty());

    }
    public LoginController(){}

    @FXML
    void OnLoginAction(ActionEvent event) throws RemoteException {


        loginService.getUserId("333284");
        loginService.getPassword();
        System.out.println(userPass);
        String pass = "3434";

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
