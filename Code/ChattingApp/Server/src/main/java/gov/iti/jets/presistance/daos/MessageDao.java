package gov.iti.jets.presistance.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import gov.iti.jets.presistance.dtos.MessageDto;
import gov.iti.jets.presistance.util.Connector;

public class MessageDao {

    private Connection conn = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    Connector connector = Connector.getInstance();

    public MessageDao() {
        try {
            conn= connector.getConnection();
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
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteMessageDtoById(int id) {
        try {
            String sql = "DELETE FROM chatting_app.message WHERE MESSAGE_ID =" + id;
            preparedStatement = conn.prepareStatement(sql);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
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
                return extractUser(resultSet);
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
