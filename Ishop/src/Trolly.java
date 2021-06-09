import java.util.ArrayList;
import java.util.List;

public class Trolly {
	List<Product> products = new ArrayList<Product>();
	Integer capacity = 5;
	Boolean full = false;
	
	// Add a product to the trolly.
	public void AddItem(Product item) {
		if(!full) {
			this.products.add(item);
			if(products.size() == capacity) {
				full = true;
			}
		}
	}
	
	// Remove an item from the trolly.
	public void RemoveItem(Product item) {
		this.products.remove(item);
		if(full) {
			full = false;
		}
	}
	
	// Check if the trolly is full.
	public Boolean IsFull() {
		return full;
	}
	
	public List<Product> Unpack(){
		return this.products;
	}
}
