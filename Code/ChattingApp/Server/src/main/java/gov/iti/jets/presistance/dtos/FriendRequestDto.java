package gov.iti.jets.presistance.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.Positive;

public class FriendRequestDto {
//    @Positive(message = "user Id can't be Negative")
    int userId;
//    @Positive(message = "friend Id can't be Negative")
    int friendId;

    public FriendRequestDto() {
        //ValidationMaker.getInstance().validate(this);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    @Override
    public String toString() {
        return "FriendRequestDto{" +
                "userId=" + userId +
                ", friendId=" + friendId +
                '}';
    }
}
