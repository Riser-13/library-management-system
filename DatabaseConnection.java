import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/library_management";

    private static final String USER = "root";

    private static final String PASSWORD =
            System.getenv("LIBRARY_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new SQLException(
                    "Database password environment variable is not configured."
            );
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}