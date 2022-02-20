package gov.iti.jets.networking;

import gov.iti.jets.common.interfaces.LoginInt;
import gov.iti.jets.common.interfaces.ContactListInt;
import gov.iti.jets.service.Impl.ContactListImpl;
import gov.iti.jets.service.Impl.LoginImpl;
import gov.iti.jets.service.Impl.RegisterImpl;
import gov.iti.jets.service.Impl.ServerMessageImpl;
import gov.iti.jets.common.interfaces.RegisterInt;
import gov.iti.jets.common.interfaces.ServerMessageInt;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICreateRegister {
    private static RMICreateRegister rmiCreateRegister = new RMICreateRegister();
    private RMICreateRegister() {
        Registry registry = null;
        try {

            RegisterInt register = new RegisterImpl();
            LoginInt loginService = new LoginImpl();
            ContactListInt contactListInt = new ContactListImpl();
            ServerMessageInt message = new ServerMessageImpl();
//            try {
                registry = LocateRegistry.createRegistry(6000);

//            }catch (Exception ex){
//                registry = LocateRegistry.getRegistry(6066);
//            }
            registry.rebind("RegisterService",register);
            registry.rebind("loginService",loginService);
            registry.rebind("ContactListService",contactListInt);
            registry.rebind("MessageService", message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public static RMICreateRegister getInstance() {
        return rmiCreateRegister;
    }


}
