package gov.iti.jets.presistance.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

import gov.iti.jets.presistance.Dtos.Status;
import gov.iti.jets.presistance.Dtos.UserDto;

public class UserDao {

    private DataSource ds = null;
    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public UserDao() {
        try {
            ds = connector.getMYSQLDataSource();
            conn = ds.getConnection();
            statement = conn.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addUserDto(UserDto userDto) {
        try {
            String sql = "insert into chatting_app.user(PHONE_NUMBER, USER_NAME, PASSWORD, GENDER, EMAIL, Picture, COUNTRY, Bio, STATUS, DateOfBirth)  values(?, ?, ?, ?, ? ,?, ? ,? ,?, ? ,?)";
            preparedStatement = conn.prepareStatement(sql);
            injectUser(userDto);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return true;
            } else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserDto(UserDto userDto, int id) {
        try {
            String sql = "UPDATE chatting_app.user SET PHONE_NUMBER= ?,USER_NAME = ?,PASSWORD = ? ,GENDER = ? ,EMAIL = ? ,Picture = ? ,COUNTRY = ?,Bio = ? ,STATUS=? ,DateOfBirth=? WHERE User_ID="
                    + id;
            preparedStatement = conn.prepareStatement(sql);
            injectUser(userDto);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return true;
            } else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteUserDtoById(int id) {

        try {
            String sql = "DELETE FROM chatting_app.user WHERE User_ID=" + id;
            int affectedRows = preparedStatement.executeUpdate(sql);
            if (affectedRows > 0) {
                return true;
            } else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public UserDto getUserDtoById(int id) {
        try {
            String sql = "select * from chatting_app.user where User_ID=" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                UserDto userDto = new UserDto();
                userDto = extractUser(resultSet);
                return userDto;
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int getUserIdByPhoneNumber(String phone_number) {
        try {
            String sql = "select USER_ID from chatting_app.user where PHONE_NUMBER=" + phone_number;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
               int number= resultSet.getInt(1);
                return number;
            } else
                return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public String getUserPasswordById(int id) {
        try {
            String sql = "select PASSWORD from chatting_app.user where User_ID=" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String password = new String(resultSet.getString(3));
                return password;
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserDto> getAllUser() {
        List<UserDto> userList = new ArrayList<>();
        try {
            String sql = "select * from chatting_app.user";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                UserDto userDto = extractUser(resultSet);
                userList.add(userDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean checkUserByPhoneNumber(String id) {
        try {
            String sql = "Select * from chatting_app.user where phone_number=" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return true;
            } else
                return false;
        } catch (SQLException e) {
            return false;
        }
    }

    private void injectUser(UserDto userDto) {
        try {
            preparedStatement.setString(1, userDto.getPhoneNumber());
            preparedStatement.setString(2, userDto.getName());
            preparedStatement.setString(3, userDto.getPassword());
            preparedStatement.setString(4, userDto.getGender());
            preparedStatement.setString(5, userDto.getEmail());
            preparedStatement.setString(6, userDto.getPicture());
            preparedStatement.setString(7, userDto.getCountry());
            preparedStatement.setString(8, userDto.getBio());
            preparedStatement.setInt(9, userDto.getStatusNumber());
            preparedStatement.setDate(10, convertUtilToSql(userDto.getDateOfBirth()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UserDto extractUser(ResultSet resultSet) {
        try {
            UserDto userDto = new UserDto();
            userDto.setUserID(resultSet.getInt(1));
            userDto.setPhoneNumber(resultSet.getString(2));
            userDto.setName(resultSet.getString(3));
            userDto.setPassword(resultSet.getString(4));
            userDto.setGender(resultSet.getString(5));
            userDto.setEmail(resultSet.getString(6));
            userDto.setPicture(resultSet.getString(7));
            userDto.setCountry(resultSet.getString(8));
            userDto.setBio(resultSet.getString(9));
            userDto.setStatus(Status.getStatusFromNumber(resultSet.getInt(10)));
            userDto.setDateOfBirth(resultSet.getDate(11));
            return userDto;
        } catch (SQLException e) {
            return null;
        }
    }

    private java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    public void testDataSource() {
        DataSource ds = null;
        ds = connector.getMYSQLDataSource();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select phone_number from chatting_app.user");
            while (rs.next()) {

                System.out.println("ID:" + rs.getString(1)); 
           //     + " Name: " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
