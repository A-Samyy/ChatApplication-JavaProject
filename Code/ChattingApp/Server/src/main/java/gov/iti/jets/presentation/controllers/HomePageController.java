package gov.iti.jets.presentation.controllers;

import gov.iti.jets.common.dtos.MessageAnnounceDto;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.presistance.dtos.Status;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.service.Impl.ServerMessageAnnounceImpl;
import gov.iti.jets.service.services.AddUserService;
import gov.iti.jets.service.services.AnalysisService;
import gov.iti.jets.service.services.ServerControlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.controlsfx.control.ToggleSwitch;
import org.kordamp.ikonli.javafx.FontIcon;

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
    AddUserService addUserService = new AddUserService();

    @FXML
    private FontIcon addUser;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private GridPane chartsBigGrid;

    @FXML
    private GridPane chartsGrid;
    @FXML
    private Label onOffLabel;
    @FXML
    private AnchorPane content;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab userAdd;

    @FXML
    private PieChart genderChart;

    @FXML
    private GridPane numberOfUsers;

    @FXML
    private Label usersNumber;

    @FXML
    private PieChart statusChart;

    @FXML
    private ListView<HBox> listView;
    @FXML
    private TextField messageTextField;

    @FXML
    private ToggleSwitch toggleButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    public HomePageController() throws RemoteException {
    }



    @FXML
    void onGetActiveUsers(MouseEvent event) {
        statusChart();

    }

    @FXML
    void onGetGender(MouseEvent event) {
        genderChart();

    }

    @FXML
    void onGetNumberOfUsers(MouseEvent event) {
        getNumberOfUsers();
    }


    @FXML
    void sendAction(MouseEvent event) {
        messageAnnounceDto.setMessageContent(messageTextField.getText());

        messageObservableList.add(stageCoordinator.loadMessage(messageAnnounceDto));
        listView.setItems(messageObservableList);

        serverMessageAnnounce.getMessageAnnounceDto(this.messageAnnounceDto);

        messageTextField.setText("");
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        toggleButton.selectedProperty().addListener((observable,oldValue,newValue)->{

            if(newValue)
            {
                serverControlService.openConnection();

                onOffLabel.setText("ON");
            }else{
                serverControlService.closeConnection();

                onOffLabel.setText("OFF");
            }

        });

        genderChart();
        statusChart();
        getNumberOfUsers();


        listView.setCellFactory(messageListView -> new MessageServerListViewCell());
        messageObservableList = FXCollections.observableArrayList();



    }

    private void getNumberOfUsers() {
        usersNumber.setText(String.valueOf(analysisService.getNumberOfAllUsers()));
    }


    private void genderChart() {

        ObservableList<PieChart.Data> genderTypeList = FXCollections.observableArrayList(
                new PieChart.Data("Number of Females", analysisService.getNumberOfFemaleUsers()),
                new PieChart.Data("Number of Males", analysisService.getNumberOfMaleUsers())
        );
        genderChart.setData(genderTypeList);

    }
    private void statusChart(){
        ObservableList<PieChart.Data> statusList= FXCollections.observableArrayList(
                new PieChart.Data("Number of onlineUsers",onlineUsers),
                new PieChart.Data("Number of offlineUsers",analysisService.getNumberOfAllUsers()-onlineUsers)
        );
        statusChart.setData(statusList);

    }

    @FXML
    void onAddUserMouseClicked(MouseEvent event) {
        UserDto userDto = new UserDto();

            userDto.setPhoneNumber(phoneNumberTextField.getText());
            userDto.setName(userNameTextField.getText());
            userDto.setEmail(emailTextField.getText());
            userDto.setPassword(passwordTextField.getText());
            userDto.setGender("Female");
            userDto.setStatus(Status.OFFLINE);
            userDto.setPicture("src/main/resources/clientPictures/user.png");
            userDto.setStatus(Status.ACTIVE);
            addUserService.addUser(userDto);
            phoneNumberTextField.setText("");
            userNameTextField.setText("");
            emailTextField.setText("");
            passwordTextField.setText("");


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
