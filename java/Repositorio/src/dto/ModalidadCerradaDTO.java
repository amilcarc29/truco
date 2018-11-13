package dto;

import java.io.Serializable;

public class ModalidadCerradaDTO extends JsonDTO  implements Serializable {

	private static final long serialVersionUID = -9054474884070966786L;

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		 return getJson(this);
	}
}
