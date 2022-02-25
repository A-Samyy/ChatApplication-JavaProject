package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.AnalysisService;
import gov.iti.jets.service.services.ServerControlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private final StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    ServerControlService serverControlService = ServerControlService.getInstance();
    AnalysisService analysisService = AnalysisService.getInstance();
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
    void onCloseConnectionMouseClick(MouseEvent event) {
        serverControlService.closeConnection();
    }
    @FXML
    void onOpenConnectionMouseClick(MouseEvent event) {
        serverControlService.openConnection();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<PieChart.Data> genderTypeList= FXCollections.observableArrayList(
            new PieChart.Data("Number of Females",analysisService.getNumberOfFemaleUsers()),
            new PieChart.Data("Number of Males",analysisService.getNumberOfMaleUsers())
        );
        genderChart.setData(genderTypeList);
        genderChart.setTitle("Users_Gender");
//        ObservableList<PieChart.Data> sattusList= FXCollections.observableArrayList(
//                new PieChart.Data("Number of Females",analysisService.getNumberOfFemaleUsers()),
//                new PieChart.Data("Number of Males",analysisService.getNumberOfMaleUsers())
//        );
//        statusChart.setData(genderTypeList);





        // ( (AnchorPane)userAdd.getContent()).getChildren().add(stageCoordinator.loadAddUser());
        content.getChildren().add(stageCoordinator.loadAddUser());

    }


}
