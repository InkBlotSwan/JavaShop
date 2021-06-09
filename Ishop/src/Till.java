import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Till extends Thread {

	public void run(Queue<Customer> checkoutQueue, View view) {
		Queue<Product> itemQueue = checkoutQueue.element().EmptyTrolly();
		
		// Go through all the products, scan them, and unpack them.
		for (int i = 0; i < itemQueue.size(); i++) {
			itemQueue.remove();
		}
		checkoutQueue.element().leaveQueue();
		view.DisplayCustomerQueue(checkoutQueue.element());
		view.DisplayQueueTime(checkoutQueue.element());
		//LoggingResults.
		try(FileWriter fw = new FileWriter("output.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(checkoutQueue.element().getTimeQueueing());
			} catch (IOException e) {
				
			}
		checkoutQueue.remove();
	}
}
