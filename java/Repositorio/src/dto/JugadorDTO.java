package dto;

import java.io.Serializable;
import java.util.List;

public class JugadorDTO extends JsonDTO  implements Serializable {

	private static final long serialVersionUID = -7553168826919721922L;
	private UsuarioDTO usuario;
	private MiembroDTO miembro;
	private List<CartaDTO> cartas;
	private List<CartaDTO> cartasJugadas;

	private boolean tieneTurno;

	private int idJugador;
	
	private int puntoEnvido;

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

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public MiembroDTO getMiembro() {
		return miembro;
	}

	public void setMiembro(MiembroDTO miembro) {
		this.miembro = miembro;
	}

	public int getPuntoEnvido() {
		return puntoEnvido;
	}

	public void setPuntoEnvido(int puntoEnvido) {
		this.puntoEnvido = puntoEnvido;
	}
	
	
}
