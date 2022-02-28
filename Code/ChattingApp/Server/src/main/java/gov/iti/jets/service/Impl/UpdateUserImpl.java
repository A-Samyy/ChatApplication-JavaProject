package gov.iti.jets.service.Impl;

import gov.iti.jets.common.dtos.UpdateDto;
import gov.iti.jets.common.interfaces.UpdateUserInt;
import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.presistance.dtos.Status;
import gov.iti.jets.presistance.dtos.UserDto;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;

public class UpdateUserImpl extends UnicastRemoteObject implements UpdateUserInt {
    UserDao userDao = new UserDao();
    public UpdateUserImpl() throws RemoteException {
    }

    @Override
    public Boolean updateUser(UpdateDto updateDto) throws RemoteException {
        UserDto  userDto = userDao.getUserDtoById(updateDto.getId());
        userDto.setBio(updateDto.getBio());
        userDto.setEmail(updateDto.getEmail());
        userDto.setPhoneNumber(updateDto.getPhoneNumber());
        userDto.setName(updateDto.getName());
        try {
            if(updateDto.getPicture()!=null){
                userDto.setPicture(decodeImage(updateDto.getPicture(),userDto.getPhoneNumber()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(updateDto.getPassword()!= null){
            userDto.setPassword(updateDto.getPassword());

        }
        userDto.setStatus(Status.getStatusFromNumber(getStatusNumber(updateDto.getStatus())));
        return userDao.updateUserDto(userDto);
    }
    int getStatusNumber(String status){
        int number = 0;
        switch (status) {
            case "Active":
                number = 1;
                break;
            case "Busy":
                number = 2;
                break;
            case "Away":
                number = 3;
                break;
            case "Offline":
                number = 4;
                break;
        }
        return number;
    }
    public String decodeImage(String image,String phone_number) throws Exception {

        String savePath ;
        if(image != null){
            byte[] data = Base64.getDecoder().decode(image.getBytes(StandardCharsets.UTF_8));
            savePath = "src/main/resources/clientPictures/user" + phone_number + ".jpg";
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);
            fileOutputStream.write(data);
            fileOutputStream.close();
        }else{

            savePath = "src/main/resources/clientPictures/default.jgp";
        }

        return savePath;
    }
}
