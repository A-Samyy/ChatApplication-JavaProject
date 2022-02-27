package gov.iti.jets.presistance.util;

import java.io.*;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Connector {
    private static HikariDataSource dataSource;
    private static FileInputStream fis = null;
    private static Properties prop = new Properties();
    static {
        HikariConfig config = new HikariConfig();
        try {
            fis = new FileInputStream("db.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setJdbcUrl(prop.getProperty("MYSQL_DB_URL"));
        config.setUsername(prop.getProperty("MYSQL_DB_USERNAME"));
        config.setPassword(prop.getProperty("MYSQL_DB_PASSWORD"));
        dataSource = new HikariDataSource(config);
        dataSource.setMaximumPoolSize(100);

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
