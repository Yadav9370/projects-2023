package inventoryServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpDAO {
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sdacproject";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "asdfghjkl";

    // MySQL Connector/J driver class name
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // Insert query to add a new user to the database
    private static final String INSERT_USER_SQL = "INSERT INTO signsup (username, email, password) VALUES (?, ?, ?)";

    // Method to insert a new user into the database
    public boolean addUser(SignUpPojo user) throws SQLException {
        boolean rowInserted = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            // Load the MySQL Connector/J driver
            Class.forName(JDBC_DRIVER);
            
            // Establish a connection to the database
            connection = (Connection) DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            
            // Prepare the insert statement
            preparedStatement = ((java.sql.Connection) connection).prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            // Execute the insert statement and check if a row was inserted
            rowInserted = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the connection and statement
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return rowInserted;
    }
}
