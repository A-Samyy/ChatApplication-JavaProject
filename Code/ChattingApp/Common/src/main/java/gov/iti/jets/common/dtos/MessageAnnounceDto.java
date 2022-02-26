package gov.iti.jets.common.dtos;

import java.io.Serializable;

public class MessageAnnounceDto implements Serializable {

    private static final long serialVersionUID = 1420672609912367055L;
    private String messageContent="" ;
    private final String sender ="Admin";

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        System.out.println("messageContent in get Message common"+messageContent);
    }

    public String getMessageSender(){
        return this.sender;
    }



    @Override
    public String toString() {
        return "MessageAnnounceDto{" +
                "MessageContent='" + messageContent + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
