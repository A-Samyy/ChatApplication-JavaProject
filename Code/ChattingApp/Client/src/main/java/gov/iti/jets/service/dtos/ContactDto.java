package gov.iti.jets.service.dtos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class ContactDto implements Serializable {
    private static final long serialVersionUID = 1427672609912364060L;

    private String friendName;
    private String Status;
    private String picture;

    public ContactDto() {
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    @Override
    public String toString() {
        return "ContactDto{" +
                "friendName='" + friendName + '\'' +
                ", Status='" + Status + '\'' +
                ", picture=" + picture +
                '}';
    }
}
