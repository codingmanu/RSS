package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.pool.OracleDataSource;

/**
 * DatabaseConnector class 
 * @author 1-DAM
 */

public class DatabaseConnector {

    static Statement statement = null;
    static Connection connection = null;

    public static void connect() throws SQLException {
        try {
            String jdbcUrl = "jdbc:oracle:thin:@msgomezm.no-ip.org:1521:xe";
            String userid = "RSS";
            String password = "rss";

            OracleDataSource datasource;
            datasource = new OracleDataSource();
            datasource.setURL(jdbcUrl);
            connection = datasource.getConnection(userid, password);
            
            System.out.println("Conectado.");
            
        } catch (SQLException sqlex) {
            System.out.println("Conexión fallida.");
        }
    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Desconectado.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
