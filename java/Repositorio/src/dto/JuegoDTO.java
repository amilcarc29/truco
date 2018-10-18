package dto;

import java.io.Serializable;

public class JuegoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3203339374060504339L;
	private int idJuego;
	
	

	public JuegoDTO(int idJuego) {
		super();
		this.idJuego = idJuego;
	}

	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}
}
