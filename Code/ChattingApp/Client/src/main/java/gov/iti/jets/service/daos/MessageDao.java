package gov.iti.jets.service.daos;


import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.service.services.LoginService;

public class MessageDao {

    MessageDto messageDto ;

    public MessageDao(MessageDto messageDto){
        this.messageDto=messageDto;
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

    public MessageDto getMessageDto() {
        return this.messageDto;
    }

    public String getMessageContent(){
        return messageDto.getMessageContent();
    }

    public String getMessageUserName(){
        return messageDto.getUserName();
    }



}