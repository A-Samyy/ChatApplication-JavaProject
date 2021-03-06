package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import javafx.scene.image.Image;

import java.io.Serializable;

public class ContactDto implements Serializable {
    private static final long serialVersionUID = 1427672609912364060L;

    @Positive
    private int id;
    @NotEmpty
    private String friendName;
    private String Status;
    private String picture;

    public ContactDto() {
       }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", friendName='" + friendName + '\'' +
                ", Status='" + Status + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
