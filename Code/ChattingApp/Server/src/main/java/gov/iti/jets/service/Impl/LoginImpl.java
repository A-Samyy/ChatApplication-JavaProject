package gov.iti.jets.service.Impl;

import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.dtos.LoginDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginImpl extends UnicastRemoteObject implements LoginInt {
    protected LoginImpl() throws RemoteException {
    }

    @Override
    public Boolean isPhoneNumberExist(LoginDto loginDto) throws RemoteException {
        return null;
    }

    @Override
    public Boolean isPasswordValid(LoginDto loginDto) throws RemoteException {
        return null;
    }
}
