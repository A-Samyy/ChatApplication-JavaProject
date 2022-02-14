package gov.iti.jets.service.Impl;

import gov.iti.jets.presistance.Daos.UserDao;
import gov.iti.jets.presistance.Dtos.UserDto;
import gov.iti.jets.service.RegisterInt;
import gov.iti.jets.service.dtos.RegisterDto;

import java.rmi.RemoteException;

public class RegisterImpl implements RegisterInt {
    UserDao userDao = new UserDao();
    @Override
    public Boolean addUser(RegisterDto registerDTO) throws RemoteException {
        UserDto userDto= mapper(registerDTO);
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
        return userDto;
    }
}
