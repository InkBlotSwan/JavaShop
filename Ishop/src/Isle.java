import java.util.*;

public class Isle {
	String description;
	Integer number;
	Integer[] connections;
	List<Product> products = new ArrayList<Product>();
	Random r = new Random();	
	
	public Isle(Integer number, String description) {
		this.description = description;
		this.number = number;
	}
	
	//Get the isle name.
		public String Display() {
			return this.description;
		}
	
	// Add a product to the isle.
	public void AddProduct(Product productToAdd) {
		this.products.add(productToAdd);
	}
	
	// Remove a product from the isle.
	public void RemoveProduct(Product productToRemove) {
		this.products.remove(productToRemove);
	}
	
	// Returns a random product from the isle.
	public Product GetRandomProduct() {
		Product randomProduct;
		randomProduct = products.get(r.nextInt(products.size()));
		return randomProduct; //TODO impliment random POPULATE ISLES.
	}
}
