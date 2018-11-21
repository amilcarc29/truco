package dto;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class ParejaDTO extends JsonDTO  implements Serializable{
	
	private int idPareja;
	private List<JugadorDTO> jugadores;
		

	public ParejaDTO(int idPareja, List<JugadorDTO> jugadores) {
		super();
		this.idPareja = idPareja;
		this.jugadores = jugadores;
	}

	public List<JugadorDTO> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorDTO> jugadores) {
		this.jugadores = jugadores;
	}	

	public int getIdPareja() {
		return idPareja;
	}


	public void setIdPareja(int idPareja) {
		this.idPareja = idPareja;
	}


	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		 return getJson(this);
	}

}
