package gov.iti.jets.service.services;

import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.dtos.ContactDto;

import java.rmi.RemoteException;
import java.util.List;

public class ContactListService {
    RMIRegister rmiRegister = RMIRegister.getInstance();

    public ContactListService() {
    }

    public List<ContactDto> getListOfContact(int id){
        try {
            return rmiRegister.contactListService().getListContact(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
