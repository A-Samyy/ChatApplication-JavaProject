package gov.iti.jets.presistance.daos;

import gov.iti.jets.presistance.dtos.AdminDto;
import gov.iti.jets.presistance.util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public AdminDao() {
    }


    public boolean addAdminDto(AdminDto adminDto) { // Email was not tested
        boolean check = checkAdminId(adminDto.getAdminID());
        if (!check) {
            try {
                String sql = "insert into chatting_app.admin(admin_id, admin_NAME, admin_PASSWORD)  values(?, ?, ?)";
                return injectAdmin(adminDto, sql);
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

    public boolean checkAdminId(int id) {
        try {
            conn = connector.getConnection();
            String sql = "Select * from chatting_app.admin where admin_id ="+ id + ";" ;
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

    public boolean updateAdminDto(AdminDto adminDto) {
        try {
            conn = connector.getConnection();
            String sql = "UPDATE chatting_app.admin SET admin_id= ?,admin_NAME = ?,admin_PASSWORD = ? WHERE User_ID="
                    + adminDto.getAdminID();
            return injectAdmin(adminDto, sql);
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

    public boolean deleteAdminDtoById(int id) {
        try {
            conn = connector.getConnection();
            String sql = "DELETE FROM chatting_app.admin WHERE admin_id=" + id;
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

    public AdminDto getAdminDtoById(int id) {
        try {
            conn = connector.getConnection();
            AdminDto adminDto;
            String sql = "select * from chatting_app.admin where admin_ID=" + id;
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                adminDto = extractAdmin(resultSet);
                return adminDto;
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

    public boolean checkAdminPassword(AdminDto adminDto) {
        try {
            conn = connector.getConnection();
            String sql = "Select admin_password from chatting_app.admin where lower(admin_password) = lower(trim('" + adminDto.getPassword() + "')); ";
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
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

    private boolean injectAdmin(AdminDto adminDto, String sql) {
        try {
            conn = connector.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, adminDto.getAdminID());
            preparedStatement.setString(2, adminDto.getAdminName());
            preparedStatement.setString(3, adminDto.getPassword());
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

    private AdminDto extractAdmin(ResultSet resultSet) {
        try {
            conn= connector.getConnection();
            AdminDto adminDto =new AdminDto();
            adminDto.setAdminID(resultSet.getInt(1));
            adminDto.setAdminName(resultSet.getString(2));
            adminDto.setPassword(resultSet.getString(3));
            return adminDto;
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
