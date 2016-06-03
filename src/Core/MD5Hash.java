package Core;

import java.security.MessageDigest;

/**
 * @author Jie Chen (github.com/JChenByte) 
 * github.com/JChenByte/RestaurantPOS
 */
public class MD5Hash {

	/* MD5 Hashing. */
	public static String hashing(String input) {
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return input;
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;

	}
}
