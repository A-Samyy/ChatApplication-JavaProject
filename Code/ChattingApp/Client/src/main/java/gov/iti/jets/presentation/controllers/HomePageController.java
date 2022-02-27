package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.interfaces.ClientAnnounceMessageInt;
import gov.iti.jets.common.interfaces.ClientGroupChatMessageInt;
import gov.iti.jets.common.interfaces.ServerGroupChatMessageInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.presentation.util.StageCoordinator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    RMIRegister rmiRegister = RMIRegister.getInstance();
    ServerGroupChatMessageInt serverGroupChatMessage = rmiRegister.groupChatMessageService();
    ClientGroupChatMessageInt clientGroupChatMessageInt ;
    ClientAnnounceMessageInt clientAnnounceMessageInt;

    @FXML
    public GridPane gridPane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        stageCoordinator.setHomepage(gridPane);
        gridPane.add(stageCoordinator.loadSidebar(), 0, 0);
        gridPane.add(stageCoordinator.loadDefault(), 1, 0);



    }
}
