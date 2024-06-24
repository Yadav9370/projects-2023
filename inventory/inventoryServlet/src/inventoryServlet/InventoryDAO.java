package inventoryServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDAO {
    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sdacproject";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "asdfghjkl";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    // SQL insert query
    private static final String INSERT_INVENTORY_SQL = "INSERT INTO Inventorytable (stockName, stockQuantity,availableQuantity, dateOfSupply, recentSupplyTrends, minStockLevel) VALUES (?, ?, ?, ?, ?, ?)";

    // Method to insert inventory data
    public boolean insertInventory(Inventory inventory) throws ClassNotFoundException {
        boolean rowInserted = false;
        try{
        	Class.forName(JDBC_DRIVER);
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVENTORY_SQL)) {
            
            preparedStatement.setString(1, inventory.getStockName());
            preparedStatement.setInt(2, inventory.getStockQuantity());
            preparedStatement.setInt(3,inventory.getAvailableQuantity() );
            preparedStatement.setDate(4,  new java.sql.Date(inventory.getDateOfSupply().getTime()));
            preparedStatement.setString(5, inventory.getRecentSupplyTrends());
           
            preparedStatement.setInt(6, inventory.getMinStockLevel());

            rowInserted = preparedStatement.executeUpdate() > 0;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }
}
