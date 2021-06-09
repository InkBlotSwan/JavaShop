import java.util.*;

public class ShopFloor {
	List<Isle> isles = new ArrayList<Isle>();
	Integer numberOfTills = 0;
	private Integer isleCount = 0;
	
	public ShopFloor(Integer tills) {
		this.isleCount = 0;
		this.numberOfTills = tills;
	}
	
	// Add an isle to the shop floor.
	public void AddIsle(Integer isleID, String isleDescription) {
		this.isles.add(new Isle(isleID, isleDescription));
		isleCount += 1;
	}
	
	// Add a product to an isle.
	public void AddProduct(Integer isleID, Product newProduct) {
		this.isles.get(isleID).AddProduct(newProduct);
	}
	
	// Gets a reference to the requested isle.
	public Isle GetIsle(Integer ID) {
		Isle currentIsle = this.isles.get(ID);
		return currentIsle;
	}
	
	public Integer GetIsleCount() {
		return this.isleCount;
	}
	
	public Integer GetTillCount() {
		return this.numberOfTills;
	}
}
