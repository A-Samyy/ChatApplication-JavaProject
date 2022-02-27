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

    private RMIRegister rmiRegister=RMIRegister.getInstance();
    private ServerGroupChatMessageInt serverGroupChatMessageInt;
    static public List<MessageGroupDto> groupMessages = new ArrayList<>();
    static public Map<Integer, List<MessageGroupDto>> mapForGroup = new HashMap<>();
    StageCoordinator stageCoordinator = StageCoordinator.getInstance();

    public ClientGroupChatMessageImpl() throws RemoteException {
        super();
        serverGroupChatMessageInt=rmiRegister.groupChatMessageService();
        serverGroupChatMessageInt.register(this, LoginService.getId());
    }

    @Override
    public boolean receiveGroupChatMessage(MessageGroupDto messageGroupDto) throws RemoteException {
        groupMessages.add(messageGroupDto);
        mapForGroup.put(messageGroupDto.getGroupId(),groupMessages);
        if(stageCoordinator.getGroupChatSectionController().get(messageGroupDto.getGroupId()) != null){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stageCoordinator.getGroupChatSectionController().get(messageGroupDto.getGroupId()).displayGroupMessage(messageGroupDto.getGroupId(),groupMessages);

                }
            });
        }
        return false;
    }
}
