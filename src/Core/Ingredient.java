package Core;

/**
 * @author Jie Chen (github.com/JChenByte) 
 * github.com/JChenByte/RestaurantPOS
 */
public class Ingredient {
	private String name;
	private double amountLeft;
	private String unit;

	public Ingredient(String name, double amountLeft, String unit) {
		this.name = name;
		this.amountLeft = amountLeft;
		this.unit = unit;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public String toString() {
		return name + ": " + amountLeft + " " + unit + ".";
	}

	public void incAmount(double temp) {
		amountLeft += temp;
	}

	public void decAmount(double temp) {
		amountLeft -= temp;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the amountLeft
	 */
	public double getAmountLeft() {
		return amountLeft;
	}

	/**
	 * @param amountLeft
	 */
	public void setAmountLeft(double amountLeft) {
		this.amountLeft = amountLeft;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

}
