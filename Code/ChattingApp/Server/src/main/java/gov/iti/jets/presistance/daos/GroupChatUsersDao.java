package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.GroupChatDto;
import gov.iti.jets.presistance.dtos.UserDto;
import gov.iti.jets.presistance.util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupChatUsersDao {

    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public GroupChatUsersDao() {
    }


    public boolean addGroupChatUsersTable(GroupChatDto groupChatDto) {
        boolean check = isGroupExist(groupChatDto);
        if (!check) {
            try {
                String sql = "insert into chatting_app.group_chat_users(group_id,user_id) values(?, ?)";
                return injectContact(groupChatDto, sql);
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

    public boolean isGroupExist(GroupChatDto groupChatDto) {
        try {
            conn = connector.getConnection();
            String sql = "Select group_id from chatting_app.group_chat_users where group_chat_id=" + groupChatDto.getGroupId();
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

    public boolean deleteGroup(GroupChatDto groupChatDto) {
        try {
            conn = connector.getConnection();
            GroupChatDao groupChatDao = new GroupChatDao();
            String sql = "DELETE FROM chatting_app.group_chat_users WHERE (group_id=" + groupChatDto.getGroupId() + ");";
            preparedStatement = conn.prepareStatement(sql);
            int affectedRows = preparedStatement.executeUpdate();
            boolean check = groupChatDao.deleteGroupChat(groupChatDto);
            return affectedRows > 0 && check;
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

    public ArrayList getAllGroupsIdforUser(UserDto userDto) {
        try {
            conn = connector.getConnection();
            ArrayList groupsIds = new ArrayList();
            String sql = "select group_id from chatting_app.group_chat_users where User_ID=" + userDto.getUserID();
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                groupsIds.add(resultSet.getInt(1));
            }
            return groupsIds;
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
    public List<Integer> getAllUsersIdFromGroupId(int groupId) {
        try {
            conn = connector.getConnection();
            List<Integer> usersList = new ArrayList();
            String sql = "select user_id from chatting_app.group_chat_users where group_ID=" + groupId;
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersList.add(resultSet.getInt(1));
            }
            return usersList;
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
            conn = connector.getConnection();
            int affectedRows = 0;
            for (int userId : groupChatDto.getUsersId()) {
                System.out.println(" " + userId);
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, groupChatDto.getGroupId());
                preparedStatement.setInt(2, userId);
                affectedRows = preparedStatement.executeUpdate();
            }
            return affectedRows > 0;
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
}
