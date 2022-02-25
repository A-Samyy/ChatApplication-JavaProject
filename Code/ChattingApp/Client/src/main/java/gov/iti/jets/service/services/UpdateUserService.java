package gov.iti.jets.service.services;

import gov.iti.jets.common.dtos.UpdateDto;
import gov.iti.jets.common.interfaces.UpdateUserInt;
import gov.iti.jets.networking.RMIRegister;

import java.rmi.RemoteException;

public class UpdateUserService {
    RMIRegister rmiRegister = RMIRegister.getInstance();

    public UpdateUserService() {
    }
    public boolean updateUser(UpdateDto updateDto){
        boolean flag = false;
        UpdateUserInt updateUserInt = rmiRegister.updateUserService();
        try {
            flag = updateUserInt.updateUser(updateDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        System.out.println("toseethe flag"+flag);
        return flag;
    }
}
