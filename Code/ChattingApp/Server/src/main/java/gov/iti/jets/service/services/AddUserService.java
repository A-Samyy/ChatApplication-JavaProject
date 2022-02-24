package gov.iti.jets.service.services;

import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.presistance.dtos.UserDto;

public class AddUserService {
UserDao userDao=new UserDao();
    public AddUserService() {
    }
    public boolean addUser(UserDto userDto){
        return userDao.addUserDto(userDto);
    }

}
