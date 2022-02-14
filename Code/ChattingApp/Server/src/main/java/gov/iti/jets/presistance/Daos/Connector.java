package gov.iti.jets.presistance.Daos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Connector {
    private Properties prop = new Properties();
    private FileInputStream fis = null;
    private MysqlDataSource mysqlDS = null;
    
    private static Connector connector = new Connector();
    private Connector() {
    }

    public static Connector getInstance() {
        return connector;
    }


    public static void MYSQLDataSource() {
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream("db.properties");
            prop.setProperty("MYSQL_DB_URL", "jdbc:mysql://localhost:3306/iti");
            prop.setProperty("MYSQL_DB_USERNAME", "root");
            prop.setProperty("MYSQL_DB_PASSWORD", "abdelaziz123@!$");
            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public DataSource getMYSQLDataSource() {
        try {
            fis = new FileInputStream("db.properties");
            prop.load(fis);
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(prop.getProperty("MYSQL_DB_URL"));
            mysqlDS.setUser(prop.getProperty("MYSQL_DB_USERNAME"));
            mysqlDS.setPassword(prop.getProperty("MYSQL_DB_PASSWORD"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysqlDS;
    }
}
