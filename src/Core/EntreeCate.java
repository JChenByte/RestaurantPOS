package Core;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jie Chen (github.com/JChenByte) 
 * github.com/JChenByte/RestaurantPOS
 */
public class EntreeCate {
	private Map<String, Entree> entreeList;
	private String name;

	public EntreeCate(String name) {
		this.name = name;
		entreeList = new HashMap<String, Entree>();

	}

	/**
	 * Add new entree.
	 * 
	 * @param entreeName
	 * @param price
	 */
	public void addEntree(String entreeName, double price) {
		entreeList.put(entreeName, new Entree(entreeName, price, name));
	}

	/**
	 * Remove entree.
	 * 
	 * @param entreeName
	 */
	public void removeEntree(String entreeName) {
		entreeList.remove(entreeName);
	}

	public String getName() {
		return name;
	}

	/**
	 * Add an ingredient to an entree.
	 * 
	 * @param entreeName
	 * @param ingrName
	 * @param quantity
	 */
	public void addIngredient(String entreeName, String ingrName, double 
			quantity) {
		entreeList.get(entreeName).addIngredient(ingrName, quantity);
	}

	/**
	 * Remove an ingredient form an entree.
	 * 
	 * @param entreeName
	 * @param ingrName
	 */
	public void removeIngredient(String entreeName, String ingrName) {
		entreeList.get(entreeName).removeIngredient(ingrName);
	}

	/**
	 * Adjust the price of an entree.
	 * 
	 * @param entreeName
	 * @param price
	 */
	public void adjustPrice(String entreeName, double price) {
		entreeList.get(entreeName).AdjustPrice(price);
	}

	public String toString() {
		String temp = "Category: " + name + "\n";
		for (Entree entree : entreeList.values()) {
			temp += entree.toString() + "\n";
		}

		return temp;
	}
}
