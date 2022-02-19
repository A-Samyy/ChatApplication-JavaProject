package gov.iti.jets.networking;

import gov.iti.jets.service.ContactListInt;
import gov.iti.jets.service.Impl.ContactListImpl;
import gov.iti.jets.service.Impl.LoginImpl;
import gov.iti.jets.service.Impl.RegisterImpl;
import gov.iti.jets.service.Impl.ServerMessageImpl;
import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.RegisterInt;
import gov.iti.jets.service.ServerMessageInt;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICreateRegister {
    private static RMICreateRegister rmiCreateRegister = new RMICreateRegister();
    private RMICreateRegister() {
        try {
            RegisterInt register = new RegisterImpl();
            LoginInt loginService = new LoginImpl();
            ContactListInt contactListInt = new ContactListImpl();
            ServerMessageInt message = new ServerMessageImpl();
            Registry registry = LocateRegistry.getRegistry(4000);
            registry.rebind("RegisterService",register);
            registry.rebind("loginService",loginService);
            registry.rebind("ContactListService",contactListInt);
//            registry.bind("MessageService", server);
//            registry.bind("BroadcastService", server);
            registry.rebind("MessageService", message);
            //   registry.bind("BroadcastService", server);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static RMICreateRegister getInstance() {
        return rmiCreateRegister;
    }


}
