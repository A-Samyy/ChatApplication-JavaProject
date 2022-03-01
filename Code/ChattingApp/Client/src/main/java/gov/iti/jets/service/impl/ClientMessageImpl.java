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
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientMessageImpl extends UnicastRemoteObject implements ClientMesseageInt {

    transient RMIRegister rmiRegister = RMIRegister.getInstance();
    transient public ServerMessageInt serverMessageInt;
    transient static public Map<Integer, List<MessageDao>> map = new HashMap<>();
    transient static public List<MessageDao> list = new ArrayList<>();
    transient StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    transient MessageService messageService = MessageService.getInstance();

    public static ClientMessageImpl getClientMessage() {
        return clientMessage;
    }

    static ClientMessageImpl clientMessage;
    MessageDao messageDao;

    public ClientMessageImpl() throws RemoteException {
        super();
    }
    public void registerMessageInt() {
//        try {
//            serverMessageInt = rmiRegister.messageService();
//            serverMessageInt.register(this, LoginService.getId());
            clientMessage= this;
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public String reciveMessage(MessageDto messageDto) throws RemoteException {
        messageDao = new MessageDao(messageDto);
        list.add(messageDao);
        map.put(messageDto.getUserId(), list);
        if (stageCoordinator.getChatSectionController().get(messageDto.getUserId()) != null) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stageCoordinator.getChatSectionController().get(messageDto.getUserId()).displayMessage(messageDto.getUserId());

                }
            });
        }
        return messageDto.getMessageContent();
    }

    @Override
    public void reciveGroupMessage(MessageGroupDto messageGroupDto, int receivedUserId) throws RemoteException {
    }


    public void sendMessage(MessageDto messageDto) {
        try {
            serverMessageInt.getMesssage(messageDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void removeMe() {
        System.out.println(" remove me 1");
        UpdateUserInt updateUserInt = rmiRegister.updateUserService();
        System.out.println(" remove me 2");
        try {
            updateUserInt.updateUser(mapperToUpdateDto(LoginService.userHomePageDtoToSend));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    UpdateDto mapperToUpdateDto(UserHomePageDto userHomePageDto) {
        UpdateDto updateDto = new UpdateDto();
        updateDto.setPhoneNumber(userHomePageDto.getPhoneNumber());
        updateDto.setPicture(userHomePageDto.getPicture());
        updateDto.setEmail(userHomePageDto.getEmail());
        updateDto.setName(userHomePageDto.getName());
        updateDto.setBio(userHomePageDto.getBio());
        updateDto.setPassword(userHomePageDto.getPassword());
        updateDto.setId(LoginService.getId());
        updateDto.setStatus("Offline");
        return updateDto;
    }
}
