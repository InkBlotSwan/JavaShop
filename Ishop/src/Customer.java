import java.util.*;

public class Customer {
	Integer ID;
	Trolly trolly = new Trolly();
	
	Integer currentIsleID;
	
	String latestAction = "";
	
	//Time variables.
	long start;
	long finish;
	long timeElapsed;
	
	//Constructor for new customer.
	public Customer(Integer ID){
		this.ID = ID;
		currentIsleID = 0;
	}
	
	//Arrive at the first isle
	public void arrive(String name) {
		this.latestAction = "Customer " + this.ID.toString() + ": Arrived at " + name + "Isle";
	}
	
	//Enter Queue
	public void enterQueue() {
		latestAction = "Customer " + this.ID.toString() + ": Entered Queue";
		this.start = System.nanoTime() / 1_000_00;
	}
	
	//Leave Queue
		public void leaveQueue() {
			latestAction = "Customer " + this.ID.toString() + ": Left Queue";
			finish = System.nanoTime() / 1_000_00;
			timeElapsed = finish - start;
		}
	
	//Get time queueing
	public String getTimeQueueing() {
		return "Customer " + this.ID.toString() + ": Spent " + String.valueOf(timeElapsed) + " in queue.";
	}
		
	//Move to another isle. 
	public void MoveIsle(int destination, String name) {
		this.currentIsleID = destination;
		this.latestAction = "Customer " + this.ID.toString() + ": Moved to" + name + "Isle";
	}
	
	public Integer GetLocation() {
		Integer location = currentIsleID;
		
		return location;
	}
	
	//Pick up a product and put it in the trolley.
	public void TakeProduct(ShopFloor floor) {
		Product newProduct = floor.GetIsle(this.currentIsleID).GetRandomProduct();
		trolly.AddItem(newProduct);
		latestAction = "Cust " + this.ID.toString() + ": Took " + newProduct.display();
	}
	
	// Check if the trolly is full.
	public Boolean IsTrollyFull() {
		return this.trolly.IsFull();
	}
	
	public Queue<Product> EmptyTrolly(){
		//UNPACK ITERATE INTO A QUEUE TO GIVE TO TILLS <-- working
		List<Product> products = this.trolly.Unpack();
		Queue<Product> queue = new LinkedList<Product>();
		for (int i = 0; i < products.size(); i++) {
			queue.add(products.get(i));
		}
		return queue;
	}
}
