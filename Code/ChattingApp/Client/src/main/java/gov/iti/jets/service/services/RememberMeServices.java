package gov.iti.jets.service.services;

import java.util.prefs.Preferences;

public class RememberMeServices {
    private Preferences userInfo = Preferences.userNodeForPackage(getClass());
    private String key ;
    private int value;

    public RememberMeServices() {
    }

    public RememberMeServices(String key , int value){
        this.key=key;
        this.value=value;
        userInfo.putInt(this.key , this.value);
    }

    public int getUserIDValue(){
        return userInfo.getInt(this.key , 0);
    }

    public Preferences getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Preferences userInfo) {
        this.userInfo = userInfo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
