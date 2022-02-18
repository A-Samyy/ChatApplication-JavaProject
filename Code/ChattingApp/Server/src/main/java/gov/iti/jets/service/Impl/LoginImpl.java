package gov.iti.jets.service.Impl;

import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.dtos.LoginDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginImpl extends UnicastRemoteObject implements LoginInt {

    UserDao userDao = new UserDao();
    private int userID;

    public LoginImpl() throws RemoteException {
    }

    @Override
    public int isPhoneNumberExist(LoginDto loginDto) throws RemoteException {
        this.userID=userDao.getUserIdByPhoneNumber(loginDto.getPhoneNumber());
        return this.userID;
    }

    @Override
    public String isPasswordValid() throws RemoteException {
        return userDao.getUserPasswordById(this.userID);
    }
}
