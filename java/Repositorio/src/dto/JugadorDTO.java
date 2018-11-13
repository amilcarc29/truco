package dto;

import java.io.Serializable;
import java.util.List;

public class JugadorDTO extends JsonDTO  implements Serializable {

	private static final long serialVersionUID = -7553168826919721922L;
	private String apodo;
	private List<CartaDTO> cartas;
	private List<CartaDTO> cartasJugadas;

	private boolean tieneTurno;

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

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		 return getJson(this);
	}
	
	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public List<CartaDTO> getCartas() {
		return cartas;
	}

	public void setCartas(List<CartaDTO> cartas) {
		this.cartas = cartas;
	}

	public boolean isTieneTurno() {
		return tieneTurno;
	}

	public void setTieneTurno(boolean tieneTurno) {
		this.tieneTurno = tieneTurno;
	}

	public List<CartaDTO> getCartasJugadas() {
		return cartasJugadas;
	}

	public void setCartasJugadas(List<CartaDTO> cartasJugadas) {
		this.cartasJugadas = cartasJugadas;
	}
}
