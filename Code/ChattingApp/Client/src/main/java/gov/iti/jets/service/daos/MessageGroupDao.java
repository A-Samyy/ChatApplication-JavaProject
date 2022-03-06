package gov.iti.jets.service.daos;

import gov.iti.jets.common.dtos.MessageGroupDto;

public class MessageGroupDao {
    MessageGroupDto messageGroupDto ;

    public void setMessageContent(String message){
        messageGroupDto.setMessageContent(message);
    }

    public String getMessageContent(){
        return messageGroupDto.getMessageContent();
    }

    public void setGroupID(int groupID){
        messageGroupDto.setGroupId(groupID);
    }

    public int getGroupId(){
        return messageGroupDto.getGroupId();
    }

    public void setSenderId(int senderId){
        messageGroupDto.setSenderId(senderId);
    }

    public int getSenderId(){
        return messageGroupDto.getGroupId();
    }
}
