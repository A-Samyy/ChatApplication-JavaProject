package gov.iti.jets.presentation.util;

import gov.iti.jets.presentation.models.FileCounterModel;
import gov.iti.jets.presentation.models.UserModel;

public class ModelFactory {
    private final static ModelFactory modelFactory = new ModelFactory();
    private final UserModel userModel = new UserModel();
    private final FileCounterModel fileCounterModel= new FileCounterModel();
    private ModelFactory(){
        
    }

    public static ModelFactory getInstance() {
        return modelFactory;
    }

    public UserModel getUserModel(){
        return userModel;
    }

    public FileCounterModel getFileCounterModel(){
        return fileCounterModel;
    }
}