package dto;

import java.io.Serializable;
import java.util.List;

public class ParejaDTO extends JsonDTO  implements Serializable{
private List<JugadorDTO> jugadores;
	
	public ParejaDTO(List<JugadorDTO> j){
		
		this.jugadores=j;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		 return getJson(this);
	}
}
