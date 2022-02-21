package gov.iti.jets.common.dtos;

import java.io.Serializable;

public class GroupDto implements Serializable {
    private static final long serialVersionUID = 1427372609914364060L;


    private int id;
    private String groupName;

    public GroupDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
