package gov.iti.jets.common.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.List;

public class ClientGroupChatDto implements Serializable {
    private static final long serialVersionUID = 1427672609912564060L;
    @Positive
    int  groupId ;
    @NotNull
    String groupName;
    List<String> usersId;

    public int getGroupCreatorId() {
        return groupCreatorId;
    }

    public void setGroupCreatorId(int groupCreatorId) {
        this.groupCreatorId = groupCreatorId;
    }

    int groupCreatorId;

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

    public List<String> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<String> usersId) {
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
