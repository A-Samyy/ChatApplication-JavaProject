package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public class FriendRequestSenderDto implements Serializable {
    private static final long serialVersionUID = 1427672600912364061L;
    @Positive
    private int userId;
    @Positive
    private int senderId;
    @NotNull
    private String senderName;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public FriendRequestSenderDto() {
        //ValidationMaker.getInstance().validate(this);
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public String toString() {
        return "FriendRequestSenderDto{" +
                "userId=" + userId +
                ", senderId=" + senderId +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
