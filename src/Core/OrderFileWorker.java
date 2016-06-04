package Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Jie Chen (github.com/JChenByte) 
 * github.com/JChenByte/RestaurantPOS
 */
public class OrderFileWorker extends Thread implements Serializable {
	private Map<String, Integer> localEntreePurchased;
	private Map<String, Integer> entreePurchased;
	private double[] stat;
	private int id;
	private int orderType, orderStatus;
	private String name, address, phoneNum;
	private double taxRate, tip, subtotal, total, finalPrice;

	private static final long serialVersionUID = 1L;

	public OrderFileWorker(int id, double[] stat, Map<String, Integer> 
	entreePurchased) {
		this.id = id;
		this.entreePurchased = entreePurchased;
		this.localEntreePurchased = new TreeMap<String, Integer>();
		this.stat = stat;

	}

	public void run() {
		try {
			FileReader fileReader = new FileReader("order" + id + ".txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Scanner fileScanner = new Scanner(bufferedReader);

			// Order type.
			orderType = fileScanner.nextInt();

			// Order status.
			orderStatus = fileScanner.nextInt();

			// ID.
			id = fileScanner.nextInt();
			fileScanner.nextLine();
			// Name
			name = fileScanner.nextLine();

			// Address
			address = fileScanner.nextLine();

			// Phone
			phoneNum = fileScanner.nextLine();
			// Tax
			taxRate = Double.parseDouble(fileScanner.next());

			// Tip
			tip = Double.parseDouble(fileScanner.next());

			subtotal = Double.parseDouble(fileScanner.next());
			total = Double.parseDouble(fileScanner.next());
			finalPrice = Double.parseDouble(fileScanner.next());

			fileScanner.nextLine();

			// Read all entrees and put them into map.
			while (fileScanner.hasNextLine()) {
				String entreeName = fileScanner.nextLine();
				int quantity = Integer.parseInt(fileScanner.nextLine());
				double sub = Double.parseDouble(fileScanner.nextLine());

				localEntreePurchased.put(entreeName, quantity);

			}
		} catch (Exception e) {
			System.out.println(e);
		}

		synchronized (stat) {
			stat[0] = subtotal + stat[0];
			stat[1] = total + stat[1];
			stat[2] = tip + stat[2];
		}

		synchronized (entreePurchased) {
			for (String entree : localEntreePurchased.keySet()) {
				int preNum = entreePurchased.get(entree);
				entreePurchased.put(entree, preNum + localEntreePurchased.get
						(entree));
			}
		}

	}

}
