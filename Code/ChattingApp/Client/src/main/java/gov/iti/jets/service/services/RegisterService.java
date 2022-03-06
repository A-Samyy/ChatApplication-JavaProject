package gov.iti.jets.service.services;


import gov.iti.jets.common.dtos.RegisterDto;
import gov.iti.jets.common.interfaces.RegisterInt;
import gov.iti.jets.networking.RMIRegister;
import java.rmi.RemoteException;

public class RegisterService {
    RMIRegister rmiRegister = RMIRegister.getInstance();
    RegisterInt registerInt= rmiRegister.registerService();


    public RegisterService() throws RemoteException {
    }

    public Boolean registUser(RegisterDto registerDto) throws RemoteException {
        return registerInt.addUser(registerDto);
    }
}
