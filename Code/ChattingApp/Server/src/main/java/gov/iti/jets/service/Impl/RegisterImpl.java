package gov.iti.jets.service.Impl;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import gov.iti.jets.presistance.Daos.UserDao;
import gov.iti.jets.presistance.Dtos.Status;
import gov.iti.jets.presistance.Dtos.UserDto;
import gov.iti.jets.service.RegisterInt;
import gov.iti.jets.service.dtos.RegisterDto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;
import java.util.Date;

public class RegisterImpl  extends UnicastRemoteObject implements RegisterInt {
    UserDao userDao = new UserDao();
    static int i=0;
    public  RegisterImpl() throws RemoteException{

    }
    @Override
    public Boolean addUser(RegisterDto registerDTO) throws RemoteException {
        System.out.println(registerDTO);
        UserDto userDto= mapper(registerDTO);
        System.out.println(userDto+"RegisterImpl Server");

        return userDao.addUserDto(userDto);
    }
    public String decodeImage(String image) throws Exception {
        byte[] data = Base64.getDecoder().decode(image.getBytes(StandardCharsets.UTF_8));
        String savePath = "src/main/resources/clientPictures/user"+i+".jpg";
        FileOutputStream fileOutputStream = new FileOutputStream(savePath);
        i++;
        fileOutputStream.write(data);
        fileOutputStream.close();
        System.out.println(savePath);
        return  savePath;
    }
    private UserDto mapper(RegisterDto registerDto) {
        String imagePath = "";
        try {
            imagePath  = decodeImage(registerDto.getPicture());
            System.out.println("mapper "+imagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserDto userDto=new UserDto();
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
