package gov.iti.jets.presistance.dtos;

public class ContactDto {
    int userId;
    int friendId;
    String type;

    public ContactDto() {
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
