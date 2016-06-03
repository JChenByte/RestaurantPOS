package Core;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jie Chen (github.com/JChenByte) 
 * github.com/JChenByte/RestaurantPOS
 */
public class Entree {
	private String name;
	private double price;
	private String category;
	private Map<String, Double> ingredients;

	public Entree(String name, double price, String category) {
		this.name = name;
		this.price = price;
		this.category = category;
		ingredients = new HashMap<String, Double>();
	}

	/**
	 * Add an ingredient to the ingredients Map.
	 * 
	 * @param name
	 * @param quantity:
	 *            quantity required for the entree.
	 */
	public void addIngredient(String name, double quantity) {
		ingredients.put(name, quantity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Remove an ingredient from ingredients Map.
	 * 
	 * @param name
	 */
	public void removeIngredient(String name) {
		ingredients.remove(name);
	}

	public String toString() {
		String temp = "Entree: " + name + ". Price: " + NumberFormat.
				getCurrencyInstance().format(price)
				+ "\nIngredients: ";
		for (String ingredient : ingredients.keySet()) {
			temp += ingredient + " ";
		}
		return temp;
	}

	public double getPrice() {
		return price;
	}

	/**
	 * Adjust the price.
	 * 
	 * @param price
	 */
	public void AdjustPrice(double price) {
		this.price = price;
	}

}
