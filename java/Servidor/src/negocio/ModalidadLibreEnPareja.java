package negocio;

import java.util.List;

import dao.JuegoDAO;
import dao.UsuarioDAO;
import excepciones.CategoriaException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;

public class ModalidadLibreEnPareja extends Juego{

	public ModalidadLibreEnPareja() {
		super();
	}

	public ModalidadLibreEnPareja(List<Pareja> parejas) {
		super(parejas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calcularPuntos() {
		return 10;
	}

	public void save() throws ParejaException {
		this.setId(JuegoDAO.getInstancia().guardarJuegoLibreEnPareja(this));
	}

	@Override
	public void finalizarJuego() throws UsuarioException, CategoriaException, ParejaException, MiembroException {
		this.setGanador(this.obtenerGanador());
		Pareja ganadora = this.getGanador();
		for (Pareja p : this.getParejas()) {
			for (Jugador j : p.getJugadores()) {
				Usuario usuario = UsuarioDAO.getInstancia().obtenerUsuarioJuegoIndividual(j);
				if (ganadora.tieneJugador(j.getId())) {
					int puntos = this.calcularPuntosSegunCategoria(usuario); // CREAR NUEVO METODO. DEBE SER SEGUN LA CATEGORIA DE SU PAREJA
					usuario.actualizarPuntos(1, 1, puntos);
				}
				else {
					usuario.actualizarPuntos(0, 1, 0);
				}
			}
		}
		this.getUltimaMano().finalizarMano();
		JuegoDAO.getInstancia().finalizarJuego(this);
	}
}
