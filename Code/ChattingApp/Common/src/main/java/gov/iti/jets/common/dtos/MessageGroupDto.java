package gov.iti.jets.common.dtos;

import java.io.Serializable;

public class MessageGroupDto implements Serializable {

    private static final long serialVersionUID = 1420672609912364188L;
    private String messageContent ;
    private int senderId ;
    private int groupId ;

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
}
