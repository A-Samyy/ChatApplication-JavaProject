package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.dtos.GroupDto;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;

import java.net.URL;
import java.util.ResourceBundle;




public class GroupListController implements Initializable {
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
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

    @FXML
    void OnOpenGroupChat(MouseEvent event) {
        GridPane home = stageCoordinator.getHomepage();
        home.getChildren().removeIf(node -> GridPane.getColumnIndex(node) == 1);
        System.out.println(this.groupDto.getGroupName());
        home.add( stageCoordinator.loadChatSectionForGroup(this.groupDto), 1, 0);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
