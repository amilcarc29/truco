package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.JugadorCartaDAO;
import dto.CartaDTO;
import dto.JugadorDTO;

public class JugadorGrupal extends Jugador {
	private Miembro miembro;
	

	public JugadorGrupal(Miembro miembro) {
		super();
		this.miembro = miembro;
	}

	public Miembro getMiembro() {
		return miembro;
	}

	public void setMiembro(Miembro miembro) {
		this.miembro = miembro;
	}

	@Override
	public JugadorDTO toDTO() {
		// TODO Auto-generated method stub
		JugadorDTO j = new JugadorDTO(this.getIdJugador());
		j.setTieneTurno(this.isTieneTurno());
		j.setUsuario(null);
		j.setPuntoEnvido(this.getPuntosEnvido());
		// VER SI FUNCIONA EL JSON
		j.setMiembro(this.getMiembro().toDTO());
		List<Carta> c = JugadorCartaDAO.getInstancia().getCartasbyJugador(this, true);
		List<CartaDTO> cdto = new ArrayList<>();

		for (Carta carta : c) {
			cdto.add(carta.toDTO());
		}
		j.setCartas(cdto);

		return j;
	}
}
