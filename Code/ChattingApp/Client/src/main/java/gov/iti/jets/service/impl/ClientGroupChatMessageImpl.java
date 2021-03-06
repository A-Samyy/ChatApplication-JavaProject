package gov.iti.jets.service.impl;

import gov.iti.jets.common.dtos.MessageGroupDto;
import gov.iti.jets.common.interfaces.ClientGroupChatMessageInt;
import gov.iti.jets.common.interfaces.ServerGroupChatMessageInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.util.StageCoordinator;
import gov.iti.jets.service.services.LoginService;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientGroupChatMessageImpl extends UnicastRemoteObject implements ClientGroupChatMessageInt {

    transient private RMIRegister rmiRegister = RMIRegister.getInstance();
    transient  private ServerGroupChatMessageInt serverGroupChatMessageInt;
    transient  static public List<MessageGroupDto> groupMessages = new ArrayList<>();
    transient  static public Map<Integer, List<MessageGroupDto>> mapForGroup = new HashMap<>();
    transient   StageCoordinator stageCoordinator = StageCoordinator.getInstance();
    transient   static ClientGroupChatMessageImpl clientGroupChatMessage;

    public static ClientGroupChatMessageImpl getClientGroupChatMessage() {
        return clientGroupChatMessage;
    }

    public ClientGroupChatMessageImpl() throws RemoteException {
        super();

    }

    public void registerGroupChatMessageInt() {
        try {
            serverGroupChatMessageInt = rmiRegister.groupChatMessageService();
            serverGroupChatMessageInt.register(this, LoginService.getId());
            clientGroupChatMessage=this;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean receiveGroupChatMessage(MessageGroupDto messageGroupDto) throws RemoteException {
        groupMessages.add(messageGroupDto);
        mapForGroup.put(messageGroupDto.getGroupId(), groupMessages);
        if (stageCoordinator.getGroupChatSectionController().get(messageGroupDto.getGroupId()) != null) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stageCoordinator.getGroupChatSectionController().get(messageGroupDto.getGroupId()).displayGroupMessage(messageGroupDto.getGroupId(), groupMessages);

                }
            });
        }
        return false;
    }
}
