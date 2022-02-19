package gov.iti.jets.service.Impl;

import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.dtos.LoginDto;
import gov.iti.jets.service.dtos.UserHomePageDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;

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

    @Override
    public UserHomePageDto getUserById(int id) throws RemoteException {
        UserHomePageDto userHomePageDtoDto = new UserHomePageDto();
        UserDto userDto = userDao.getUserDtoById(id);
        userHomePageDtoDto = mapperToUser(userDto);
        return userHomePageDtoDto;
    }
    UserHomePageDto mapperToUser(UserDto userDto){
        UserHomePageDto userHomePageDtoDto = new UserHomePageDto();
        userHomePageDtoDto.setPhoneNumber(userDto.getPhoneNumber());
        userHomePageDtoDto.setName(userDto.getName());
        userHomePageDtoDto.setGender(userDto.getGender());
        userHomePageDtoDto.setEmail(userDto.getEmail());
        userHomePageDtoDto.setCountry(userDto.getCountry());
        userHomePageDtoDto.setBio(userDto.getBio());
        //
        java.util.Date date;
        if(userDto.getDateOfBirth() == null){
            date = null;
        }else {
            date = new java.util.Date(userDto.getDateOfBirth().getTime());
        }
        userHomePageDtoDto.setDateOfBirth(date);

        try {
//            System.out.println("error gded1"+encodeImage(userDto.getPicture()));
            userHomePageDtoDto.setPicture(encodeImage(userDto.getPicture()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //satuts
        userHomePageDtoDto.setStatus(userDto.getStatus().name());

        return userHomePageDtoDto;
    }

    public String encodeImage(String imgPath) throws IOException {
        String imageString = null;

        if(!imgPath.isEmpty()){
//            System.out.println("error gded"+imgPath);
            FileInputStream stream = new FileInputStream(imgPath);
            byte[] imageData = stream.readAllBytes();
            imageString = Base64.getEncoder().encodeToString(imageData);
            stream.close();

        }
        return  imageString;
    }
}
