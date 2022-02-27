package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.ContactDto;
import gov.iti.jets.presistance.dtos.FriendRequestDto;
import gov.iti.jets.presistance.util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDao {
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public FriendRequestDao() {
    }


    public int addFriendRequestDto(FriendRequestDto friendRequestDto) {
        boolean check1 = isRequestExist(friendRequestDto);
        if (!check1) {
            boolean check2 = isRequestExistInReverse(friendRequestDto);
            if (!check2) {
                try {
                    conn = connector.getConnection();
                    String sql = "insert into chatting_app.friend_request(user_from,user_to)  values(? ,?)";
                    if (injectContact(friendRequestDto.getUserId(), friendRequestDto.getFriendId(), sql)) {
                        return 1;
                    } else
                        return -1;
                } catch (Exception e) {
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
            } else {
                if (acceptFriendRequestAutomatically(friendRequestDto))
                    return 0;
                else {
                    return -1;
                }
            }
        } else
            return -1;
    }

    private boolean isRequestExistInReverse(FriendRequestDto friendRequestDto) {
        try {
            conn = connector.getConnection();
            String sql = "select * from chatting_app.friend_request where (user_from =" + friendRequestDto.getFriendId() + " && user_to =" + friendRequestDto.getUserId() + ");";
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

    private boolean acceptFriendRequestAutomatically(FriendRequestDto friendRequestDto) {
        ContactDao contactDao = new ContactDao();
        ContactDto contactDto = new ContactDto();
        boolean check;
        contactDto.setUserId(friendRequestDto.getUserId());
        contactDto.setFriendId(friendRequestDto.getFriendId());
        check = contactDao.addFriendDto(contactDto);
        if (check) {
            deleteFriendRequest(friendRequestDto);
        }
        return check;
    }

    public boolean isRequestExist(FriendRequestDto friendRequestDto) {
        try {
            conn = connector.getConnection();
            String sql = "select * from chatting_app.friend_request where (user_from =" + friendRequestDto.getUserId() + " && user_to =" + friendRequestDto.getFriendId() + ");";
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


    public boolean deleteFriendRequest(FriendRequestDto friendRequestDto) {
        try {
            conn = connector.getConnection();
            String sql = "DELETE FROM chatting_app.friend_request WHERE (user_from=" + friendRequestDto.getUserId() + "&& user_to =" + friendRequestDto.getFriendId() + ") || (user_from =" + friendRequestDto.getFriendId() + " && user_to=" + friendRequestDto.getUserId() + ");";
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

    public List<FriendRequestDto> getAllFriendRequestsForUser(int id) {
        List<FriendRequestDto> friendRequestList = new ArrayList<>();
        try {
            conn = connector.getConnection();
            String sql = "select user_from,user_to from chatting_app.friend_request where (user_to =" + id + ");";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                FriendRequestDto friendRequestDto = extractFriendRequest(resultSet);
                friendRequestList.add(friendRequestDto);
            }
            return friendRequestList;
        } catch (SQLException e) {
            return friendRequestList;
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

    private boolean injectContact(int userId, int friendId, String sql) {
        try {
            conn = connector.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendId);
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

    private FriendRequestDto extractFriendRequest(ResultSet resultSet) {
        try {
            conn = connector.getConnection();
            FriendRequestDto friendRequestDto = new FriendRequestDto();
            friendRequestDto.setUserId(resultSet.getInt(1));
            friendRequestDto.setFriendId(resultSet.getInt(2));
            return friendRequestDto;
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
}
