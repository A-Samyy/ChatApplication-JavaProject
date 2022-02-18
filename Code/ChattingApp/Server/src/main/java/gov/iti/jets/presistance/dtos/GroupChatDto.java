package gov.iti.jets.presistance.dtos;

import java.util.List;

public class GroupChatDto {
    int  groupId ;
    String groupName;
    List<Integer> usersId;

    public GroupChatDto() {
    }

    public List<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Integer> usersId) {
        this.usersId = usersId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
