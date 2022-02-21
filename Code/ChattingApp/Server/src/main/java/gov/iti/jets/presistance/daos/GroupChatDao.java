package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.GroupChatDto;
import gov.iti.jets.presistance.util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupChatDao {
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();
    GroupChatUsersDao groupChatUsersDao = new GroupChatUsersDao();

    public GroupChatDao() {
    }


    public boolean addGroup(GroupChatDto groupChatDto) {
        boolean check;
        boolean cheak2;
        try {
            conn = connector.getConnection();
            check = insertingGroupName(groupChatDto);
            groupChatDto.setGroupId(getTheLastGroupID());
            System.out.println("before adding groupchatUser for group id = "+getTheLastGroupID());
            cheak2 = groupChatUsersDao.addGroupChatUsersTable(groupChatDto);
            System.out.println(cheak2);
            return check && cheak2;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean insertingGroupName(GroupChatDto groupChatDto) {
        String sql = "insert into chatting_app.group_chat(group_chat_name)  values(?)";
        return injectContact(groupChatDto, sql);
    }

    public int getTheLastGroupID() {
        String sql = "SELECT max(group_chat_id) FROM chatting_app.group_chat ; ";
        try {
            conn = connector.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet= preparedStatement.executeQuery();
            resultSet.next();
            int id =resultSet.getInt(1);
            System.out.println(id);
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // The only method that uses this method is in the GroupChatUsers to delete the dependency first
    public boolean deleteGroupChat(GroupChatDto groupChatDto) {
        try {
            conn = connector.getConnection();
            String sql = "DELETE FROM chatting_app.group_chat WHERE (group_chat_id=" + groupChatDto.getGroupId() + ");";
            preparedStatement = conn.prepareStatement(sql);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getGroupsNameById(GroupChatDto groupChatDto) {
        try {
            conn = connector.getConnection();
            String sql = "select group_chat_name from chatting_app.group_chat where group_chat_ID=" + groupChatDto.getGroupId();
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean injectContact(GroupChatDto groupChatDto, String sql) {
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, groupChatDto.getGroupName());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
