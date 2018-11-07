package dto;


import com.google.gson.Gson;

public class JsonDTO {
	/**
	 * 
	 */

	public JsonDTO() {
	}

	public static String getJson(Object objectClass) {

		Gson gson = new Gson();
		return gson.toJson(objectClass);
	}

}
