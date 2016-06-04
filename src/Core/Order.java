package Core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jie Chen (github.com/JChenByte) 
 * github.com/JChenByte/RestaurantPOS
 */
public class Order implements Serializable {
	private int orderType;
	private int orderStatus;
	private int id;
	private String name;
	private String address;
	private String phoneNum;
	private double taxRate;
	private double tip;
	private Map<Entree, Integer> entreeList;

	private static final long serialVersionUID = 1L;

	public Order(String name, String address, String phoneNum, int orderType, 
			int id, double taxRate) {
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.orderType = orderType;
		this.id = id;
		this.taxRate = taxRate;
		orderStatus = 1;
		tip = 0;
		entreeList = new HashMap<Entree, Integer>();
	}

	public Order(int orderType, int id, double taxRate) {
		this(null, null, null, orderType, id, taxRate);
	}

	/**
	 * Add entree to the order (can be multiple).
	 * 
	 * @param entree
	 * @param numOfEntree
	 */
	public void addEntree(Entree entree, int numOfEntree) {
		/* If entree already been ordered before. */
		if (entreeList.containsKey(entree)) {
			numOfEntree += entreeList.get(entree);
		}

		entreeList.put(entree, numOfEntree);
	}

	/**
	 * Remove an entree from the order.
	 * 
	 * @param entree
	 * @return
	 */
	public boolean removeEntree(Entree entree) {
		if (entreeList.containsKey(entree)) {
			if (entreeList.get(entree) >= 2) {
				int numOfEntree = entreeList.get(entree) - 1;
				entreeList.put(entree, numOfEntree);
				return true;
			} else {
				entreeList.remove(entree);
				return true;
			}

		} else {
			return false;
		}

	}

	/**
	 * Add Tips.
	 */
	public void addTip(double tip) {
		this.tip += tip;
	}

	/**
	 * Change the status of the order to open.
	 */
	public void changeToOpen() {
		orderStatus = 1;
	}

	/**
	 * Change the status of the order to paid.
	 */
	public void changeToPaid() {
		orderStatus = 2;
	}

	/**
	 * Change the status of the order to delivered. (Only worked with orderType
	 * 3)
	 */
	public void changeToDelivered() {
		if (orderType == 3) {
			orderStatus = 3;
		} else {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * @return the total price for this order. (Excluding tip & tax)
	 */
	public double subtotal() {
		double total = 0;
		for (Entree entree : entreeList.keySet()) {
			// Increase the total by price*quantity.
			total += (entree.getPrice() * entreeList.get(entree));
		}

		return total;
	}

	/**
	 * @return the total price for this order. (Excluding tip)
	 */
	public double total() {

		return subtotal() * (1 + taxRate);
	}

	/**
	 * @return the final price customer has to pay. (Including tip)
	 */
	public double finalPrice() {
		return this.total() + tip;
	}

	public String getReceipt() {
		String temp = "";
		temp += "Customer Name: " + name + "\nPhone Number: " + phoneNum + 
				"\nAddress: " + address + "\n";
		temp += "Order ID:" + id + ". \nOrder Type: ";

		switch (orderType) {
		case 1:
			temp += "Dine In \n";
			break;
		case 2:
			temp += "Carry Out \n";
			break;
		case 3:
			temp += "Delivery \n";
			break;
		default:
			break;
		}

		switch (orderStatus) {
		case 1:
			temp += "Order Status: Open \n";
			break;
		case 2:
			temp += "Order Status: Paid \n";
			break;
		case 3:
			temp += "Order Status: Delivered \n";
			break;
		default:
			break;
		}

		temp += "\nOrder List: \n";

		for (Entree entree : entreeList.keySet()) {
			temp += entree.getName() + " x " + entreeList.get(entree) + "\t"
					+ NumberFormat.getCurrencyInstance().format(entree.
							getPrice()) + "\n";
		}
		temp += "\nSubtotal: " + NumberFormat.getCurrencyInstance().format
				(this.subtotal()) + "\nTax ("
				+ (taxRate * 100) + "%): " + NumberFormat.getCurrencyInstance().
				format(this.subtotal() * taxRate)
				+ "\nTotal: " + NumberFormat.getCurrencyInstance().format(this.
						total());

		temp += "\nTip: " + NumberFormat.getCurrencyInstance().format(tip) + 
				"\nYou Pay: "
				+ NumberFormat.getCurrencyInstance().format(this.finalPrice());

		return temp;
	}

	public int getOrderType() {
		return orderType;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public int getId() {
		return id;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public double getTip() {
		return tip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void saveToFile() {

		try {
			/* Try with an invalid filename */
			String filename = "order" + id + ".txt";
			boolean append = false;
			FileWriter fileWriter = new FileWriter(filename, append);

			fileWriter.write(orderType + " " + orderStatus + " " + id + "\n" + 
			name + "\n" + address + "\n" + phoneNum
					+ "\n" + taxRate + " " + tip + "\n");
			fileWriter.write(subtotal() + " " + total() + " " + finalPrice() +
					"\n");
			for (Entree entree : entreeList.keySet()) {
				fileWriter.write(entree.getName() + "\n");
				fileWriter.write(entreeList.get(entree) + "\n");
				fileWriter.write(entree.getPrice() + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
