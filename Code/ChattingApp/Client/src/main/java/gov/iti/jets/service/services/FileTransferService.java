package gov.iti.jets.service.services;
import gov.iti.jets.networking.RMIRegister;
import java.io.*;


public class FileTransferService {
    RMIRegister rmiRegister = RMIRegister.getInstance();
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public FileTransferService() {
    }

}
