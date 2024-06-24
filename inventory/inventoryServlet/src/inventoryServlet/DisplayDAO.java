package inventoryServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisplayDAO {
 // Update these values with your actual database connection details
 private static final String DB_URL = "jdbc:mysql://localhost:3306/sdacproject";
 private static final String DB_USER = "root";
 private static final String DB_PASSWORD = "asdfghjkl";

 static {
     try {
         Class.forName("com.mysql.jdbc.Driver");
     } catch (ClassNotFoundException e) {
         e.printStackTrace();
         // Handle exception
     }
 }

 private Connection getConnection() throws SQLException {
     return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
 }
 
 public List<Inventorys> getAllItems() {
     List<Inventorys> items = new ArrayList<>();
     try (Connection conn = getConnection();
          PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventorytable");
          ResultSet rs = stmt.executeQuery()) {
         
         while (rs.next()) {
             Inventorys item = new Inventorys();
             item.setId(rs.getInt("id"));
             item.setStockName(rs.getString("stockName"));
             item.setStockQuantity(rs.getInt("stockQuantity"));
             item.setAvailabilityQuantity(rs.getInt("availableQuantity"));
             item.setDateOfSupply(rs.getDate("dateofSupply"));
             item.setRecentSupplyTrends(rs.getString("recentSupplyTrends"));
             item.setMinimumStockTrends(rs.getString("minStockLevel"));
             items.add(item);
         }
     } catch (SQLException e) {
         e.printStackTrace();
         // Handle exception
     }
     return items;
 }
 
 public void refreshItem(String itemId) {
     try (Connection conn = getConnection();
          PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventorytable WHERE id = ?");
          PreparedStatement supplyStmt = conn.prepareStatement("SELECT stockQuantity FROM inventorytable WHERE id = ? ORDER BY dateofSupply DESC LIMIT 1");
          PreparedStatement updateStmt = conn.prepareStatement("UPDATE inventorytable SET stockQuantity = ? WHERE id = ?")) {
         
         // Retrieve the item from the database
         stmt.setString(1, itemId);
         try (ResultSet rs = stmt.executeQuery()) {
             if (rs.next()) {
                 int currentStockQuantity = rs.getInt("StockQuantity");
                 
                 // Retrieve the most recent supply quantity for the item
                 supplyStmt.setString(1, itemId);
                 try (ResultSet supplyRs = supplyStmt.executeQuery()) {
                     if (supplyRs.next()) {
                         int newStockQuantity = supplyRs.getInt("Stockquantity");
                         
                         // Calculate new stock quantity
                        
                         
                         // Update the item's stock quantity in the database
                         updateStmt.setInt(1, newStockQuantity);
                         updateStmt.setString(2, itemId);
                         updateStmt.executeUpdate();
                         
                         // Optionally, log the refresh action
                         System.out.println("Item with ID " + itemId + " refreshed. New stock quantity: " + newStockQuantity);
                     } else {
                         // Handle case when no supply record found for the item
                         System.err.println("No supply record found for item with ID " + itemId);
                     }
                 }
             } else {
                 // Handle case when item with given ID is not found
                 System.err.println("Item with ID " + itemId + " not found");
             }
         }
     } catch (SQLException e) {
         e.printStackTrace();
         // Handle exception
     }
 }
}
