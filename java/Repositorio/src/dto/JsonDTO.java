package dto;

import com.google.gson.Gson;

public abstract class JsonDTO {
	public String toJson() {
		Gson g = new Gson();
		String us = g.toJson(this);
		return us;

	}
}
