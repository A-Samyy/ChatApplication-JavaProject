package gov.iti.jets.service.dtos;

import java.io.Serializable;

public class MessageDto implements Serializable {
    private static final long serialVersionUID = 1420672609912364060L;
    private String messageContent;
    private String userName;
    private int userId;
    private int friendId;



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
