package dto;

import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonDTO {

	public JsonDTO() {
	}

	public static String getJson(Object objectClass) {

		Gson gson = new Gson();
		return gson.toJson(objectClass);
	}

	public static String getJsonPropio(Object objectClass) {
		JSONObject object = new JSONObject();
		for (Field field : objectClass.getClass().getDeclaredFields()) {
			String nombreAtributo = field.getName();
			if (!nombreAtributo.equalsIgnoreCase("serialVersionUID")) {
				try {
					Object result;
					if (field.getType().getName().contains("DTO")) {
						Object bla = buscarMetodo(objectClass.getClass().getMethods(), nombreAtributo).invoke(objectClass, null);
						result = new JSONObject((String) bla.getClass().getMethod("toJson").invoke(bla, null));
					} else {
						result = buscarMetodo(objectClass.getClass().getMethods(), nombreAtributo).invoke(objectClass, null);
					}
					object.put(nombreAtributo, result);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException
						| JSONException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
		return object.toString();
	}

	private static Method buscarMetodo(Method[] metodos, String nombreAtributo) {
		for (Method metodo : metodos) {
			if (metodo.getName().equalsIgnoreCase("get" + nombreAtributo)
					|| metodo.getName().equalsIgnoreCase("is" + nombreAtributo)) {
				return metodo;
			}
		}
		return null;
	}
}
