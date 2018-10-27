package dto;

import java.io.Serializable;

public class ModalidadCerradaDTO implements Serializable {

	private static final long serialVersionUID = -9054474884070966786L;

	public String toJson() {
		return JsonDTO.getJson(this);
	}
}
