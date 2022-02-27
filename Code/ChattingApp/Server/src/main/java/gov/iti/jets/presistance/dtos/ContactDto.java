package gov.iti.jets.presistance.dtos;

import gov.iti.jets.common.hibernate.ValidationMaker;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ContactDto {
    @Positive(message = "user Id can't be Negative")
    int userId;
    @Positive(message = "friend Id can't be Negative")
    int friendId;
    @NotNull
    @NotEmpty(message = "type is required")
    String type;

    public ContactDto() {
        ValidationMaker.getInstance().validate(this);
    }

    public ContactDto(int userId, int friendId, String type) {
        this.userId = userId;
        this.friendId = friendId;
        this.type = type;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getFriendId() {
        return this.friendId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                "userId=" + userId +
                ", friendId=" + friendId +
                ", type='" + type + '\'' +
                '}';
    }
}
