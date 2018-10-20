package dto;

import java.io.Serializable;
import java.util.List;

public class BazaDTO implements Serializable{

	private static final long serialVersionUID = 7697472000145577384L;
	/**
	 * 
	 */
	private int idBaza;
	private List<JugadorDTO> jugadores;
	private boolean parda;

	
	
	
	public BazaDTO(int idBaza, List<JugadorDTO> jugDTO, boolean parda) {
		super();
		this.idBaza = idBaza;
		this.jugadores=jugDTO;
		this.parda=parda;
		
	}
	public int getIdBaza() {
		return idBaza;
	}
	public void setIdBaza(int idBaza) {
		this.idBaza = idBaza;
	}	

}
