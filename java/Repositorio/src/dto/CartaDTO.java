package dto;

import java.io.Serializable;

public class CartaDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4426685981267588322L;
	private int numero;
	private String palo;

	public CartaDTO(int numero, String palo) {
		this.setNumero(numero);
		this.setPalo(palo);

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
}
