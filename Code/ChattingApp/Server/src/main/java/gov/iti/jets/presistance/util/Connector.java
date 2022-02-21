package gov.iti.jets.presistance.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Connector {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config =new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3307/chatting_app");
        config.setUsername("root");
        config.setPassword("hend1234");
        dataSource= new HikariDataSource( config );
        dataSource.setMaximumPoolSize(10);
    }

    private static Connector connector = new Connector();
    private Connector() {
    }

    public static Connector getInstance() {
        return connector;
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void endConnection() {
        dataSource.close();
    }
}
