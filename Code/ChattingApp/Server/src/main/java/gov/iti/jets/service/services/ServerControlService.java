package gov.iti.jets.service.services;

public class ServerControlService {
    public static boolean flag =false;
    public ServerControlService() {
    }
    public void openConnection(){
        this.flag=true;
    }
    public  void closeConnection(){
        this.flag=false;
    }
}
