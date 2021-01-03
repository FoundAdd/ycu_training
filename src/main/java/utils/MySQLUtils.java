package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLUtils {
    private static final String driver = "com.mysql.jdbc.Driver";
//    private static final String url = "jdbc:mysql://121.196.219.111:3306/javaGui";
    private static final String url = "jdbc:mysql://localhost:3306/javaGui";
    private static final String username = "root";
    private static final String password = "sa";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }

    public static Statement getStatement(Connection conn) {
        Statement stmt = null;

        if (conn != null) {
            try {
                stmt = conn.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return stmt;
    }
}
