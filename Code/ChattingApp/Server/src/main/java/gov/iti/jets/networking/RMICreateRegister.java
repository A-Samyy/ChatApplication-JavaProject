package gov.iti.jets.networking;

import gov.iti.jets.service.ContactListInt;
import gov.iti.jets.service.Impl.ContactListImpl;
import gov.iti.jets.service.Impl.LoginImpl;
import gov.iti.jets.service.Impl.RegisterImpl;
import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.RegisterInt;

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
            Registry registry = LocateRegistry.createRegistry(5006);
            registry.rebind("RegisterService",register);
            registry.rebind("loginService",loginService);
            registry.rebind("ContactListService",contactListInt);
//            registry.bind("MessageService", server);
//            registry.bind("BroadcastService", server);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static RMICreateRegister getInstance() {
        return rmiCreateRegister;
    }


}
