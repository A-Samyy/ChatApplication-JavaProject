package gov.iti.jets.service.Impl;

import gov.iti.jets.presistance.Daos.UserDao;
import gov.iti.jets.presistance.Dtos.Status;
import gov.iti.jets.presistance.Dtos.UserDto;
import gov.iti.jets.service.RegisterInt;
import gov.iti.jets.service.dtos.RegisterDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RegisterImpl  extends UnicastRemoteObject implements RegisterInt {
    UserDao userDao = new UserDao();
    public  RegisterImpl() throws RemoteException{

    }
    @Override
    public Boolean addUser(RegisterDto registerDTO) throws RemoteException {
        System.out.println(registerDTO);
        UserDto userDto= mapper(registerDTO);
        System.out.println(userDto+"RegisterImpl Server");
        return userDao.addUserDto(userDto);
    }

    private UserDto mapper(RegisterDto registerDto){
        UserDto userDto=new UserDto();
        userDto.setPhoneNumber(registerDto.getPhoneNumber());
        userDto.setName(registerDto.getName());
        userDto.setPassword(registerDto.getPassword());
        userDto.setEmail(registerDto.getEmail());
        userDto.setCountry(registerDto.getCountry());
        userDto.setDateOfBirth(registerDto.getDateOfBirth());
        userDto.setGender(registerDto.getGender());
        userDto.setPicture(registerDto.getPicture());
        userDto.setBio(registerDto.getBio());
        userDto.setStatus(Status.ACTIVE);
        System.out.println(userDto);
        return userDto;
    }
}
