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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class LoginController1 implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    LoginService loginService = new LoginService();

    @FXML
    private FontIcon backArrow;

    @FXML
    private Label createbutton;

    @FXML
    private GridPane grid;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane loginPaneContent;

    @FXML
    private Label phoneNumberError;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Label welcome;

    @FXML
    void ToWelcom(MouseEvent event){
        stageCoordinator.switchToWelcomScreen();
    }

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
    void OnRegestrationAction(MouseEvent ev1ent) {
        stageCoordinator.switchToRegistrationScreen();
    }
//    @FXML
//    void ToWelcom(MouseEvent event) {
//        stageCoordinator.switchToWelcomScreen();
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        phoneNumberTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());

    }
}
