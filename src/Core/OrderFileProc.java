package Core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Jie Chen (github.com/JChenByte) 
 * github.com/JChenByte/RestaurantPOS
 */
public class OrderFileProc implements Serializable {
	private final Map<String, Entree> entreeList;
	private Map<String, Integer> entreePurchased;
	private int beginId;
	private int endId;
	private double[] stat;

	private static final long serialVersionUID = 1L;
	
	public OrderFileProc(Map<String, Entree> entreeList, int beginId, int endId) {
		this.entreeList = entreeList;
		entreePurchased = new TreeMap<String, Integer>();
		this.beginId = beginId;
		this.endId = endId;

		// Init entreePurchased.
		for (String entree : entreeList.keySet()) {
			entreePurchased.put(entree, 0);
		}

		stat = new double[3];
		stat[0] = 0; // Subtotal
		stat[1] = 0; // Total
		stat[2] = 0; // Tip
		// stat[3] = 0; //Tax

		ArrayList<Thread> threadList = new ArrayList<Thread>();

		for (int n = beginId; n <= endId; n++) {
			String fileName = "order" + n + ".txt";
			Thread worker = new OrderFileWorker(n, stat, entreePurchased);
			worker.start();
			threadList.add(worker);
		}
		for (Thread thread : threadList) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public double getSubtotal() {
		return stat[0];
	}

	public double getTotal() {
		return stat[1];
	}

	public double getTip() {
		return stat[2];
	}

	public String getOrderDetails() {
		String temp = "";

		for (String entree : entreePurchased.keySet()) {
			temp += "Entree Name: " + entree + " Quantity: " + 
		entreePurchased.get(entree) + "\n";
		}

		return temp;
	}

}
