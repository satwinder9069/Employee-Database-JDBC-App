import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/employee_db";
    private final static String USERNAME = "root";
    private final static String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch (ClassNotFoundException e) {
            throw new SQLException("MYSQL JDBC Driver not found! :) ");
        }
    }
}
