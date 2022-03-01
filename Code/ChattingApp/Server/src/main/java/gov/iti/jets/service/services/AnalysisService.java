package gov.iti.jets.service.services;

import gov.iti.jets.presistance.daos.UserDao;
import gov.iti.jets.presistance.dtos.UserDto;

import java.util.List;

public class AnalysisService {
    UserDao userDao = new UserDao();

    private static AnalysisService analysisService = new AnalysisService();
    public static AnalysisService getInstance() {
        return analysisService;
    }
    int numberOfMale;
    int numberOfFemale;




    public int getAllUsers() {

        return userDao.getAllUser().size();

    }
     public int getNumberOfFemaleUsers(){
        numberOfFemale=userDao.getNumberofFemaleUsers();
        return numberOfFemale;

    }
    public int getNumberOfMaleUsers(){

        numberOfMale=userDao.getNumberofMaleUsers();
        return numberOfMale;

    }






}
