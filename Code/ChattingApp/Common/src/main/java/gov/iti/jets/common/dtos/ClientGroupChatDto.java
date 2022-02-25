package gov.iti.jets.common.dtos;

import java.io.Serializable;
import java.util.List;

public class ClientGroupChatDto implements Serializable {
    private static final long serialVersionUID = 1427672609912564060L;
    int  groupId ;
    String groupName;
    List<Integer> usersId;

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

    public List<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Integer> usersId) {
        this.usersId = usersId;
    }

    @Override
    public String toString() {
        return "ClientGroupChatDto{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", usersId=" + usersId +
                '}';
    }
}
