package gov.iti.jets.presentation.util;

public class ModelFactory {
    private static ModelFactory modelFactory = new ModelFactory();
    private UserModel userModel = new UserModel();
    private ModelFactory(){
        
    }

    public static ModelFactory getInstance() {
        return modelFactory;
    }

    public UserModel getUserModel(){
        return userModel;
    }


}