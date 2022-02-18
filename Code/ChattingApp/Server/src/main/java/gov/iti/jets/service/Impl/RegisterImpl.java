package gov.iti.jets.service.Impl;

import gov.iti.jets.presistance.Daos.UserDao;
import gov.iti.jets.presistance.Dtos.Status;
import gov.iti.jets.presistance.Dtos.UserDto;
import gov.iti.jets.service.RegisterInt;
import gov.iti.jets.service.dtos.RegisterDto;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;

public class RegisterImpl extends UnicastRemoteObject implements RegisterInt {
    UserDao userDao = new UserDao();
    static int i = 0;

    public RegisterImpl() throws RemoteException {

    }

    @Override
    public Boolean addUser(RegisterDto registerDTO) throws RemoteException {
        UserDto userDto = mapper(registerDTO);
        return userDao.addUserDto(userDto);
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
            System.out.println("marwa image  "+image);
            savePath = "src/main/resources/clientPictures/default.jgp";
        }

        return savePath;
    }

    private UserDto mapper(RegisterDto registerDto) {
        String imagePath = "";
        try {
            imagePath = decodeImage(registerDto.getPicture(),registerDto.getPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserDto userDto = new UserDto();
        userDto.setPhoneNumber(registerDto.getPhoneNumber());
        userDto.setName(registerDto.getName());
        userDto.setPassword(registerDto.getPassword());
        userDto.setEmail(registerDto.getEmail());
        userDto.setCountry(registerDto.getCountry());
        userDto.setDateOfBirth(registerDto.getDateOfBirth());
        userDto.setGender(registerDto.getGender());
        userDto.setPicture(imagePath);
        userDto.setBio(registerDto.getBio());
        userDto.setStatus(Status.ACTIVE);
        return userDto;
    }
}
