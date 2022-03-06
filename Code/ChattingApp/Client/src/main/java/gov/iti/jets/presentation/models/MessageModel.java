package gov.iti.jets.presentation.models;

import gov.iti.jets.common.dtos.MessageDto;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MessageModel {
    private final StringProperty userName = new SimpleStringProperty();
    private final StringProperty content = new SimpleStringProperty();
    private final IntegerProperty friendId = new SimpleIntegerProperty();

    public int getFriendId() {
        return friendId.get();
    }

    public IntegerProperty friendIdProperty() {
        return friendId;
    }

    public void setFriendId(MessageDto messageDto) {
        this.friendId.set(messageDto.getFriendId());
    }

    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(MessageDto messageDto) {
        this.userName.set(messageDto.getUserName());
    }



    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(MessageDto messageDto) {
        this.content.set(messageDto.getMessageContent());
    }



}
