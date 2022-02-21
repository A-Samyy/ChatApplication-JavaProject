package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.dtos.GroupDto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;

import java.net.URL;
import java.util.ResourceBundle;




public class GroupListController implements Initializable {
    @FXML
    private Label groupName;
    GroupDto groupDto = new GroupDto();
    public  void displayGroup(GroupDto groupDto) {
        this.groupDto =groupDto;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                groupName.setText(groupDto.getGroupName());
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
