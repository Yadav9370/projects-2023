package inventoryServlet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteInventory {
    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sdacproject";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "asdfghjkl";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    // SQL delete query
    private static final String DELETE_INVENTORY_SQL = "DELETE FROM Inventorytable WHERE id = ? AND stockName = ?";

    // Method to delete inventory data by ID and name
    public boolean deleteInventory(int id, String stockName) throws ClassNotFoundException {
        boolean rowDeleted = false;
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVENTORY_SQL)) {
                
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, stockName);

                rowDeleted = preparedStatement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
