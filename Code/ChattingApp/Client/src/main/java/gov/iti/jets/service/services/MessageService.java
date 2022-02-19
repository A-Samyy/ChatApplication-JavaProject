package gov.iti.jets.service.services;

import gov.iti.jets.service.ClientMesseageInt;
import gov.iti.jets.service.dtos.MessageDto;
import gov.iti.jets.service.impl.ClientMessageImpl;

import java.rmi.RemoteException;

public class MessageService {
    static MessageService messageService = new MessageService();
    ClientMessageImpl clientMessage ;
    MessageDto messageDto = new MessageDto();
    private MessageService(){
        try {
            clientMessage = new ClientMessageImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public static MessageService getInstance(){
        return messageService;
    }
    public void sendMessageDto(MessageDto messageDto){
        this.messageDto = messageDto;
        System.out.println("messageService");
        clientMessage.sendMessage(this.messageDto);
    }
//    public void recieveMessageDto(MessageDto messageDto){
//
//    }


}
