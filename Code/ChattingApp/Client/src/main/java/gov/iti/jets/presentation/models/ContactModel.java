package gov.iti.jets.presentation.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class ContactModel {
    private final StringProperty userName = new SimpleStringProperty();
    private final  StringProperty status = new SimpleStringProperty();
    private final ObjectProperty<Image> picture = new SimpleObjectProperty<>();

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public Image getPicture() {
        return picture.get();
    }

    public ObjectProperty<Image> pictureProperty() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture.set(picture);
    }
}
