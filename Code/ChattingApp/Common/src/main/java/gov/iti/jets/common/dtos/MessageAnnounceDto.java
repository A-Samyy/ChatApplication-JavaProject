package gov.iti.jets.common.dtos;

import java.io.Serializable;

public class MessageAnnounceDto implements Serializable {

    private static final long serialVersionUID = 1420672609912367055L;
    private String MessageContent ;
    private final String sender ="Admin";

    public String getMessageContent() {
        return MessageContent;
    }

    public void setMessageContent(String messageContent) {
        MessageContent = messageContent;
    }

    @Override
    public String toString() {
        return "MessageAnnounceDto{" +
                "MessageContent='" + MessageContent + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
