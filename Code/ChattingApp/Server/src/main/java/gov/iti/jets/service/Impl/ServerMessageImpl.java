package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.MessageDto;
import gov.iti.jets.common.interfaces.ClientMesseageInt;
import gov.iti.jets.common.interfaces.ServerMessageInt;
import gov.iti.jets.service.services.ChatBotService;
import gov.iti.jets.service.services.ServerControlService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ServerMessageImpl extends UnicastRemoteObject implements ServerMessageInt {

    Map<Integer, ClientMesseageInt> clients = new HashMap<>();

    ChatBotService chatBotService = new ChatBotService();

    public ServerMessageImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean getMesssage(MessageDto messageDto) throws RemoteException {
        if (ServerControlService.flag) {
            if (clients.containsKey(messageDto.getFriendId())) {
                sendMessage(messageDto);
                return true;
            } else {
                System.out.println(messageDto.getFriendId()+" exist ? "+ clients.containsKey(messageDto.getFriendId()));
                MessageDto messageBot = new MessageDto();
                messageBot.setFriendId(messageDto.getUserId());
                messageBot.setUserName("Mr.ChatBot");
                messageBot.setUserId(messageDto.getFriendId());
                messageBot.setMessageContent(chatBotService.chatBotReply(messageDto.getMessageContent()));
                return sendChatBotMessage(messageBot);
            }
        } else {
            return false;
        }
    }


    @Override
    public boolean register(ClientMesseageInt clientMesseageInt, int userId) throws RemoteException {
        if(!clients.containsKey(userId)) {
            clients.put(userId, clientMesseageInt);
            return clients.containsKey(userId);
        }return false;
    }

    @Override
    public boolean unRegister(ClientMesseageInt clientMesseageInt, int userId) throws RemoteException {
        if (clients.containsKey(userId)) {
            return clients.remove(userId, clientMesseageInt);
        }
        return false;
    }

    private boolean sendChatBotMessage(MessageDto messageBot) {
        try {
            clients.get(messageBot.getFriendId()).reciveMessage(messageBot);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean sendMessage(MessageDto messageDto) {
        try {
            clients.get(messageDto.getFriendId()).reciveMessage(messageDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<Integer, ClientMesseageInt> clientsOnline() {
        return this.clients;
    }

    public int getOnlineUsers(){
        System.out.println("inServerMessageImpl:"+clients.size());
        return clients.size();
    }
}
