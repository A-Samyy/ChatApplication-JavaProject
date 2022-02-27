package gov.iti.jets.common.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class ClientFriendRequestDto implements Serializable {
    private static final long serialVersionUID = 1427672600912364060L;

//   @Positive(message = "id must be positive number")

    private int UserId;
//    @NotNull(message = "phone number is required")
//    @Size(min = 11,max = 11,message = "uncorrected phone number")
    private String friendPhoneNumber;

    public ClientFriendRequestDto() {
        //ValidationMaker.getInstance().validate(this);
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
