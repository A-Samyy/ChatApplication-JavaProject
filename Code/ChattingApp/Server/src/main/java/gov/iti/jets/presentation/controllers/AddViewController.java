package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presistance.dtos.Status;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.service.services.AddUserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddViewController implements Initializable {
    AddUserService addUserService = new AddUserService();
    @FXML
    private TextField PhoneNumberTextField;

    @FXML
    private TextField UserNameTextfield;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void onAddUserMouseClicked(MouseEvent event) {
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber(PhoneNumberTextField.getText());
        userDto.setName(UserNameTextfield.getText());
        userDto.setEmail(emailTextField.getText());
        userDto.setPassword(passwordTextField.getText());
        userDto.setStatus(Status.ACTIVE);
        addUserService.addUser(userDto);
    }

    @FXML
    void z(MouseDragEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
