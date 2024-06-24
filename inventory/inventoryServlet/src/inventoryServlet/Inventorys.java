package inventoryServlet;

public class Inventorys {
 private int id;
 private String stockName;
 private int stockQuantity;
 private int availabilityQuantity;
 private java.sql.Date dateOfSupply;
private String recentSupplyTrends;
 private String minimumStockTrends;

 
 
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
public int getAvailabilityQuantity() {
	return availabilityQuantity;
}
public void setAvailabilityQuantity(int availabilityQuantity) {
	this.availabilityQuantity = availabilityQuantity;
}

public java.sql.Date getDateOfSupply() {
	return dateOfSupply;
}
public void setDateOfSupply(java.sql.Date date) {
	this.dateOfSupply = date;
}
public String getRecentSupplyTrends() {
	return recentSupplyTrends;
}
public void setRecentSupplyTrends(String recentSupplyTrends) {
	this.recentSupplyTrends = recentSupplyTrends;
}
public String getMinimumStockTrends() {
	return minimumStockTrends;
}
public void setMinimumStockTrends(String minimumStockTrends) {
	this.minimumStockTrends = minimumStockTrends;
}

 
}
