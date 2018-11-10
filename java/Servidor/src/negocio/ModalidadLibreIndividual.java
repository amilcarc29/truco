package negocio;

import java.util.List;

import dao.JuegoDAO;
import dao.UsuarioDAO;
import excepciones.CategoriaException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;

public class ModalidadLibreIndividual extends Juego{

	public ModalidadLibreIndividual() {
		super();
	}

	public ModalidadLibreIndividual(List<Pareja> parejas) {
		super(parejas);
	}

	@Override
	public int calcularPuntos() {
		return 10;
	}

	public void save() throws ParejaException, CategoriaException, MiembroException {
		this.setId(JuegoDAO.getInstancia().guardarJuegoLibreIndividual(this));
	}

	@Override
	public void finalizarJuego() throws UsuarioException, CategoriaException, ParejaException, MiembroException {
		this.setGanador(this.obtenerGanador());
		Pareja ganadora = this.getGanador();
		for (Pareja p : this.getParejas()) {
			for (Jugador j : p.getJugadores()) {
				Usuario usuario = UsuarioDAO.getInstancia().obtenerUsuarioJuegoIndividual(j);
				if (ganadora.tieneJugador(j.getId())) {
					int puntos = this.calcularPuntosSegunCategoria(usuario);
					usuario.actualizarPuntos(1, 1, puntos);
				}
				else {
					usuario.actualizarPuntos(0, 1, 0);
				}
			}
		}
		JuegoDAO.getInstancia().finalizarJuego(this);
	}
}
