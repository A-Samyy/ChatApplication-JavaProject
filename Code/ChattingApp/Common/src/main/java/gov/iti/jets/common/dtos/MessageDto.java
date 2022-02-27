package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public class MessageDto implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
    @NotNull(message = "message can't be empty")
    private String messageContent;
    @NotNull(message = "user name must be defined")
    private String userName;
    @Negative(message = "Id shouldn't be negative number")
    private int userId;
    @Positive

    private int friendId;

    public MessageDto(){
        ValidationMaker.getInstance().validate(this);
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
