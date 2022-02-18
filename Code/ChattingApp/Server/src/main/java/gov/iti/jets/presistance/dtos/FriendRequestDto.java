package gov.iti.jets.presistance.dtos;

public class FriendRequestDto {
    int userId;
    int friendId;

    public FriendRequestDto() {
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
