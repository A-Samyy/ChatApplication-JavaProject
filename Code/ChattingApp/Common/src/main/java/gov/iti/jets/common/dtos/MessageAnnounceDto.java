package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class MessageAnnounceDto implements Serializable {

    private static final long serialVersionUID = 1420672609912367055L;
 //   @NotNull
    private String messageContent="" ;
  //  @NotEmpty
    private final String sender ="Admin";
    public MessageAnnounceDto(){
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
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
