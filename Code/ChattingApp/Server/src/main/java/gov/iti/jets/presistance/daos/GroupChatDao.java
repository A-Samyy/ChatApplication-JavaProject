package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.GroupChatDto;
import gov.iti.jets.presistance.util.Connector;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupChatDao {

    //    private DataSource ds = null;
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Statement statement =null;
    Connector connector = Connector.getInstance();

    public GroupChatDao() {
        try {
            conn = connector.getConnection();
            statement=conn.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(GroupChatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public boolean addGroup(GroupChatDto groupChatDto) {

        String sql = "insert into chatting_app.group_chat(group_chat_name)  values(?)";
        boolean check = injectContact(groupChatDto, sql);
        boolean cheak2 = false;
        groupChatDto.setGroupId(getTheLastGroupID());
        System.out.println(groupChatDto.getGroupId());
        GroupChatUsersDao groupChatUsersDao = new GroupChatUsersDao();
        cheak2 = groupChatUsersDao.addGroupChatUsersTable(groupChatDto);
        return check && cheak2;
    }

    public int getTheLastGroupID() {
        String sql = "SELECT max(group_chat_id) FROM chatting_app.group_chat ; ";
        try {
            //        ResultSet resultSet = statement.executeQuery(sql);
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.next());

            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // The only method that uses this method is in the GroupChatUsers to delete the dependency first
    public boolean deleteGroupChat(GroupChatDto groupChatDto) {
        try {
            String sql = "DELETE FROM chatting_app.group_chat WHERE (group_chat_id=" + groupChatDto.getGroupId() + ");";
            preparedStatement = conn.prepareStatement(sql);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getGroupsNameById(GroupChatDto groupChatDto) {
        try {
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
        }
    }

    private boolean injectContact(GroupChatDto groupChatDto, String sql) {
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, groupChatDto.getGroupName());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
