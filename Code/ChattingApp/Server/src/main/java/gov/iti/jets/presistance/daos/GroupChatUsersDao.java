package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.ContactDto;
import gov.iti.jets.presistance.dtos.GroupChatDto;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.presistance.util.Connector;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupChatUsersDao {

    //   private DataSource ds = null;
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public GroupChatUsersDao() {
        try {
            conn= connector.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(GroupChatUsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public boolean addGroupChatUsersTable(GroupChatDto groupChatDto) {
        boolean check= isGroupExist(groupChatDto);
        int count= groupChatDto.getUsersId().size();
        if(!check) {
            String sql = "insert into chatting_app.group_chat_users(group_id,user_id)";
            String sql2 = " ,values(?, ?)";
            String sql3=" values(?, ?)";
            for(int i=0 ; i<count-1 ; i++){
                sql3=sql3 + sql2;
            }
            sql= sql +sql3 +";";
            System.out.println(sql);
            return injectContact(groupChatDto , sql);
        }
        else
            return false;
    }
    public boolean isGroupExist(GroupChatDto groupChatDto){
        try {
            String sql = "Select group_id from chatting_app.group_chat_users where group_chat_id=" + groupChatDto.getGroupId() ;
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteGroup(GroupChatDto groupChatDto) {
        try {
            GroupChatDao groupChatDao= new GroupChatDao();
            String sql = "DELETE FROM chatting_app.group_chat_users WHERE (group_id="+ groupChatDto.getGroupId()+");";
            preparedStatement = conn.prepareStatement(sql);
            int affectedRows = preparedStatement.executeUpdate();
            boolean check = groupChatDao.deleteGroupChat(groupChatDto);
            return affectedRows > 0  && check;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Integer> getAllGroupsIdforUser(UserDto userDto) {
        try {
            List<Integer> groupsIds = new ArrayList();
            String sql = "select group_id from chatting_app.group_chat_users where User_ID=" + userDto.getUserID();
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                groupsIds.add(resultSet.getInt(1));
            }
            return groupsIds;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean injectContact(GroupChatDto groupChatDto ,String sql) {
        try {
            int x=0;
            preparedStatement = conn.prepareStatement(sql);
            for (int userId:groupChatDto.getUsersId()) {
                preparedStatement.setInt(1+x, groupChatDto.getGroupId());
                preparedStatement.setInt(2+x, userId);
                x+=2;
            }
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
