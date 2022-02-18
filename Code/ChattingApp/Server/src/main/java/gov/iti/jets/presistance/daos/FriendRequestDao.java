package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.ContactDto;
import gov.iti.jets.presistance.dtos.FriendRequestDto;
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

public class FriendRequestDao {

    private DataSource ds = null;
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public FriendRequestDao() {
        try {
            conn= connector.getConnection();
        } catch (Exception ex) {
            Logger.getLogger(FriendRequestDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public boolean addFriendRequestDto(FriendRequestDto friendRequestDto) {
        boolean check= isRequestExist(friendRequestDto);
        if(!check) {
            System.out.println(friendRequestDto.getUserId()+friendRequestDto.getFriendId());
            String sql = "insert into chatting_app.friend_request(user_from,user_to)  values(? ,?)";
            boolean firstCheck =injectContact(friendRequestDto.getUserId(),friendRequestDto.getFriendId(), sql);
            return firstCheck ;
        }
        else // we suppose to add the contacts ourselves on this false
            return false;
    }
    public boolean isRequestExist(FriendRequestDto friendRequestDto){
        try {
            String sql = "select * from chatting_app.friend_request where (user_from ="+friendRequestDto.getUserId() +" && user_to ="+friendRequestDto.getFriendId() + ") || (user_from = "+friendRequestDto.getFriendId()+" && user_to ="+friendRequestDto.getUserId()+");";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }
    // Deleting on accepting or rejecting the request
    public boolean deleteFriendRequest(FriendRequestDto friendRequestDto) {
        try {
            String sql = "DELETE FROM chatting_app.friend_request WHERE (user_from="+ friendRequestDto.getUserId() +"&& user_to ="+ friendRequestDto.getFriendId() +") || (user_from ="+ friendRequestDto.getFriendId() +" && user_to="+ friendRequestDto.getUserId() +");";
            preparedStatement = conn.prepareStatement(sql);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean injectContact(int userId, int friendId,String sql) {
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendId);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
