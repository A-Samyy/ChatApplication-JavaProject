package gov.iti.jets.presentation.util;

public class ModelFactory {
    private static ModelFactory modelFactory = new ModelFactory();

    private ModelFactory(){
        
    }

    public static ModelFactory getInstance() {
        return modelFactory;
    }
}