package gov.iti.jets.presentation.controllers;

import gov.iti.jets.presentation.models.MessageStyleModel;
import gov.iti.jets.presentation.util.ModelFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class StylingController implements Initializable {
    ModelFactory modelFactory = ModelFactory.getInstance();
    MessageStyleModel messageStyleModel = modelFactory.getMessageStyleModel();

    @FXML
    private Label sampleField;

    @FXML
    private ColorPicker fontColor;

    @FXML
    private Label fontColorField;

    @FXML
    private ComboBox<String> fontFamily;

    @FXML
    private Label fontFamilyField;

    @FXML
    private ComboBox<Integer> fontSize;

    @FXML
    private Label fontSizeField;

    @FXML
    private ComboBox<String> fontStyle;

    @FXML
    private Label fontStyleField;


    String color = "000000";
    String family = "Arial";
    Integer size = 20;
    String style = "Normal";
    String familyCss = "-fx-font-family: ";
    String styleCss = "-fx-font-weight: ";
    String colorCss = "-fx-text-fill: #";
    String sizeCss = "-fx-font-size: ";
    String COLOR = colorCss+color;
    String SIZE = sizeCss+size;
    String FAMILY = familyCss+family;
    String STYLE = styleCss+style;

    String finalStyle = COLOR+";"+FAMILY+";"+STYLE+";"+SIZE;

    ObservableList<Integer> sizeFont= FXCollections.observableArrayList();

    ObservableList<String> styleFont= FXCollections.observableArrayList();
    ObservableList<String> familyFont= FXCollections.observableArrayList();

    public String getFinalStyle(){
        return finalStyle;
    }

    @FXML
    void OnChangingFontColor(ActionEvent event) {
        color = fontColor.getValue().toString().substring(2);
        fontColorField.setText(color);
        COLOR = colorCss+color;
        messageStyleModel.setStyle(COLOR+";"+FAMILY+";"+STYLE+";"+SIZE);
        sampleField.setStyle(COLOR+";"+FAMILY+";"+STYLE+";"+SIZE);
        fontColorField.setText(fontColor.getValue().toString());
    }

    @FXML
    void OnChangingFontFamily(ActionEvent event) {
        family = fontFamily.getValue();
        fontFamilyField.setText(family);
        FAMILY = familyCss+family;
        messageStyleModel.setStyle(COLOR+";"+FAMILY+";"+STYLE+";"+SIZE);
        sampleField.setStyle(COLOR+";"+FAMILY+";"+STYLE+";"+SIZE);
       fontFamilyField.setText(fontFamily.getValue());
    }

    @FXML
    void OnChangingFontSize(ActionEvent event) {
        size = fontSize.getValue();
        fontSizeField.setText(size.toString());
        SIZE = sizeCss+size;
        messageStyleModel.setStyle(COLOR+";"+FAMILY+";"+STYLE+";"+SIZE);
        sampleField.setStyle(COLOR+";"+FAMILY+";"+STYLE+";"+SIZE);
        fontSizeField.setText(fontSize.getValue().toString());
    }

    @FXML
    void OnChangingFontStyle(ActionEvent event) {
        style = fontStyle.getValue();
        fontStyleField.setText(style);
        STYLE = styleCss+style;
        messageStyleModel.setStyle(COLOR+";"+FAMILY+";"+STYLE+";"+SIZE);
        sampleField.setStyle(COLOR+";"+FAMILY+";"+STYLE+";"+SIZE);
        fontStyleField.setText(fontStyle.getValue());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sizeFont.addAll(8,10,12,14,16,18,20,22,24,26,28,30,32);
        styleFont.addAll("Normal","Bold");
        familyFont.addAll(Font.getFamilies());
        fontSize.setItems(sizeFont);
        fontStyle.setItems(styleFont);
        fontFamily.setItems(familyFont);





    }
}
