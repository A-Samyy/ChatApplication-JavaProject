package gov.iti.jets.presentation.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileCounterModel {
    private DoubleProperty number = new SimpleDoubleProperty();


    public double getNumber() {
        return number.get();
    }

    public DoubleProperty numberProperty() {
        return number;
    }

    public void setNumber(double number) {
        this.number.set(number);
    }


}
