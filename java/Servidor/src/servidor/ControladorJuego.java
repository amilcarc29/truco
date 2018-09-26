package servidor;

import java.util.LinkedList;
import java.util.List;

import negocio.FactoryJuegos;
import negocio.GrupoJuego;
import negocio.Juego;

public class ControladorJuego {

	private List<Juego> juegos;
	private static FactoryJuegos fcJuegos;

	public ControladorJuego() {
		juegos = new LinkedList<Juego>();
		fcJuegos = new FactoryJuegos();

	}

	public void iniciarJuego(GrupoJuego grupo) {
		Juego j = fcJuegos.getJuego(grupo.getTipoJuego());
		if (j != null) {
			j.setParejas(grupo.getParejas());
			j.crearChico();
			juegos.add(j);
		}
	}

	public void cantarTruco(int idJuego, int idJugador) {
		Juego j = this.buscarJuego(idJuego);
		j.cantarTruco(idJugador);

	}

	public void cantarReTruco(int idJuego, int idJugador) {
		Juego j = this.buscarJuego(idJuego);
		j.cantarReTruco(idJugador);

	}

	public void cantarVale4(int idJuego, int idJugador) {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.cantarVale4(idJugador);
	}

	public void cantarQuieroTruco(boolean quieroSiNo, int idJuego, int idJugador) {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.cantarQuieroTruco(quieroSiNo, idJugador);
	}

	public void cantarQuieroEnvido(boolean quieroSiNo, int idJuego, int idJugador) {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.cantarQuieroEnvido(quieroSiNo, idJugador);
	}

	public void cantarEnvido(int idJuego, int idJugador) {
		Juego j = this.buscarJuego(idJuego);
		j.cantarEnvido(idJugador);

	}

	// TODO AGREGAR
	public void aceptarTruco(int idJuego, int idJugador) {
		Juego j = this.buscarJuego(idJuego);
	}

	public Juego buscarJuego(int idJuego) {

		for (Juego juego : juegos) {
			if (juego.sosJuego(idJuego))
				return juego;
		}
		return null;
	}

	public void jugarCarta(int idJuego, int idJugador, int numero, String palo) {
		Juego j = this.buscarJuego(idJuego);
		j.jugarCarta(idJugador, numero, palo);
	}

	public boolean verificarFinJuego(int idJuego) {
		Juego j = this.buscarJuego(idJuego);
		return j.verificarFinChico();

	}

	public void contarPuntos(int idJuego) {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.contarPuntos();
	}

}
