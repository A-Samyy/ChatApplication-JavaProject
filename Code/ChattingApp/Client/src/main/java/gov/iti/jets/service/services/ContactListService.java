package gov.iti.jets.service.services;

import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.networking.RMIRegister;

import java.rmi.RemoteException;
import java.util.ArrayList;
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
