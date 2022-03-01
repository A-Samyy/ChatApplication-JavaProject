package gov.iti.jets.service.daos;


import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.service.services.LoginService;

public class MessageDao {

    MessageDto messageDto ;
    MessageGroupDto messageGroupDto;

    public MessageDao(MessageDto messageDto){
        this.messageDto=messageDto;
    }
    public MessageDao(MessageGroupDto messageGroupDto){
        this.messageGroupDto=messageGroupDto;
    }

    public void setMessage(String message){
        messageDto.setMessageContent(message);
    }
    public void setUserName(String userName){
        messageDto.setUserName(userName);
    }

    public void setUserID(){
        messageDto.setUserId(LoginService.getId());
    }
    public void setMessageColor(String color){
        messageDto.setColor(color);
    }
    public String getMessageColor(){return messageDto.getColor();}
    public MessageDto getMessageDto() {
        return this.messageDto;
    }

    public String getMessageContent(){
        return messageDto.getMessageContent();
    }

    public String getMessageUserName(){
        return messageDto.getUserName();
    }

    public MessageGroupDto getMessageGroupDto() {
        return this.messageGroupDto;
    }

    public String getMessageGroupContent(){
        return messageGroupDto.getMessageContent();
    }

    public int getMessageGroupId(){
        return messageGroupDto.getGroupId();
    }
    public int getMessageGroupSenderId(){
        return messageGroupDto.getSenderId();
    }
    public String getMessageGroupSenderName(){
        return messageGroupDto.getSenderName();
    }
    public String getMessageGroupStyle(){
        return messageGroupDto.getMessageStyle();
    }
}