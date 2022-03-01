package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.LoginDto;
import gov.iti.jets.common.dtos.UserHomePageDto;
import gov.iti.jets.common.interfaces.LoginInt;
import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.presistance.dtos.Status;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.service.services.ServerControlService;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;

public class LoginImpl extends UnicastRemoteObject implements LoginInt {
    UserDao userDao = new UserDao();
    private int userID;
    private static int counter=0;
    public LoginImpl() throws RemoteException {
    }

    @Override
    public int isPhoneNumberExist(LoginDto loginDto) throws RemoteException {


        if (ServerControlService.flag) {
            int userid = userDao.getUserIdByPhoneNumber(loginDto.getPhoneNumber());
            if (!ServerGroupChatMessageImpl.clients.containsKey(userid)) {
                this.userID=userid;
                return this.userID;
            }
           else
                return -1;
        }
        return -1;
    }

    @Override
    public String isPasswordValid() throws RemoteException {

        if (ServerControlService.flag) {
            counter++;
            return userDao.getUserPasswordById(this.userID);
        }
        return "stopped";

    }

    @Override
    public UserHomePageDto getUserById(int id) throws RemoteException {
        UserHomePageDto userHomePageDtoDto = new UserHomePageDto();
        if (ServerControlService.flag) {
            UserDto userDto = userDao.getUserDtoById(id);
            userHomePageDtoDto = mapperToUser(userDto);
            return userHomePageDtoDto;
        }
        return userHomePageDtoDto;
    }

    UserHomePageDto mapperToUser(UserDto userDto) {
        UserHomePageDto userHomePageDtoDto = new UserHomePageDto();
        userHomePageDtoDto.setPhoneNumber(userDto.getPhoneNumber());
        userHomePageDtoDto.setName(userDto.getName());
        userHomePageDtoDto.setGender(userDto.getGender());
        userHomePageDtoDto.setEmail(userDto.getEmail());
        userHomePageDtoDto.setCountry(userDto.getCountry());
        userHomePageDtoDto.setBio(userDto.getBio());
        java.util.Date date;
        if (userDto.getDateOfBirth() == null) {
            date = null;
        } else {
            date = new java.util.Date(userDto.getDateOfBirth().getTime());
        }
        userHomePageDtoDto.setDateOfBirth(date);

        try {
            userHomePageDtoDto.setPicture(encodeImage(userDto.getPicture()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        userDto.setStatus(Status.ACTIVE);
        userHomePageDtoDto.setStatus(userDto.getStatus().name());
        userDao.updateUserDto(userDto);
        return userHomePageDtoDto;
    }

    public String encodeImage(String imgPath) throws IOException {
        String imageString = null;

        if (!imgPath.isEmpty()) {
            FileInputStream stream = new FileInputStream(imgPath);
            byte[] imageData = stream.readAllBytes();
            imageString = Base64.getEncoder().encodeToString(imageData);
            stream.close();

        }
        return imageString;
    }

    public int getCounter(){
        return counter;
    }
}
