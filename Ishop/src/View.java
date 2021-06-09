import java.util.List;

public class View {

	//Display customers walking round store and picking up items.
	public void DisplayCustomerActions(List<Customer> customers) {
		
		for (int i = 0; i < customers.size(); i++) {
			System.out.println(customers.get(i).latestAction);
		}
	}
	
	//Display customers entering/leaving queue.
	public void DisplayCustomerQueue(Customer customer) {
		System.out.println(customer.latestAction);
	}
	
	//Display queue time.
	public void DisplayQueueTime(Customer customer) {
		System.out.println(customer.getTimeQueueing());
	}
}
