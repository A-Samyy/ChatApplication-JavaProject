package gov.iti.jets.service.impl;

import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.ClientMesseageInt;
import gov.iti.jets.service.ServerMessageInt;
import gov.iti.jets.service.dtos.MessageDto;
import gov.iti.jets.service.services.LoginService;
import org.jetbrains.annotations.NotNull;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientMessageImpl extends UnicastRemoteObject implements ClientMesseageInt {

    RMIRegister rmiRegister=RMIRegister.getInstance();
    public ServerMessageInt serverMessageInt;

    public ClientMessageImpl() throws RemoteException {
        super();
        serverMessageInt=rmiRegister.messageService();
        System.out.println("regester client ?  "+serverMessageInt.register(this,LoginService.getId()));
    }

    @Override
    public String reciveMessage(@NotNull MessageDto messageDto) throws RemoteException {
        /*
        Stagecor.loadMessage(messageDto)
         */


        System.out.println(messageDto + "ay haga");
        return messageDto.getMessageContent();
    }


    public void sendMessage(MessageDto messageDto){
        try {
            serverMessageInt.getMesssage(messageDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
