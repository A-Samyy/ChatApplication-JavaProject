package gov.iti.jets.service.services;

import gov.iti.jets.common.interfaces.ClientAnnounceMessageInt;
import gov.iti.jets.common.interfaces.ClientFileRequestInt;
import gov.iti.jets.service.impl.ClientAnnounceImpl;
import gov.iti.jets.service.impl.ClientFileRequestImpl;
import gov.iti.jets.service.impl.ClientGroupChatMessageImpl;
import gov.iti.jets.service.impl.ClientMessageImpl;

import java.rmi.RemoteException;
import java.util.prefs.Preferences;

public class RememberMeServices {
    private static RememberMeServices rememberMeServices = new RememberMeServices();
    private Preferences userInfo = Preferences.userNodeForPackage(getClass());
    private final String key ="userId";
    ClientAnnounceMessageInt clientAnnounceMessageInt ;
    ClientMessageImpl clientMessage ;
    ClientGroupChatMessageImpl clientGroupChatMessageInt;
    ClientFileRequestInt clientFileRequestInt;
    private int value;

    private RememberMeServices() {
    }
    public static RememberMeServices getInstance() {
        return rememberMeServices;
    }

    public int getUserInfoValue() {
        return userInfo.getInt(this.key, 0);
    }

    public String getKey() {
        return key;
    }

public void removeUserInfo(){
        this.userInfo.remove(this.key);
}



    public void setValue(int value) {
        this.value = value;
        userInfo.putInt(this.key, this.value);
    }

    public Preferences getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Preferences userInfo) {
        this.userInfo = userInfo;
    }




    public void registerme(){
        try {
            clientFileRequestInt = new ClientFileRequestImpl();
            clientAnnounceMessageInt = new ClientAnnounceImpl();
            clientMessage = new ClientMessageImpl();
            MessageService.getInstance().setClient(clientMessage);
            clientGroupChatMessageInt = new ClientGroupChatMessageImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
