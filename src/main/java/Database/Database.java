package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private Connection connection;

    public Database connect() throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/appliances";
        String DB_USERNAME = "natalia";
        String DB_PASSWORD = "anat2327";
        this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return this;
    }

    public ResultSet execRequest(String query) throws SQLException {
        return connection.prepareStatement(query).executeQuery();
    }

    public int execUpdate(String query) throws SQLException {
        return connection.prepareStatement(query).executeUpdate();
    }

    public void close() throws SQLException {
        connection.close();
    }
}
