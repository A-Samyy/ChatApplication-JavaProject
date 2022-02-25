package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.common.dtos.UpdateDto;
import gov.iti.jets.common.dtos.UserHomePageDto;
import gov.iti.jets.common.interfaces.ClientMesseageInt;
import gov.iti.jets.common.interfaces.ServerMessageInt;
import gov.iti.jets.common.interfaces.UpdateUserInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.daos.MessageDao;

import gov.iti.jets.service.services.LoginService;
import gov.iti.jets.service.services.MessageService;
import gov.iti.jets.service.services.UpdateUserService;
import javafx.application.Platform;
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
    static public Map<Integer, List<MessageDao>> map = new HashMap<>();
    static public List<MessageDao> list = new ArrayList<>();
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
        messageDao = new MessageDao(messageDto);
        list.add(messageDao);
        map.put(messageDto.getUserId(),list);
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                stageCoordinator.getChatSectionController().displayMessage(messageDto.getUserId());
//
//            }
//        });
//        System.out.println(map.get(messageDto.getFriendId()).toString());
        return messageDto.getMessageContent();
    }

    @Override
    public void reciveGroupMessage(MessageGroupDto messageGroupDto , int receivedUserId) throws RemoteException {
        if(receivedUserId==LoginService.getId()){
            System.out.println(messageGroupDto.toString());
        }

    }


    public void sendMessage(MessageDto messageDto){
        try {
            serverMessageInt.getMesssage(messageDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void removeMe() throws RemoteException {
        UpdateUserInt updateUserInt= rmiRegister.updateUserService();
        updateUserInt.updateUser(mapperToUpdateDto(LoginService.userHomePageDtoToSend));
        serverMessageInt.unRegister(this,LoginService.getId());
    }

    UpdateDto mapperToUpdateDto(UserHomePageDto userHomePageDto){
        UpdateDto updateDto = new UpdateDto();
        updateDto.setPhoneNumber(userHomePageDto.getPhoneNumber());
        updateDto.setPicture(userHomePageDto.getPicture());
        updateDto.setEmail(userHomePageDto.getEmail());
        updateDto.setName(userHomePageDto.getName());
        updateDto.setBio(userHomePageDto.getBio());
        updateDto.setPassword(userHomePageDto.getPassword());
        updateDto.setId(LoginService.getId());
        updateDto.setStatus("Offline");
        return  updateDto;
    }
}
