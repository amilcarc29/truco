package dto;

import com.google.gson.Gson;


public abstract class JsonDTO {

	public String getJson(Object objectClass) {

		Gson gson = new Gson();
		return gson.toJson(objectClass);
	}

	public abstract String toJson();

}
