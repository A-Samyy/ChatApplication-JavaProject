package gov.iti.jets.service.services;


import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.service.RegisterInt;
import gov.iti.jets.service.dtos.RegisterDto;
import java.rmi.RemoteException;

public class RegisterService {
    RMIRegister rmiRegister = RMIRegister.getInstance();
    RegisterInt registerInt= rmiRegister.registerService();


    public RegisterService() throws RemoteException {
    }

    public Boolean registUser(RegisterDto registerDto) throws RemoteException {
        System.out.println(registerDto + "registerService Client");
        return registerInt.addUser(registerDto);
    }
}
