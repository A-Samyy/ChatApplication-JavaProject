package gov.iti.jets.common.interfaces;

import gov.iti.jets.common.dtos.ContactDto;
import gov.iti.jets.common.dtos.GroupDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GroupListInt extends Remote {
    static final long serialVersionUID = 1420672609912364068L;
    List<GroupDto> getListGroup (int id) throws RemoteException;

}
