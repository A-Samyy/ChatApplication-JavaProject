package gov.iti.jets.service.impl;


import gov.iti.jets.service.RegisterInt;
import gov.iti.jets.service.dtos.RegisterDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegisterService   {

    RegisterInt registerInt;
    public RegisterService() throws RemoteException {

        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        try {
            registerInt = (RegisterInt) registry.lookup("RegisterService");
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
    public Boolean registUser(RegisterDto registerDto) throws RemoteException {

        return registerInt.addUser(registerDto);


    }
}
