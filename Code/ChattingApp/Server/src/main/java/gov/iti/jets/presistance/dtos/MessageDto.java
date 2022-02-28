package gov.iti.jets.presistance.dtos;
import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.InputStream;

public class MessageDto {
    @Positive(message = "id must be positive number")
    int messageId;
    @NotNull
    @NotEmpty(message = "content name can't be empty")
    String content;
    @NotNull
    InputStream fileForUser;

    public MessageDto(){
        //ValidationMaker.getInstance().validate(this);
    }
    public void setMessageId(int messageId){
        this.messageId = messageId;
    }
    public int getMessageId(){
        return this.messageId;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public InputStream getFileForUser() {
        return fileForUser;
    }
    public void setFileForUser(InputStream fileForUser) {
        this.fileForUser = fileForUser;
    }


    
}
