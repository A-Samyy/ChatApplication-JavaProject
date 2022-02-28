package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.interfaces.ClientAnnounceMessageInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.MessageService;
import gov.iti.jets.service.services.RememberMeServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.CompoundValidationDecoration;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class PasswordController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    LoginService loginService = new LoginService();
    RememberMeServices rememberMeServices ;
    RMIRegister rmiRegister = RMIRegister.getInstance();
    ClientAnnounceMessageInt clientAnnounceMessageInt ;
    Preferences userInfo = Preferences.userNodeForPackage(getClass());
    ValidationSupport validationSupport=new ValidationSupport();

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
        if(!loginService.getPassword().equals("stopped")) {
            if (userPassword.equals(userPass)) {
                loginService.getdata();
                MessageService.getInstance();
                stageCoordinator.switchToGHomePageScreen();
                passwordTextField.setText("");
            } else {
            }
            validationSupport.setValidationDecorator(new StyleClassValidationDecoration());

            validationSupport.setValidationDecorator(new CompoundValidationDecoration(
                    new GraphicValidationDecoration(),
                    new StyleClassValidationDecoration()));
            validationSupport.registerValidator(passwordTextField, false, Validator.createEmptyValidator(
                    "Password is required",
                    Severity.WARNING));
        }else {
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        passwordTextField.setText("");
        System.out.println("from password page "+userInfo.get("phone number","no phone number"));

    }
}
