package gov.iti.jets.service.services;

import gov.iti.jets.networking.RMIRegister;
import gov.iti.jets.presentation.models.UserModel;
import gov.iti.jets.presentation.util.ModelFactory;
import gov.iti.jets.service.ClientMesseageInt;
import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.dtos.LoginDto;
import gov.iti.jets.service.dtos.UserHomePageDto;
import gov.iti.jets.service.impl.ClientMessageImpl;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.util.Base64;

public class LoginService {
    static int userId ;
    private final ModelFactory modelFactory = ModelFactory.getInstance();
    UserModel userModel = modelFactory.getUserModel();
    RMIRegister rmiRegister = RMIRegister.getInstance();
    LoginInt loginInt = rmiRegister.loginService();
    LoginDto loginDto = null;
    ClientMessageImpl clientMesseageInt;

    public LoginService() {

    }

    public int getUserId(String phoneNumber) throws RemoteException {

        this.loginDto = new LoginDto(phoneNumber);
        this.userId = this.loginInt.isPhoneNumberExist(this.loginDto);
        return this.userId;
    }

    public String getPassword() throws RemoteException {
        return this.loginInt.isPasswordValid();
    }

    public void  getdata() throws RemoteException {
        clientMesseageInt=new ClientMessageImpl();
        UserHomePageDto userHomePageDto = null;
        try {
            userHomePageDto = loginInt.getUserById(this.userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        userModel.setPhoneNumber(userHomePageDto.getPhoneNumber());
        userModel.setUserName(userHomePageDto.getName());
        userModel.setGender(userHomePageDto.getGender());
        userModel.setEmail(userHomePageDto.getEmail());
        userModel.setCountry(userHomePageDto.getCountry());
        userModel.setBio(userHomePageDto.getBio());
        userModel.setStatus(userHomePageDto.getStatus());
        if (userHomePageDto.getDateOfBirth() == null){
            userModel.setDate(" ");
        }else{
            userModel.setDate(userHomePageDto.getDateOfBirth().toString());
        }

        try {
            if(userHomePageDto.getPicture()!=null){
                userModel.setImage(decodeImage(userHomePageDto.getPicture()));
            }else{
//                userModel.setImage(decodeImage("C:/Users/marwa/OneDrive/Pictures/20210810_090650.jpg"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Image decodeImage(String image) throws Exception {
        Image img ;

        byte[] data = Base64.getDecoder().decode(image.getBytes(StandardCharsets.UTF_8));
        img = new Image(new ByteArrayInputStream(data));
        return img;
    }

    public static int getId(){
        return userId;
    }

}
