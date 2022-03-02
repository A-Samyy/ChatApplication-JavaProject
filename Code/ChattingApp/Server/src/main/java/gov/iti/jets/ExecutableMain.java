package gov.iti.jets;

import java.rmi.RemoteException;

public class ExecutableMain {
    public static void main(String[] args){
        try {
            MainApp.main(args);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
