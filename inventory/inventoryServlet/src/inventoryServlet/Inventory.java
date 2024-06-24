package inventoryServlet;

public class Inventory {
    private String stockName;
    private int stockQuantity;
    private int availableQuantity;
    private java.util.Date dateOfSupply;
    private String recentSupplyTrends;
    private int minStockLevel;

    // Getter methods
    public String getStockName() {
        return stockName;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public java.util.Date getDateOfSupply() {
        return dateOfSupply;
    }

    public String getRecentSupplyTrends() {
        return recentSupplyTrends;
    }

    public int getMinStockLevel() {
        return minStockLevel;
    }

    // Setter methods
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setDateOfSupply(java.util.Date dateOfSupply) {
        this.dateOfSupply = dateOfSupply;
    }

    public void setRecentSupplyTrends(String recentSupplyTrends) {
        this.recentSupplyTrends = recentSupplyTrends;
    }

    public void setMinStockLevel(int minStockLevel) {
        this.minStockLevel = minStockLevel;
    }
}
