package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.interfaces.ServerMessageAnnouncetInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.impl.ClientAnnounceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminMessageContainerController implements Initializable {

    RMIRegister rmiRegister = RMIRegister.getInstance();
    ServerMessageAnnouncetInt serverMessageAnnouncetInt = rmiRegister.serverMessageAnnouncetInt();
    @FXML
    private ListView<String> adminMessageListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void displayMessage(List<String > messagesFromAdmin){
        int count = 0;
        for (String message:messagesFromAdmin) {
            System.out.println((message+" "+count));
            adminMessageListView.getItems().add(message);
            count++;
        }
    }
    public void shutdown() {
        System.out.println("Stop");
        ClientAnnounceImpl.messagesFromAdmin.clear();
        adminMessageListView.getItems().clear();
        try {
            serverMessageAnnouncetInt.removeList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
