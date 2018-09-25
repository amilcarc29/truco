package controlador;

import java.util.Vector;

import negocio.FactoryJuegos;
import negocio.GrupoJuego;
import negocio.Juego;

public class ControladorJuego {

	private Vector<Juego> juegos;
	private FactoryJuegos factory;

	private ControladorJuego instancia;

	public ControladorJuego() {
		juegos = new Vector<>();
	}

	public ControladorJuego(Vector<Juego> juegos, FactoryJuegos factory) {
		super();
		this.juegos = juegos;
		this.factory = factory;
	}

	public Vector<Juego> getJuegos() {
		return juegos;
	}

	public void setJuegos(Vector<Juego> juegos) {
		this.juegos = juegos;
	}

	public FactoryJuegos getFactory() {
		return factory;
	}

	public void setFactory(FactoryJuegos factory) {
		this.factory = factory;
	}

	public void iniciarJuego(GrupoJuego grupoJuego) {
		// TODO
	}

	public void cantarTruco(int idJuego, int idJugador) {
		// TODO
	}

	public void cantarReTruco(int idJuego, int idJugador) {
		// TODO
	}

	public void cantarVale4(int idJuego, int idJugador) {
		// TODO
	}

	public void cantarEnvido(int idJuego, int idJugador) {
		// TODO
	}

	public void cantarRealEnvido(int idJuego, int idJugador) {
		// TODO
	}

	public void cantarFaltaEnvido(int idJuego, int idJugador) {
		// TODO
	}

	public void quieroTruco(int idJuego, int idJugador) {
		// TODO
	}

	public void quieroEnvido(int idJuego, int idJugador) {
		// TODO
	}

	public void noQuieroTruco(int idJuego, int idJugador) {
		// TODO
	}

	public void noQuieroEnvido(int idJuego, int idJugador) {
		// TODO
	}

	public void jugarCarta(int idJuego, int idJugador, int idCarta) {
		// TODO
	}

	public void irseAlMazo(int idJuego, int idJugador) {
		// TODO
	}

	public void salir(int idJuego, int idJugador) {
		// TODO
	}

	public ControladorJuego getInstancia() {
		if (instancia == null) {
			instancia = new ControladorJuego();
		}
		return instancia;
	}
}
