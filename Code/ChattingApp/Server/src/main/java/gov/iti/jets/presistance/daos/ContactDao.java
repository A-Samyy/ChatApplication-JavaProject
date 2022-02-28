package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.ContactDto;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.presistance.util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public ContactDao() {

    }


    public boolean addFriendDto(ContactDto contactDto) {
        if (!checkIfUserHasFriend(contactDto)) {
            try {
                conn = connector.getConnection();
                String sql = "insert into chatting_app.contacts(user_id,friend_id,type)  values(?, ? ,?)";
                boolean firstCheck = injectContact(contactDto.getUserId(), contactDto.getFriendId(), contactDto.getType(), sql);
                boolean secondCheck = injectContact(contactDto.getFriendId(), contactDto.getUserId(), contactDto.getType(), sql);
                return firstCheck && secondCheck;
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
        } else {
            return false;
        }
    }

    public boolean checkIfUserHasFriend(ContactDto contactDto) {
        try {
            conn = connector.getConnection();
            String sql = "Select friend_id from chatting_app.contacts where user_id=" + contactDto.getUserId() + "&& friend_id=" + contactDto.getFriendId();
            preparedStatement = conn.prepareStatement(sql);
            return preparedStatement.executeQuery().next();
        } catch (SQLException e) {
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

    public boolean deleteFriend(ContactDto contactDto) {
        try {
            conn = connector.getConnection();
            String sql = "DELETE FROM chatting_app.contacts WHERE (user_id=" + contactDto.getUserId() + "&& friend_id=" + contactDto.getFriendId() + ") || (user_id =" + contactDto.getFriendId() + " &&friend_id=" + contactDto.getUserId() + ");";
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

    public List<UserDto> getAllUserFriendsById(int userId) {
        try {
            conn = connector.getConnection();
            List<Integer> friendsIds = new ArrayList();
            String sql = "select friend_id from chatting_app.contacts where User_ID=" + userId;
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                friendsIds.add(resultSet.getInt(1));
            }
            return mappingListofIdToListofUserDto(friendsIds);
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

    private List<UserDto> mappingListofIdToListofUserDto(List<Integer> friendsIds) {
        UserDao userDao = new UserDao();

        List<UserDto> userDtoList = new ArrayList<>();
        for (int id : friendsIds) {
            UserDto userDto = new UserDto();
            userDto = userDao.getUserDtoById(id);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    private boolean injectContact(int userId, int friendId, String status, String sql) {
        try {
            conn = connector.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendId);
            preparedStatement.setString(3, status);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
