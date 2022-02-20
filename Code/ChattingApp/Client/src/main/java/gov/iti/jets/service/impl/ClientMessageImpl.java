package gov.iti.jets.service.impl;

import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.ClientMesseageInt;
import gov.iti.jets.service.ServerMessageInt;
import gov.iti.jets.service.daos.MessageDao;
import gov.iti.jets.service.dtos.MessageDto;
import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.MessageService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientMessageImpl extends UnicastRemoteObject implements ClientMesseageInt {

    RMIRegister rmiRegister=RMIRegister.getInstance();
    public ServerMessageInt serverMessageInt;
    static public Map<Integer, ObservableList<HBox>> map = new HashMap<>();
    static public ObservableList<HBox> list = FXCollections.observableArrayList();
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    MessageService messageService = MessageService.getInstance();

    MessageDao messageDao ;
    public ClientMessageImpl() throws RemoteException {
        super();
        serverMessageInt=rmiRegister.messageService();
        System.out.println("regester client ?  "+serverMessageInt.register(this,LoginService.getId()));
    }

    @Override
    public String reciveMessage(MessageDto messageDto) throws RemoteException {
        /*
        Stagecor.loadMessage(messageDto)
         */
//        messageService.recieveMessageDto(messageDto);

//        stageCoordinator.getHomepage().add( stageCoordinator.loadChatSection(null,null,null,0), 1, 0);

        messageDao = new MessageDao(messageDto);

        list.add(stageCoordinator.loadMessage(messageDao));
//        if(add){
            map.put(messageDto.getUserId(),list);
//        }


        System.out.println(list.toString());
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
