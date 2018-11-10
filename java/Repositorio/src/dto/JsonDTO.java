package dto;

import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class JsonDTO {

	public JsonDTO() {
	}

	public static String getJson(Object objectClass) {

		Gson gson = new Gson();
		return gson.toJson(objectClass);
	}


}
