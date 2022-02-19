package gov.iti.jets.service.services;

import gov.iti.jets.service.ClientMesseageInt;
import gov.iti.jets.service.dtos.MessageDto;
import gov.iti.jets.service.impl.ClientMessageImpl;

import java.rmi.RemoteException;

public class MessageService {
    ClientMessageImpl clientMessage ;
    MessageDto messageDto = new MessageDto();
    public MessageService(){
    }
    public void sendMessageDto(MessageDto messageDto){
        this.messageDto = messageDto;
        try {
            clientMessage = new ClientMessageImpl();
            clientMessage.sendMessage(this.messageDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
//    public void recieveMessageDto(MessageDto messageDto){
//
//    }


}
