package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.JuegoDAO;
import dao.JugadorCartaDAO;
import dto.CartaDTO;
import dto.JugadorDTO;

public class JugadorIndividual extends Jugador {

	private Usuario usuario;

	public JugadorIndividual(Usuario usuario) {
		super();
		setUsuario(usuario);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public JugadorDTO toDTO() {
		// TODO Auto-generated method stub
		JugadorDTO j = new JugadorDTO(this.getIdJugador());
		j.setTieneTurno(this.isTieneTurno());
		j.setUsuario(this.getUsuario().toDTO());
		j.setPuntoEnvido(this.getPuntosEnvido());
		// VER SI FUNCIONA EL JSON
		j.setMiembro(null);
		List<Carta> c = JugadorCartaDAO.getInstancia().getCartasbyJugador(this, true);
		List<CartaDTO> cdto = new ArrayList<>();

		for (Carta carta : c) {
			cdto.add(carta.toDTO());
		}
		j.setCartas(cdto);

		return j;
	}
}
