package dto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import com.google.gson.Gson;

public class JsonDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2785588970965132478L;

	public JsonDTO() {
	}

	public static String getJson(Object objectClass) {

		//
		//
		// JSONObject object = new JSONObject();
		// for (Field field : objectClass.getClass().getDeclaredFields()) {
		// String nombreAtributo = field.getName();
		// if (!nombreAtributo.equalsIgnoreCase("serialVersionUID")) {
		// try {
		// Object result;
		// if (field.getType().getName().contains("DTO")) {
		// Object bla = buscarMetodo(objectClass.getClass().getMethods(),
		// nombreAtributo).invoke(objectClass, null);
		// result = new JSONObject((String)
		// bla.getClass().getMethod("toJson").invoke(bla, null));
		// } else {
		//
		// result = buscarMetodo(objectClass.getClass().getMethods(),
		// nombreAtributo).invoke(objectClass, null);
		// }
		// object.put(nombreAtributo, result);
		// } catch (IllegalAccessException | IllegalArgumentException |
		// InvocationTargetException | SecurityException
		// | JSONException e) {
		// e.printStackTrace();
		// } catch (NoSuchMethodException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }
		Gson gson = new Gson();
		return gson.toJson(objectClass).toString();
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
