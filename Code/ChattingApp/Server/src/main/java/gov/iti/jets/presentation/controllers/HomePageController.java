package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.Impl.ServerMessageAnnounceImpl;
import gov.iti.jets.service.services.AnalysisService;
import gov.iti.jets.service.services.ServerControlService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    ServerControlService serverControlService = ServerControlService.getInstance();
    AnalysisService analysisService = AnalysisService.getInstance();
    MessageAnnounceDto messageAnnounceDto=new MessageAnnounceDto();
    ServerMessageAnnounceImpl serverMessageAnnounce= new ServerMessageAnnounceImpl();
    private ObservableList<HBox> messageObservableList;

    int onlineUsers=serverMessageAnnounce.onlinUsers();
    @FXML
    private AnchorPane content;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab userAdd;

    @FXML
    private PieChart genderChart;

    @FXML
    private PieChart numberOfUsersChart;

    @FXML
    private PieChart statusChart;

    @FXML
    private ListView<HBox> listView;
    @FXML
    private TextField messageTextField;

    public HomePageController() throws RemoteException {
    }

    @FXML
    void onCloseConnectionMouseClick(MouseEvent event) {
        serverControlService.closeConnection();
    }
    @FXML
    void onOpenConnectionMouseClick(MouseEvent event) {
        serverControlService.openConnection();
    }

    @FXML
    void sendAction(MouseEvent event) throws RemoteException {
        messageAnnounceDto.setMessageContent(messageTextField.getText());

        messageObservableList.add(stageCoordinator.loadMessage(messageAnnounceDto));
        listView.setItems(messageObservableList);
        serverMessageAnnounce.getMessageAnnounceDto(this.messageAnnounceDto);
        System.out.println("messageAnnounce:"+this.messageAnnounceDto);
        messageTextField.setText("");

    }


    public MessageAnnounceDto getMessageAnnounceDto(){
        return this.messageAnnounceDto;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listView.setCellFactory(messageListView -> new MessageServerListViewCell());
        messageObservableList = FXCollections.observableArrayList();

        ObservableList<PieChart.Data> genderTypeList= FXCollections.observableArrayList(
                new PieChart.Data("Number of Females",analysisService.getNumberOfFemaleUsers()),
                new PieChart.Data("Number of Males",analysisService.getNumberOfMaleUsers())
        );
        genderChart.setData(genderTypeList);
        genderChart.setTitle("Users_Gender");

        ObservableList<PieChart.Data> statusList= FXCollections.observableArrayList(
                new PieChart.Data("Number of onlineUsers",onlineUsers),
                new PieChart.Data("Number of offlineUsers",analysisService.getNumberOfAllUsers()-onlineUsers)
        );
        statusChart.setData(statusList);


        statusChart.setTitle("Online_users");

//        new Thread(() -> {
//            while (true) {
//
//                    Platform.runLater(() -> {
//
//                    });
//            }
//        }).start();


        // ( (AnchorPane)userAdd.getContent()).getChildren().add(stageCoordinator.loadAddUser());
        content.getChildren().add(stageCoordinator.loadAddUser());

    }
    private class MessageServerListViewCell extends ListCell<HBox> {

        private Pane messageCellContainer=new Pane();
        public MessageServerListViewCell() {
        }

        @Override
        protected void updateItem(HBox item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) { // <== test for null item and empty parameter
                messageCellContainer.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                item.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                messageCellContainer.getChildren().add(item);
                setGraphic(messageCellContainer);
            } else {
                setGraphic(null);
            }
        }
    }
}
