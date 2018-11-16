package dto;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class ParejaDTO extends JsonDTO  implements Serializable{
private List<JugadorDTO> jugadores;
	
	public ParejaDTO(List<JugadorDTO> j){
		
		this.jugadores=j;
	}
		

	public List<JugadorDTO> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorDTO> jugadores) {
		this.jugadores = jugadores;
	}



	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		 return getJson(this);
	}

}
