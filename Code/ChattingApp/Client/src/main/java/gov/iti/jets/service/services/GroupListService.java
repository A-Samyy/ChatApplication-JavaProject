package gov.iti.jets.service.services;

import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.dtos.GroupDto;
import gov.iti.jets.networking.RMIRegister;

import java.rmi.RemoteException;
import java.util.List;

public class GroupListService {
    RMIRegister rmiRegister = RMIRegister.getInstance();
    public GroupListService() {
    }

    public List<GroupDto> getListOfGroup(int id){
        try {
            return rmiRegister.groupListService().getListGroup(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

}
