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

import gov.iti.jets.presistance.Dtos.MessageDto;

public class MessageDao {

    private DataSource ds = null;
    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public MessageDao() {
        try {
            ds = connector.getMYSQLDataSource();
            conn = ds.getConnection();
            statement = conn.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addMessageDto(MessageDto MessageDto) {
        try {
            String sql = "insert into chatting_app.user values(?, ?, ?)";
            preparedStatement = conn.prepareStatement(sql);
            injectUser(MessageDto);
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
/// is this method important ?? 
    public boolean updateMessageDto(MessageDto MessageDto, int id) {
        try {
            String sql = "UPDATE chatting_app.message SET MESSAGE_ID= ?,CONTENT = ?,FILE = ? WHERE MESSAGE_ID="
                    + id;
            preparedStatement = conn.prepareStatement(sql);
            injectUser(MessageDto);
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

    public boolean deleteMessageDtoById(int id) {
        try {
            String sql = "DELETE FROM chatting_app.message WHERE MESSAGE_ID =" + id;
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

    public MessageDto getMessageDtoById(int id) {
        try {
            String sql = "select * from chatting_app.message where MESSAGE_ID=" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                MessageDto MessageDto = new MessageDto();
                MessageDto = extractUser(resultSet);
                return MessageDto;
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<MessageDto> getAllMessageDtos() {
        List<MessageDto> messageList = new ArrayList<>();
        try {
            String sql = "select * from chatting_app.message";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                MessageDto MessageDto = extractUser(resultSet);
                messageList.add(MessageDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    private void injectUser(MessageDto MessageDto) {
        try {
            preparedStatement.setInt(1, MessageDto.getMessageId());
            preparedStatement.setString(2, MessageDto.getContent());
            preparedStatement.setBinaryStream(3,MessageDto.getFileForUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private MessageDto extractUser(ResultSet resultSet) {
        try {
            MessageDto MessageDto = new MessageDto();
            MessageDto.setMessageId(resultSet.getInt(1));
            MessageDto.setContent(resultSet.getString(2));
            MessageDto.setFileForUser(resultSet.getBinaryStream(3));
            return MessageDto;
        } catch (SQLException e) {
            return null;
        }
    }

}
