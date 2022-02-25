package gov.iti.jets.networking;



import gov.iti.jets.common.interfaces.*;
import gov.iti.jets.service.impl.ClientGroupChatMessageImpl;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegister {
    private static RMIRegister rmiRegister = new RMIRegister();
    Registry registry;


    private RMIRegister() {                 /// cmd-> ipconfig
        try {                                ///getRegistry("192.168.100.2", port)
            registry = LocateRegistry.getRegistry("localhost", 3333);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static RMIRegister getInstance() {
        return rmiRegister;
    }


    public RegisterInt registerService(){
        RegisterInt registerInt = null;
        try {
            registerInt = (RegisterInt) registry.lookup("RegisterService");
        } catch (NotBoundException |AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return registerInt;
    }

    public LoginInt loginService(){
        LoginInt loginInt = null;
        try {
            loginInt = (LoginInt) registry.lookup("loginService");
        } catch (NotBoundException |AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return loginInt;
    }

    public ContactListInt contactListService(){
        ContactListInt contactListInt = null;
        try {
            contactListInt = (ContactListInt) registry.lookup("ContactListService");
        } catch (NotBoundException |AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return contactListInt;
    }
    public ServerMessageInt messageService(){
        ServerMessageInt serverMessageInt = null;
        try {
            serverMessageInt = (ServerMessageInt) registry.lookup("MessageService");
        } catch (NotBoundException |AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return serverMessageInt;

    }
    public FriendRequestInt friendRequestService(){
        FriendRequestInt  friendRequestInt = null;
        try {
            friendRequestInt = (FriendRequestInt) registry.lookup("FriendRequestService");
        } catch (NotBoundException |AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return friendRequestInt;
    }public ServerGroupChatMessageInt groupChatMessageService(){
        ServerGroupChatMessageInt  serverGroupChatMessageInt = null;
        try {
            serverGroupChatMessageInt = (ServerGroupChatMessageInt) registry.lookup("GroupChatMessageService");
        } catch (NotBoundException |AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return serverGroupChatMessageInt;
    }public ServerMessageAnnouncetInt serverMessageAnnouncetInt(){
        ServerMessageAnnouncetInt  serverMessageAnnouncetInt = null;
        try {
            serverMessageAnnouncetInt = (ServerMessageAnnouncetInt) registry.lookup("AnnouncementService");
        } catch (NotBoundException |AccessException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return serverMessageAnnouncetInt;
    }


}
