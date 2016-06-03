package Core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jie Chen (github.com/JChenByte) 
 * github.com/JChenByte/RestaurantPOS
 */
public class Restaurant {
	private String resturantName;
	private double taxRate;
	private int currentOrderId;
	private Map<String, Ingredient> inventory;
	private Map<String, Employee> employeeList;
	private Map<Integer, Order> orders;
	private Map<String, EntreeCate> entreeList;

	/**
	 * @param resturantName
	 */
	public Restaurant(String resturantName, double taxRate) {
		this.resturantName = resturantName;
		this.taxRate = taxRate;
		currentOrderId = 0;
		inventory = new HashMap<String, Ingredient>();
		employeeList = new HashMap<String, Employee>();
		orders = new HashMap<Integer, Order>();
		entreeList = new HashMap<String, EntreeCate>();
	}

	/**
	 * Add an employee to the employeeList.
	 * 
	 * @param name
	 * @param password
	 *            plain-text password.
	 * @param isManager
	 */
	public void addEmployee(String name, String password, boolean isManager) {
		employeeList.put(name, new Employee(name, MD5Hash.hashing(password), 
				isManager));
	}

	/**
	 * @param name
	 * @param password
	 * @return true if the password is correct.
	 */
	public boolean verifyPasword(String name, String password) {
		return employeeList.get(name).verifyPassword(MD5Hash.hashing(password));
	}

	/**
	 * Change Employee's password.
	 * 
	 * @param name
	 * @param newPassword
	 */
	public void changePassword(String name, String newPassword) {
		employeeList.get(name).setPassword(MD5Hash.hashing(newPassword));
	}

	/**
	 * @return a list of employee.
	 */
	public String getEmployeeList() {
		String temp = "Employee List:\n";
		for (Employee employee : employeeList.values()) {
			temp += employee.toString() + "\n";
		}

		return temp;
	}

	/**
	 * Set an employee as manager.
	 * 
	 * @param name
	 */
	public void setManager(String name) {
		employeeList.get(name).setManager(true);
	}

	/**
	 * Remove an employee from manager.
	 * 
	 * @param name
	 */
	public void removeManager(String name) {
		employeeList.get(name).setManager(false);
	}

	/**
	 * Create a new ingredient and add it to the inventory. If ingredient
	 * already exists, add the amount to the existing ingredient object.
	 * 
	 * @param name
	 * @param amount
	 * @param unit
	 */
	public void newIngredient(String name, double amount, String unit) {
		if (inventory.containsKey(name)) {
			inventory.get(name).incAmount(amount);
		} else {
			inventory.put(name, new Ingredient(name, amount, unit));
		}
	}

	/**
	 * @param name
	 * @param amount
	 * @return true if ingredient exists, false otherwise.
	 */
	public boolean replenishInventory(String name, double amount) {
		if (inventory.containsKey(name)) {
			inventory.get(name).incAmount(amount);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param name
	 * @param amount
	 * @return true if ingredient exists, false otherwise.
	 */
	public boolean reduceInventory(String name, double amount) {
		if (inventory.containsKey(name)) {
			inventory.get(name).decAmount(amount);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return number of open orders.
	 */
	public int numOfOrders() {
		return orders.size();
	}

	/**
	 * Create a new category.
	 * 
	 * @param name
	 */
	public void addCate(String name) {
		entreeList.put(name, new EntreeCate(name));
	}

	public void addEntree(String categoryName, String entreeName, double price) {
		if (!(entreeList.containsKey(categoryName))) {
			this.addCate(categoryName);
		}

		entreeList.get(categoryName).addEntree(entreeName, price);
	}

	/**
	 * Remove every entree with the same name under all categories.
	 * 
	 * @param entreeName
	 */
	public void removeEntree(String entreeName) {
		/*
		 * Iterate through all category and remove all entrees with same name.
		 */
		for (EntreeCate category : entreeList.values()) {
			category.removeEntree(entreeName);
		}
	}

	/**
	 * Add an ingredient into an entree.
	 * 
	 * @param category
	 * @param entreeName
	 * @param IngredientName
	 * @param quantity
	 */
	public void addIngredientToEntree(String category, String entreeName, 
			String IngredientName, double quantity) {
		entreeList.get(category).addIngredient(entreeName, IngredientName, 
				quantity);
	}

	/**
	 * Remove an ingredient from an entree.
	 * 
	 * @param category
	 * @param entreeName
	 * @param IngredientName
	 */
	public void removeIngredientFromEntree(String category, String entreeName, 
			String IngredientName) {
		entreeList.get(category).removeIngredient(entreeName, IngredientName);
	}

	/**
	 * Adjust an entree's price from a specific category.
	 * 
	 * @param category
	 * @param entreeName
	 * @param price
	 */
	public void adjustEntreePrice(String category, String entreeName, double 
			price) {
		entreeList.get(category).adjustPrice(entreeName, price);
	}

	/**
	 * Return a menu sting.
	 * 
	 * @return string.
	 */
	public String getMenu() {
		String temp = resturantName + "\n";
		for (EntreeCate cate : entreeList.values()) {
			temp += cate.toString() + "\n";
		}
		return temp;
	}

	/**
	 * Create a new dine-in order.
	 */
	public void newDineInOrder() {
		orders.put(++currentOrderId, new Order(1, currentOrderId, taxRate));
	}

	/**
	 * Create a new carry-out order.
	 */
	public void newCarryOutOrder() {
		orders.put(++currentOrderId, new Order(2, currentOrderId, taxRate));
	}

	/**
	 * Create a new delivery order.
	 */
	public void newDeliveryOrder(String name, String address, String phoneNum) {
		orders.put(++currentOrderId, new Order(name, address, phoneNum, 3, 
				currentOrderId, taxRate));
	}

	/**
	 * Change the status of an order 1: Open 2: Paid 3: Delivered (Only for
	 * delivery order)
	 * 
	 * @param orderId
	 * @param status
	 */
	public void changeOrderStatus(int orderId, int status) {
		if (status == 1) {
			orders.get(orderId).changeToOpen();
		} else if (status == 2) {
			orders.get(orderId).changeToPaid();
		} else {
			orders.get(orderId).changeToDelivered();
		}

	}

	/**
	 * Add entrees to an order (can be multiple)
	 * 
	 * @param orderId
	 * @param entree
	 * @param numOfEntree
	 */
	public void addEntreeToOrder(int orderId, Entree entree, int numOfEntree) {
		orders.get(orderId).addEntree(entree, numOfEntree);
	}

	/**
	 * Remove an entree from the order (only one at a time)
	 * 
	 * @param orderId
	 * @param entree
	 */
	public void removeEntreeFromOrder(int orderId, Entree entree) {
		orders.get(orderId).removeEntree(entree);
	}

	/**
	 * Add tip to an order. (does not override existing tip)
	 * 
	 * @param orderId
	 * @param tip
	 */
	public void addTipToOrder(int orderId, double tip) {
		orders.get(orderId).addTip(tip);
	}

	public double subtotalOfOrder(int orderId) {
		return orders.get(orderId).subtotal();
	}

	public double totalOfOrder(int orderId) {
		return orders.get(orderId).total();
	}

	public double finalPriceOfOrder(int orderId) {
		return orders.get(orderId).finalPrice();
	}

	public String getOrderReceipt(int orderId) {
		return orders.get(orderId).getReceipt();
	}

	public void changePhoneNumOfOrder(int orderId, String phoneNum) {
		orders.get(orderId).setPhoneNum(phoneNum);
	}

	public void changeAddressOfOrder(int orderId, String address) {
		orders.get(orderId).setAddress(address);
	}
}
