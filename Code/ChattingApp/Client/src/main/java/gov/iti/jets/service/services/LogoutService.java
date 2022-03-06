package gov.iti.jets.service.services;

import gov.iti.jets.common.interfaces.ServerFileRequestInt;
import gov.iti.jets.common.interfaces.ServerGroupChatMessageInt;
import gov.iti.jets.common.interfaces.ServerMessageAnnouncetInt;
import gov.iti.jets.common.interfaces.ServerMessageInt;
import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.controllers.ChatSectionController;
import gov.iti.jets.presentation.controllers.SidebarController;
import gov.iti.jets.service.impl.ClientAnnounceImpl;
import gov.iti.jets.service.impl.ClientFileRequestImpl;
import gov.iti.jets.service.impl.ClientGroupChatMessageImpl;
import gov.iti.jets.service.impl.ClientMessageImpl;

import java.rmi.RemoteException;

public class LogoutService {
    private RMIRegister rmiRegister = RMIRegister.getInstance();
    private ServerFileRequestInt serverFileRequestInt = rmiRegister.serverFileRequestService();
    private ServerGroupChatMessageInt serverGroupChatMessageInt = rmiRegister.groupChatMessageService();
    private ServerMessageAnnouncetInt serverMessageAnnouncetInt = rmiRegister.serverMessageAnnouncetInt();
    private ServerMessageInt serverMessageInt = rmiRegister.messageService();
    MessageService messageService = MessageService.getInstance();
    private int userId = LoginService.getId();
    RememberMeServices rememberMeServices=RememberMeServices.getInstance();
    public LogoutService() {
    }

    public boolean logout() {
        try {
            serverMessageInt.unRegister(ClientMessageImpl.getClientMessage(),userId);
            serverMessageAnnouncetInt.unRegister(ClientAnnounceImpl.getClientAnnounce());
            serverGroupChatMessageInt.unregister(ClientGroupChatMessageImpl.getClientGroupChatMessage(),userId);
            serverFileRequestInt.unRegister(ClientFileRequestImpl.getClientFileRequest(), userId);
            messageService.getClient().removeMe();
            rememberMeServices.removeUserInfo();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }
}
