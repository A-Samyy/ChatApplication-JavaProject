package gov.iti.jets.service.dtos;

import java.io.Serializable;

public class ClientFriendRequestDto implements Serializable {
    private static final long serialVersionUID = 1427672600912364060L;


    private int UserId;
    private String friendPhoneNumber;

    public ClientFriendRequestDto() {
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFriendPhoneNumber() {
        return friendPhoneNumber;
    }

    public void setFriendPhoneNumber(String friendPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
    }
}
