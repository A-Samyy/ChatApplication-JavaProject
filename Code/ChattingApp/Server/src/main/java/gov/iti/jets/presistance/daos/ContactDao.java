package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.ContactDto;
import gov.iti.jets.presistance.dtos.Status;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.presistance.util.Connector;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactDao {

    //    private DataSource ds = null;
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public ContactDao() {
        try {
            conn= connector.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(ContactDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public boolean addFriendDto(ContactDto contactDto) {
        boolean check= checkIfUserHasFriend(contactDto);
        if(!check) {
            System.out.println(contactDto.getUserId()+contactDto.getFriendId());
            String sql = "insert into chatting_app.contacts(user_id,friend_id,type)  values(?, ? ,?)";
            boolean firstCheck =injectContact(contactDto.getUserId(),contactDto.getFriendId(),contactDto.getType(), sql);
            boolean secondCheck =injectContact(contactDto.getFriendId(),contactDto.getUserId(),contactDto.getType(), sql);
            return firstCheck && secondCheck ;
        }
        else
            return false;
    }
    public boolean checkIfUserHasFriend(ContactDto contactDto){
        try {
            String sql = "Select friend_id from chatting_app.contacts where user_id=" + contactDto.getUserId() +"&& friend_id="+ contactDto.getFriendId();
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteFriend(ContactDto contactDto) {
        try {
            String sql = "DELETE FROM chatting_app.contacts WHERE (user_id="+ contactDto.getUserId() +"&& friend_id="+ contactDto.getFriendId() +") || (user_id ="+ contactDto.getFriendId() +" &&friend_id="+ contactDto.getUserId() +");";
            preparedStatement = conn.prepareStatement(sql);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UserDto> getAllUserFriendsById(int userId) {
        try {
            UserDao userDao= new UserDao();
            UserDto userDto= new UserDto();
            List<UserDto> userDtoList = new ArrayList<>();
            List<Integer> friendsIds = new ArrayList();
            String sql = "select friend_id from chatting_app.contacts where User_ID=" + userId;
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
             while(resultSet.next()) {
                friendsIds.add(resultSet.getInt(1));
            }
            System.out.println(friendsIds.toString());
            for(int id : friendsIds){
                userDto= new UserDto();
                userDto=userDao.getUserDtoById(id);
                userDtoList.add(userDto);
             }
             return userDtoList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean injectContact(int userId, int friendId, String status ,String sql) {
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendId);
            preparedStatement.setString(3, status);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
