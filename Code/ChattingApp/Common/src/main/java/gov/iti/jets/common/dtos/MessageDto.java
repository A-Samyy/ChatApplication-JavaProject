package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public class MessageDto implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
    @NotNull
    private String messageContent;
    @NotNull
    private String userName;
    @Negative
    private int userId;
    @Positive

    private int friendId;

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public MessageDto(){
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }



    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "messageContent='" + messageContent + '\'' +
                ", userName=" + userName + '\'' +
                ", userId=" + userId ;
    }
}
