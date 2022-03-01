package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.FileCounterModel;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.service.impl.ClientFileRequestImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ProgressBarController implements Initializable {

    private ModelFactory modelFactory=ModelFactory.getInstance();
    private FileCounterModel fileCounterModel =modelFactory.getFileCounterModel();

    @FXML
    private FontIcon doneIcon;

    @FXML
    private Label fileReceivedLabel;

    @FXML
    private Button doneButton;

    @FXML
    private ProgressIndicator progressIndicator;


    @FXML
    void OnAddNewGroupClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fileCounterModel.setNumber(0.0);
        progressIndicator.progressProperty().bindBidirectional(fileCounterModel.numberProperty());
        new Thread(new Runnable() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {


                        System.out.println("progress");
                        System.out.println("progress="+progressIndicator.getProgress());
                        if (fileCounterModel.getNumber()==1.0){
                            doneIcon.setOpacity(1);
                            fileReceivedLabel.setOpacity(1);
                            doneButton.setDisable(false);
                        }


                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



        }).start();


        }
}
