package gov.iti.jets.presentation.controllers;

import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.MessageService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    LoginService loginService = new LoginService();
    RMIRegister rmiRegister = RMIRegister.getInstance();

    @FXML
    private FontIcon backArrow;

    @FXML
    private GridPane grid;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginPaneContent;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label phoneNumberError;

    @FXML
    private Label welcome;

    @FXML
    void OnBackAction(MouseEvent event) {
        stageCoordinator.switchToLoginScreen();
    }


    @FXML
    void OnLoginAction(ActionEvent event) throws RemoteException {
        String userPassword = passwordTextField.getText();
        String userPass = loginService.getPassword();
        if(userPassword.equals(userPass)) {
            loginService.getdata();
            MessageService.getInstance();
            stageCoordinator.switchToGHomePageScreen();
        }else{
            System.out.println("Password is not Correct");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordTextField.setText("");
    }
}
