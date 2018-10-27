package dto;

import java.io.Serializable;

public class CartaDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4426685981267588322L;
	
	private int idCarta;
	private int numero;
	private String palo;
	
	
	public CartaDTO(int idCarta, int numero, String palo) {
		super();
		this.idCarta = idCarta;
		this.numero = numero;
		this.palo = palo;
	}


	public int getIdCarta() {
		return idCarta;
	}


	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getPalo() {
		return palo;
	}


	public void setPalo(String palo) {
		this.palo = palo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toJson() {
		return JsonDTO.getJson(this);
	}
}
