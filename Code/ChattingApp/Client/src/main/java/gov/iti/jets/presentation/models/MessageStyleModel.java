package gov.iti.jets.presentation.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MessageStyleModel {
    private StringProperty style = new SimpleStringProperty();

    public String getStyle() {
        return style.get();
    }

    public StringProperty styleProperty() {
        return style;
    }

    public void setStyle(String style) {
        this.style.set(style);
    }


}
