package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

public class MessageGroupDto implements Serializable {
    private static final long serialVersionUID = 1420672609912364188L;
    @NotEmpty
    @NotNull
    private String messageContent ;
    @Positive
    private int senderId ;
    @Positive
    private int groupId ;
    private String senderName;
    public MessageGroupDto(){
        ValidationMaker.getInstance().validate(this);
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public String getMessageContent() {
        return messageContent;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "MessageGroupDto{" +
                "messageContent='" + messageContent + '\'' +
                ", senderId=" + senderId +
                ", groupId=" + groupId +
                '}';
    }
}
