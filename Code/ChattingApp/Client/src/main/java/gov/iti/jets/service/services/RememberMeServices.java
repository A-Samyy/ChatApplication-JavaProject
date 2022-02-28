package gov.iti.jets.service.services;

import java.util.prefs.Preferences;

public class RememberMeServices {
    private static RememberMeServices rememberMeServices = new RememberMeServices();
    private Preferences userInfo = Preferences.userNodeForPackage(getClass());
    private final String key ="userId";
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
}
