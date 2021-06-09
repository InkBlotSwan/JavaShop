import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Model {
	
	public static void main(String[] args) {
		
		//Simulation variables.
		//PLACEHOLDER ADDING CUSTOMER
		InputStreamReader streamReader = new InputStreamReader(System.in);
	    BufferedReader bufferedReader = new BufferedReader(streamReader);
	    
		Controller controller = new Controller();
		View view = new View();
		List<Customer> customers = new ArrayList<Customer>();
		
		Queue<Customer> checkoutQueue = new LinkedList<Customer>();
		
		//Placeholder MAKE THESE OBJECTS PERMENENCE
		ShopFloor floor = null;
		System.out.println("How many Tills to simulate: ");
		try {
			floor = controller.GenerateFloor(Integer.parseInt(bufferedReader.readLine()));
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		//Starting the simulation
		System.out.println("How many customers to simulate: ");
		try {
			customers = controller.GenerateCustomers(Integer.parseInt(bufferedReader.readLine()), floor);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		view.DisplayCustomerActions(customers);
		
		// Logic code.
		Boolean floorEmpty = false;
		Boolean queueEmpty = true;
		while(!floorEmpty || !queueEmpty) {
			controller.UpdateCustomers(floor, customers, checkoutQueue, view);
			view.DisplayCustomerActions(customers);
			controller.updateQueue(checkoutQueue, floor, view);
			// Output code.
			
			
			if(customers.size() == 0) {
				floorEmpty = true;
			}
			if(checkoutQueue.size() == 0) {
				queueEmpty = true;
			}
			else {
				queueEmpty = false;
			}
			
		}
		
		controller.finishSimulation();
	}

}