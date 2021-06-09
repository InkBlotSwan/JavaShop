import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Controller {
	
	// Controller variables.
	Random r = new Random();
	Integer longestQueueSize = 0;
	// Generate initial shop floor.
	public ShopFloor GenerateFloor(Integer tills) {
		
		ShopFloor floor = new ShopFloor(tills);
		
		File file = new File("Isles.txt");
		File productsFile = new File("Products.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
			Integer ID = 0;
			while(sc.hasNext()) {
				floor.AddIsle(ID, sc.next());
				ID++;
			}	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sc = new Scanner(productsFile);
			String name;
			while(sc.hasNext()) {
				name = sc.next();
				floor.AddProduct(Integer.parseInt(name.substring(0, 1)),  new Product(name.substring(1)));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return floor;
	}
	
	// Generate the supplied number of customers.
	public List<Customer> GenerateCustomers(Integer amountOfCustomers, ShopFloor floor) {
		List<Customer> customers = new ArrayList<Customer>();
		for (int i = 0; i < amountOfCustomers; i++) {
			  customers.add(new Customer(i));
			  customers.get(i).arrive(floor.GetIsle(customers.get(i).GetLocation()).Display());
			}
		//Log simulaion start
		try(FileWriter fw = new FileWriter("output.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("NEW SIM START: Cust="+ amountOfCustomers.toString() + " Tills=" + floor.GetIsleCount().toString());
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		return customers;
	}
	
	// Update all the customers, ie, pick up a product or move to another isle.
	public List<Customer> UpdateCustomers(ShopFloor floor, List<Customer> customers, Queue<Customer> checkoutQueue, View view) {
		List<Customer> updatedCustomers = customers;
		
		// Iterate through all customers.
		for (int i = 0; i < updatedCustomers.size(); i++) {
			// Check trolly isn't full
			if(!updatedCustomers.get(i).IsTrollyFull()) {
				if(r.nextInt(2) == 0) {
					updatedCustomers.get(i).TakeProduct(floor);
				}
				// Move to another location if not in the last isle.
				else if(updatedCustomers.get(i).GetLocation()+1 != floor.GetIsleCount()){
					updatedCustomers.get(i).MoveIsle(updatedCustomers.get(i).GetLocation()+1, floor.GetIsle(updatedCustomers.get(i).GetLocation()+1).Display());
				}
				// If in last Isle proceed to checkout.
				else {
					//Remove customer
					checkoutQueue.add(updatedCustomers.get(i));
					updatedCustomers.get(i).enterQueue();
					view.DisplayCustomerQueue(updatedCustomers.get(i));
					updatedCustomers.remove(i);
					break;
				}
			}
			// If trolly is full Proceed to checkout.
			else {
				checkoutQueue.add(updatedCustomers.get(i));
				updatedCustomers.get(i).enterQueue();
				view.DisplayCustomerQueue(updatedCustomers.get(i));
				updatedCustomers.remove(i);
				break;
			}
        }
		
		return updatedCustomers;
	}
	
	// Process the checkOut Queue.
	public void updateQueue(Queue<Customer> checkoutQueue, ShopFloor floor, View view) {
		// Check Queue is not empty before proceeding.
		if(checkoutQueue.size() != 0) {
			//Check queue statistics
			if(checkoutQueue.size() > longestQueueSize) {
				longestQueueSize = checkoutQueue.size();
			}
			
			if(Thread.activeCount() <= floor.GetTillCount()) {
				Till till = new Till();
				till.run(checkoutQueue, view);
			}
		}
	}
	
	//Finish up simulation, an log statistics.
	public void finishSimulation() {
		
		//Logging
		try(FileWriter fw = new FileWriter("output.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("SIM COMPLETE");
			    out.println("Longest queue: " + longestQueueSize.toString());
			} catch (IOException e) {
			}
	}
}

