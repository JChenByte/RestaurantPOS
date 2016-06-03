package Core;

/**
 * @author Jie Chen (github.com/JChenByte) 
 * github.com/JChenByte/RestaurantPOS
 */
public class Employee {
	private String name;
	private String password;
	private boolean isManager;

	/**
	 * @param username
	 * @param password
	 *            In MD5 Hash.
	 * @param isManager
	 *            Is this employee a manager.
	 */
	public Employee(String name, String password, boolean isManager) {
		this.name = name;
		this.password = password;
		this.isManager = isManager;
	}

	/**
	 * @param password
	 * @return true if the password is correct, false otherwise.
	 */
	public boolean verifyPassword(String password) {
		return (password == this.password);
	}

	public String toString() {
		String temp = "Name: " + name + ".";

		/* Check if the person is a manager. */
		if (isManager) {
			temp += " Manager.";
		} else {
			temp += " Non-Manager.";
		}

		return temp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public String getName() {
		return name;
	}

}
