package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.Status;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.presistance.util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public UserDao() {
    }


    public boolean addUserDto(UserDto userDto) { // Email was not tested
        boolean check1 = checkUserByPhoneNumber(userDto.getPhoneNumber());
        boolean check2 = checkUserbyMail(userDto.getEmail());
        if (!check1 && !check2) {
            try {
                String sql = "insert into chatting_app.user(PHONE_NUMBER, USER_NAME, PASSWORD, GENDER, EMAIL, Picture, COUNTRY, Bio, STATUS, DateOfBirth)  values(?, ?, ?, ? ,?, ? ,? ,?, ? ,?)";
                return injectUser(userDto, sql);
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
        } else
            return false;
    }

    private boolean checkUserbyMail(String email) {
        try {
            conn = connector.getConnection();
            String sql = "Select * from chatting_app.user where lower(EMAIL) = lower(trim('" + email + "')); ";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    public boolean updateUserDto(UserDto userDto) {
        try {
            conn = connector.getConnection();
//            int id = getUserIdByPhoneNumber(userDto.getPhoneNumber());
            String sql = "UPDATE chatting_app.user SET PHONE_NUMBER= ?,USER_NAME = ?,PASSWORD = ? ,GENDER = ? ,EMAIL = ? ,Picture = ? ,COUNTRY = ?,Bio = ? ,STATUS=? ,DateOfBirth=? WHERE User_ID="
                    + userDto.getUserID();
            return injectUser(userDto, sql);
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

    public boolean deleteUserDtoById(int id) {
        try {
            conn = connector.getConnection();
            String sql = "DELETE FROM chatting_app.user WHERE User_ID=" + id;
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

    public UserDto getUserDtoById(int id) {
        try {
            conn = connector.getConnection();
            UserDto userDto;
            String sql = "select * from chatting_app.user where User_ID=" + id;
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userDto = extractUser(resultSet);
                return userDto;
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
    public int getNumberofMaleUsers(){
        try {
            conn = connector.getConnection();
            String sql ="select count(User_ID) from chatting_app.user where gender ='Male';";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else
                return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
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

    public int getNumberofFemaleUsers(){
        try {
            conn = connector.getConnection();
            String sql ="select count(User_ID) from chatting_app.user where gender ='Female';";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else
                return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
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
    public int getUserIdByPhoneNumber(String phone_number) {
        try {
            conn = connector.getConnection();
            String sql = "select USER_ID from chatting_app.user where PHONE_NUMBER=" + phone_number;
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else
                return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
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

    public String getUserPasswordById(int id) {
        try {
            conn = connector.getConnection();
            String sql = "select PASSWORD from chatting_app.user where User_ID=" + id;
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
    public String getUserNameById(int id) {
        try {
            conn = connector.getConnection();
            String sql = "select USER_NAME from chatting_app.user where User_ID=" + id;
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

    public List<UserDto> getAllUser() {
        List<UserDto> userList = new ArrayList<>();
        try {
            conn = connector.getConnection();
            String sql = "select * from chatting_app.user";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserDto userDto = extractUser(resultSet);
                userList.add(userDto);
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
            return userList;
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

    public boolean checkUserByPhoneNumber(String id) {
        try {
            conn = connector.getConnection();
            String sql = "Select * from chatting_app.user where phone_number=" + id;
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

    private boolean injectUser(UserDto userDto, String sql) {
        try {
            conn = connector.getConnection();
            preparedStatement = conn.prepareStatement(sql);
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

    private UserDto extractUser(ResultSet resultSet) {
        try {
            conn= connector.getConnection();
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

    private java.sql.Date convertUtilToSql(java.util.Date uDate) {
        if (uDate != null) {
            java.sql.Date sDate = new java.sql.Date(uDate.getTime());
            return sDate;
        } else
            return null;
    }


}
