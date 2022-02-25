package gov.iti.jets.service.services;

import gov.iti.jets.common.dtos.ClientGroupChatDto;
import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.interfaces.AddGroupChatInt;
import gov.iti.jets.networking.RMIRegister;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class AddGroupChatService {
    RMIRegister rmiRegister = RMIRegister.getInstance();

    public AddGroupChatService() {
    }
    public boolean addGroupChat(ClientGroupChatDto clientGroupChatDto) throws RemoteException {
        AddGroupChatInt addGroupChatInt=rmiRegister.addGroupChatService();
        System.out.println(addGroupChatInt.addGroupChat(clientGroupChatDto));
        return true;
    }


}
