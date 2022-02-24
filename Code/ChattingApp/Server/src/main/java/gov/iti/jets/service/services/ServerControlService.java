package gov.iti.jets.service.services;

import gov.iti.jets.networking.RMICreateRegister;

public class ServerControlService {
    public static boolean flag =true;
    private static ServerControlService serverControlService = new ServerControlService();
    public static ServerControlService getInstance() {
        return serverControlService;
    }
    private ServerControlService() {
    }
    public void openConnection(){
        this.flag=true;
    }
    public  void closeConnection(){
        this.flag=false;
    }
}
