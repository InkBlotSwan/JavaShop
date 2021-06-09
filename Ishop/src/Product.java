
public class Product {
	String name;
	Integer price;
	Integer quantity;
	
	//Constructor for initialising product.
	public Product(String name) {
		this.name = name;		
	}
	
	public String display() {
		String output = "";
		output += this.name;
		return output;
	}
}
