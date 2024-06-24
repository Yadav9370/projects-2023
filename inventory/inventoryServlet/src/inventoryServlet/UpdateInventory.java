package inventoryServlet;

import java.util.Date;

public class UpdateInventory {
    private int id;
    private String stockName;
    private int stockQuantity;
    private int availableQuantity;
    private Date dateOfSupply;
    private String recentSupplyTrends;
    private int minimumStockLevel;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getStockName() {
        return stockName;
    }
    
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public int getAvailableQuantity() {
        return availableQuantity;
    }
    
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
    
    public void setDateOfSupply(Date dateOfSupply) {
        this.dateOfSupply = dateOfSupply;
    }
    
    public Date getDateOfSupply() {
        return dateOfSupply;
    }
    
    public String getRecentSupplyTrends() {
        return recentSupplyTrends;
    }
    
    public void setRecentSupplyTrends(String recentSupplyTrends) {
        this.recentSupplyTrends = recentSupplyTrends;
    }
    
    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }
    
    public void setMinimumStockLevel(int minimumStockLevel) {
        this.minimumStockLevel = minimumStockLevel;
    }
}
