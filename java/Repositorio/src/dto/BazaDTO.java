package dto;

import java.io.Serializable;

public class BazaDTO implements Serializable{
	
	// NO SE POR QUE ESTA CLASE NO SE HACE DTO (?)

	private static final long serialVersionUID = 7697472000145577384L;
	/**
	 * 
	 */
	private int idBaza;
	
	
	
	public BazaDTO(int idBaza) {
		super();
		this.idBaza = idBaza;
	}
	public int getIdBaza() {
		return idBaza;
	}
	public void setIdBaza(int idBaza) {
		this.idBaza = idBaza;
	}	

}