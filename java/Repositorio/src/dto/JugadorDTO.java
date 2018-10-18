package dto;

import java.io.Serializable;

public class JugadorDTO  implements Serializable{
	
	
	private static final long serialVersionUID = -7553168826919721922L;
	
	
	private int idJugador;


	public JugadorDTO(int idJugador) {
		super();
		this.idJugador = idJugador;
	}


	public int getIdJugador() {
		return idJugador;
	}


	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	
}
