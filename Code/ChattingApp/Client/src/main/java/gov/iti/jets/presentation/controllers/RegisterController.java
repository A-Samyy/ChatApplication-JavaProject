package gov.iti.jets.presentation.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import gov.iti.jets.common.dtos.RegisterDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.RegisterService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.CompoundValidationDecoration;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegisterController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    RegisterService registerService = new RegisterService();
    Boolean isRegistered;
    ValidationSupport validationSupport=new ValidationSupport();

    @FXML
    private FontIcon backArrow;

    @FXML
    private Label backButton;

    @FXML
    private Label countryError;

    @FXML
    private Label invalidEmail;

    @FXML
    private Label invalidName;

    @FXML
    private Label invalidPassword;

    @FXML
    private Label invalidPhone;

    @FXML
    private TextArea bioTextArea;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private ChoiceBox<String> countryChoiceBox;

    @FXML
    private DatePicker dateOfBirthTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private TextField fullNameTextField;

    @FXML
    private Label confirmPasswordError;
    @FXML
    private Label genderError;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Circle profilePicture;

    @FXML
    private Button regbutton;

    LocalDate date;
    String picPath;
    Image image;
    String imagePath;
    FileInputStream stream;
    ToggleGroup toggleGroup;
    RadioButton selectedRadioButton;
    RegisterDto registerDto;
    Date dateToDto;

    public RegisterController() throws RemoteException {
    }


    @FXML
    void onBackButtonAction(MouseEvent event) {
        stageCoordinator.switchToLoginScreen();
    }

    @FXML
    void addYourProfilePicture(MouseEvent event) {
        picPath = stageCoordinator.openFile();
        if (picPath != null) {
            try {
                image = new Image(new FileInputStream(picPath));
                imagePath = encodeImage(picPath);
                userModel.setImagePath(imagePath);
                profilePicture.setFill(new ImagePattern(image));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            try {
                picPath="src/main/resources/images/profileimage.png";
                image = new Image(new FileInputStream(picPath));
                imagePath = encodeImage(picPath);
                userModel.setImagePath(imagePath);
                profilePicture.setFill(new ImagePattern(image));
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
    void changeOnAction(ActionEvent event) {
        date = dateOfBirthTextField.getValue();
        userModel.setDate(date.toString());
    }

    @FXML
    void Clicked(ActionEvent event) {
        toggleGroup = new ToggleGroup();
        maleRadioButton.setToggleGroup(toggleGroup);
        femaleRadioButton.setToggleGroup(toggleGroup);
        selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        userModel.setGender(selectedRadioButton.getText());
    }


    @FXML
    void registerOnMouseClick(MouseEvent event) {
        try {
            if(userModel.getDate() == null){
                dateToDto = null;
            }else {
                dateToDto = new SimpleDateFormat("yyyy-MM-dd").parse(userModel.getDate());
            }
        } catch (ParseException e) {
            e.getMessage();
        }
        if(userModel.getImagePath()==null){
            try {
                picPath="src/main/resources/images/profileimage.png";
                image = new Image(new FileInputStream(picPath));
                imagePath = encodeImage(picPath);
                userModel.setImagePath(imagePath);
                profilePicture.setFill(new ImagePattern(image));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        registerDto = new RegisterDto();
        registerDto.setPhoneNumber(userModel.getPhoneNumber());
        registerDto.setName(userModel.getUserName());
        registerDto.setEmail(userModel.getEmail());
        registerDto.setPassword(userModel.getPassword());
        registerDto.setCountry(userModel.getCountry());
        registerDto.setBio(userModel.getBio());
        registerDto.setPicture(userModel.getImagePath());
        registerDto.setGender(userModel.getGender());
        registerDto.setDateOfBirth(dateToDto);
        try {
            isRegistered = registerService.registUser(registerDto);

            if(isRegistered)
                stageCoordinator.switchToLoginScreen();
        } catch (RemoteException e) {
            e.getMessage();
        }

        validationSupport.setValidationDecorator(new StyleClassValidationDecoration());

        validationSupport.setValidationDecorator(new CompoundValidationDecoration(
                new GraphicValidationDecoration(),
                new StyleClassValidationDecoration()));


        validationSupport.registerValidator(fullNameTextField, Validator.createRegexValidator(
                "Enter a valid Name that contains only characters",
                Pattern.compile("^\\p{Alpha}+$"), Severity.ERROR));

        validationSupport.registerValidator(phoneNumberTextField, Validator.createRegexValidator(
                "Enter only numbers",
                Pattern.compile("^[0-9]+$"), Severity.ERROR));



        validationSupport.registerValidator(passwordTextField, Validator.createRegexValidator(
                "This password is weak!",
                Pattern.compile("^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$"), Severity.ERROR));
        if(!passwordTextField.getText().equals(confirmPasswordTextField.getText())){
            confirmPasswordTextField.setStyle(" -fx-border-color: rgb(245, 43, 43);\n" +
                    "-fx-border-width: 2;");
            confirmPasswordError.setText("password is not the same");
        } else {
        confirmPasswordTextField.setStyle(" -fx-border-radius:5px;\n" +
                "    -fx-border-width:0.5px;\n" +
                "    -fx-text-fill:#1e1836 ;\n" +
                "    -fx-font-weight\t:normal;\n" +
                "    -fx-border-color: #1e1836;");
            confirmPasswordError.setText("");
        }


        validationSupport.registerValidator(emailTextField, Validator.createRegexValidator(
                "Enter a valid email format",
                Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b"), Severity.ERROR));

        validationSupport.registerValidator(countryChoiceBox,Validator.createEmptyValidator("You have to choose a country"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validationSupport.setErrorDecorationEnabled(true);
        addCountryChoiceBox();
        passwordTextField.setText("");
        confirmPasswordTextField.setText("");
    }

    private void addCountryChoiceBox(){
        ObservableList<String> cities = FXCollections.observableArrayList();
        String[] locales1 = Locale.getISOCountries();
        for (String countrylist : locales1) {
            Locale obj = new Locale("", countrylist);
            String[] city = { obj.getDisplayCountry() };
            for (int x = 0; x < city.length; x++) {
                cities.add(obj.getDisplayCountry());
            }
        }
        countryChoiceBox.setItems(cities);
    }
}
