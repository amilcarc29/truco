package dto;

import java.util.List;

public class ParejaDTO {
private List<JugadorDTO> jugadores;
	
	public ParejaDTO(List<JugadorDTO> j){
		
		this.jugadores=j;
	}

	public String toJson() {
		return JsonDTO.getJson(this);
	}
}
