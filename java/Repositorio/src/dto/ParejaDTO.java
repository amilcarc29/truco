package dto;

import java.io.Serializable;
import java.util.List;

public class ParejaDTO  implements Serializable{
private List<JugadorDTO> jugadores;
	
	public ParejaDTO(List<JugadorDTO> j){
		
		this.jugadores=j;
	}

	public String toJson() {
		return JsonDTO.getJson(this);
	}
}
