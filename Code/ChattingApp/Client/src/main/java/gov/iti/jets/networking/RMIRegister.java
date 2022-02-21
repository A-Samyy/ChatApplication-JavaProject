package gov.iti.jets.networking;



import gov.iti.jets.common.interfaces.*;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRegister {
    private static RMIRegister rmiRegister = new RMIRegister();
    Registry registry;


    private RMIRegister() {
        try {
            registry = LocateRegistry.getRegistry("localhost", 4005);
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
    }
    public AddGroupChatInt addGroupChatService(){
        AddGroupChatInt addGroupChatInt =null;
        try{
            addGroupChatInt =(AddGroupChatInt) registry.lookup("AddGroupChatService");
        }catch (RemoteException e){
            e.printStackTrace();
        }catch (NotBoundException e) {
            e.printStackTrace();
        }return addGroupChatInt;
    }


}
