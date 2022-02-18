package gov.iti.jets.service.Impl;

import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.presistance.dtos.Status;
import gov.iti.jets.presistance.dtos.UserDto;
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
        System.out.println(registerDTO);
        UserDto userDto = mapper(registerDTO);
        System.out.println(userDto + "RegisterImpl Server");

        return userDao.addUserDto(userDto);
    }

    public String decodeImage(String image,String phone_number) throws Exception {
        byte[] data = Base64.getDecoder().decode(image.getBytes(StandardCharsets.UTF_8));
        String savePath = "src/main/resources/clientPictures/user" + phone_number + ".jpg";
        FileOutputStream fileOutputStream = new FileOutputStream(savePath);
        i++;
        fileOutputStream.write(data);
        fileOutputStream.close();
        System.out.println(savePath);
        return savePath;
    }

    private UserDto mapper(RegisterDto registerDto) {
        String imagePath = "";
        try {
            imagePath = decodeImage(registerDto.getPicture(),registerDto.getPhoneNumber());
            System.out.println("mapper " + imagePath);
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

        System.out.println(userDto);
        return userDto;
    }
}
