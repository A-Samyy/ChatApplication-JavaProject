package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class MessageAnnounceDto implements Serializable {

    private static final long serialVersionUID = 1420672609912367055L;
    @NotEmpty
    @NotNull
    private String MessageContent ;
    @NotEmpty
    private final String sender ="Admin";
    public MessageAnnounceDto(){
        ValidationMaker.getInstance().validate(this);
    }

    public String getMessageContent() {
        return MessageContent;
    }

    public void setMessageContent(String messageContent) {
        MessageContent = messageContent;
    }

    public String getMessageSender(){
        return this.sender;
    }



    @Override
    public String toString() {
        return "MessageAnnounceDto{" +
                "MessageContent='" + MessageContent + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
