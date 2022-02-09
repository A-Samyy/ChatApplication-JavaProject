package gov.iti.jets.presistance.Dtos;
import java.io.InputStream;

public class MessageDto {
    int messageId;
    String content;
    InputStream fileForUser;

    public MessageDto(){}
    // public MessageDto(int messageId, String content, File fileForUser){
    //     this.messageId = messageId;
    //     this.content = content;
    //     this.fileForUser = fileForUser;
    // }
    
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
