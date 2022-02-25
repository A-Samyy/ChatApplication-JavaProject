package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.UpdateDto;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.UpdateUserService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.CompoundValidationDecoration;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ProfileController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    UpdateUserService updateUserService = new UpdateUserService();
    ValidationSupport validationSupport =new ValidationSupport();
    @FXML
    private RadioButton active;

    @FXML
    private RadioButton away;

    @FXML
    private Label backButton;

    @FXML
    private AnchorPane background;

    @FXML
    private TextArea bio;

    @FXML
    private RadioButton busy;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private AnchorPane editingBox;

    @FXML
    private GridPane gridPane;

    @FXML
    private PasswordField password;

    @FXML
    private Circle pofilePic;

    @FXML
    private Button saveButton;

    @FXML
    private GridPane top2;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userName;

    @FXML
    private TextField userPhone;

    @FXML
    private Label confirmPasswordError;

    private ToggleGroup toggleGroup = new ToggleGroup();;
    RadioButton selectedRadioButton;
    GridPane homeGrid;
    ImageView img;

    @FXML
    void OnBackAction(MouseEvent event) {
        stageCoordinator.switchToGHomePageScreen();
    }

    @FXML
    void OnChoosingStatus(ActionEvent event) {

        active.setToggleGroup(toggleGroup);
        busy.setToggleGroup(toggleGroup);
        away.setToggleGroup(toggleGroup);
        selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        userModel.setStatus(selectedRadioButton.getText());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        img = new ImageView();
        img.imageProperty().bindBidirectional(userModel.imageProperty());
        pofilePic.setFill(new ImagePattern(img.getImage()));
        getUserStatus();

        bio.textProperty().bindBidirectional(userModel.bioProperty());
        userName.textProperty().bindBidirectional(userModel.userNameProperty());
        userEmail.textProperty().bindBidirectional(userModel.emailProperty());
        userPhone.textProperty().bindBidirectional(userModel.phoneNumberProperty());

//        st
    }
    void getUserStatus(){
        if(userModel.getStatus().equals("ACTIVE")){
            active.setSelected(true);
        }else if(userModel.getStatus().equals("DoNotDisturb")){
            busy.setSelected(true);
        }else if(userModel.getStatus().equals("AWAY")){
            away.setSelected(true);
        }
    }
    String picPath;
    Image image;
    String imagePath;
    FileInputStream stream;
    @FXML
    void OnChangingPic(MouseEvent event) {
        picPath = stageCoordinator.openFile();
        if (picPath != null) {
            try {
                image = new Image(new FileInputStream(picPath));
                imagePath = encodeImage(picPath);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        userModel.setImage(image);
                        userModel.setImagePath(imagePath);
                        pofilePic.setFill(new ImagePattern(image));
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String encodeImage(String imgPath) throws IOException {
        stream = new FileInputStream(imgPath);
        byte[] imageData = stream.readAllBytes();
        imagePath = Base64.getEncoder().encodeToString(imageData);
        stream.close();
        return  imagePath;
    }


    @FXML
    void onSave(MouseEvent event) {
        UpdateDto updateDto = new UpdateDto();
        updateDto.setId(LoginService.getId());
        updateDto.setBio(userModel.getBio());
        updateDto.setEmail(userModel.getEmail());
        updateDto.setName(userModel.getUserName());
        if(!password.getText().isEmpty()){
            updateDto.setPassword(password.getText());
        }
        if(toggleGroup.getSelectedToggle() == null){
            updateDto.setStatus(getStatusToUpdateUser(userModel.getStatus()));
        }else {
            selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            setStatusToUserModel(selectedRadioButton.getText());
            updateDto.setStatus(selectedRadioButton.getText());
        }
        updateDto.setPicture(userModel.getImagePath());
        updateDto.setPhoneNumber(userModel.getPhoneNumber());
        updateUserService.updateUser(updateDto);
        if(!password.getText().equals(confirmPassword.getText())){
            confirmPassword.setStyle(" -fx-border-color: rgb(245, 43, 43);\n" +
                    "-fx-border-width: 2;");
            confirmPasswordError.setText("password is not the same");
        } else {
            confirmPassword.setStyle(" -fx-border-radius:5px;\n" +
                    "    -fx-border-width:0.5px;\n" +
                    "    -fx-text-fill:#1e1836 ;\n" +
                    "    -fx-font-weight\t:normal;\n" +
                    "    -fx-border-color: #1e1836;");
            confirmPasswordError.setText("");
        }
    }
    void setStatusToUserModel(String status){
        if(status.equals("Active")){
            userModel.setStatus("ACTIVE");
        }else if(status.equals("Busy")){
            userModel.setStatus("DoNotDisturb");
        }else if(status.equals("Away")){
            userModel.setStatus("AWAY");
        }
    }
    String getStatusToUpdateUser(String status){
        String Status = "";
        if(status.equals("ACTIVE")){
            Status = ("Active");
        }else if(status.equals("DoNotDisturb")){
            Status = ("Busy");
        }else if(status.equals("AWAY")){
            Status = ("Away");
        }
        return Status;
    }
}
