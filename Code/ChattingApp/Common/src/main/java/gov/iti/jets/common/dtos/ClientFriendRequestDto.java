package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class ClientFriendRequestDto implements Serializable {
    private static final long serialVersionUID = 1427672600912364060L;

   @Positive
    private int UserId;
    @NotNull
    @Size(min = 11,max = 11)
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

    @Override
    public String toString() {
        return "ClientFriendRequestDto{" +
                "UserId=" + UserId +
                ", friendPhoneNumber='" + friendPhoneNumber + '\'' +
                '}';
    }
}
