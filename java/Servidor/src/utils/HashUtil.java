package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashUtil {

	public static String hashString(String pass) {

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");

			byte[] arr = md.digest(pass.getBytes());
			return Base64.getEncoder().encodeToString(arr);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
