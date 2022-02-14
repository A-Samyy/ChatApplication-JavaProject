package gov.iti.jets.presentation.controllers;


import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.service.dtos.RegisterDto;
import gov.iti.jets.service.impl.RegisterService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    ModelFactory modelFactory=ModelFactory.getInstance();
    UserModel userModel=modelFactory.getUserModel();
    RegisterService registerService=new RegisterService();
    Boolean isRegistered;
    @FXML
    private TextArea bioTextArea;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private DatePicker dateOfBirthTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label female;

    @FXML
    private RadioButton femaleRadioButton;

    @FXML
    private TextField fullNameTextField;

    @FXML
    private Label male;

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


    public RegisterController() throws RemoteException {
    }

    @FXML
    void addYourProfilePicture(MouseEvent event) {

    }

    @FXML
    void registerOnMouseClick(MouseEvent event) {
        RegisterDto registerDto=new RegisterDto();
        registerDto.setPhoneNumber(userModel.getPhoneNumber());
        registerDto.setName(userModel.getUserName());
        registerDto.setEmail(userModel.getEmail());
        registerDto.setPassword(userModel.getPassword());
        registerDto.setCountry(userModel.getCountry());

        registerDto.setDateOfBirth(null);
        registerDto.setBio(userModel.getBio());
        registerDto.setPicture(userModel.getImagePath());
        registerDto.setGender(userModel.getGender());
        System.out.println(registerDto);
        try {
            isRegistered=registerService.registUser(registerDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println(isRegistered);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        phoneNumberTextField.textProperty().bindBidirectional(userModel.phoneNumberProperty());
//        fullNameTextField.textProperty().bindBidirectional(userModel.userNameProperty());
//        emailTextField.textProperty().bindBidirectional(userModel.emailProperty());
//        passwordTextField.textProperty().bindBidirectional(userModel.passwordProperty());
//        bioTextArea.textProperty().bindBidirectional(userModel.bioProperty());
////        dateOfBirthTextField.ObjectProperty().bindBidirectional(userModel.dateProperty());
//        countryTextField.textProperty().bindBidirectional(userModel.countryProperty());

//        MARWAAAAAAAA IMAGE PICTUREEEEEE!!!!
//        maleRadioButton.textProperty().bindBidirectional(userModel.genderProperty());
//        femaleRadioButton.textProperty().bindBidirectional(userModel.genderProperty());




    }
}
