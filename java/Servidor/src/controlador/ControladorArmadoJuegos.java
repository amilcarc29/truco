package controlador;

import java.util.Vector;

import negocio.GrupoJuego;
import negocio.Jugador;
import negocio.Pareja;

public class ControladorArmadoJuegos {

	private Vector<GrupoJuego> grupos;
	private Vector<Jugador> jugadores;
	private Vector<Pareja> parejas;
	private Vector<Jugador> jugadoresEnEspera;
	private Vector<Pareja> parejasEnEspera;

	private static ControladorArmadoJuegos instancia;

	public ControladorArmadoJuegos() {
		grupos = new Vector<>();
		jugadores = new Vector<>();
		parejas = new Vector<>();
		jugadoresEnEspera = new Vector<>();
		parejasEnEspera = new Vector<>();
	}

	public ControladorArmadoJuegos(Vector<GrupoJuego> grupos, Vector<Jugador> jugadores, Vector<Pareja> parejas,
			Vector<Jugador> jugadoresEnEspera, Vector<Pareja> parejasEnEspera) {
		super();
		this.grupos = grupos;
		this.jugadores = jugadores;
		this.parejas = parejas;
		this.jugadoresEnEspera = jugadoresEnEspera;
		this.parejasEnEspera = parejasEnEspera;
	}

	public Vector<GrupoJuego> getGrupos() {
		return grupos;
	}

	public void setGrupos(Vector<GrupoJuego> grupos) {
		this.grupos = grupos;
	}

	public Vector<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Vector<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Vector<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(Vector<Pareja> parejas) {
		this.parejas = parejas;
	}

	public Vector<Jugador> getJugadoresEnEspera() {
		return jugadoresEnEspera;
	}

	public void setJugadoresEnEspera(Vector<Jugador> jugadoresEnEspera) {
		this.jugadoresEnEspera = jugadoresEnEspera;
	}

	public Vector<Pareja> getParejasEnEspera() {
		return parejasEnEspera;
	}

	public void setParejasEnEspera(Vector<Pareja> parejasEnEspera) {
		this.parejasEnEspera = parejasEnEspera;
	}

	public void agregarJugadorLibreAEspera(int idUsuario) {
		// TODO
	}

	public void agregarParejaLibreAEspera(Pareja pareja) {
		// TODO
	}

	// TODO Renombrar en Diagrama
	public void confirmarGrupo(GrupoJuego grupo) {
		// TODO
	}

	public boolean armarGrupoDeIgualCategoria() {
		// TODO
		return false;
	}

	public boolean armarGrupoDeMayorCategoria() {
		// TODO
		return false;
	}

	public boolean armarGrupoDeMenorCategoria() {
		// TODO
		return false;
	}

	public boolean armarGrupoDeParejas() {
		// TODO
		return false;
	}

	public void cancelarEsperaJugador(int idJugador) {
		// TODO
	}

	public void cancelarEsperaPareja(int idPareja) {
		// TODO
	}

	public GrupoJuego buscarGrupo(int idGrupo) {
		// TODO
		return null;
	}

	public Jugador buscarJugador(int idJugador) {
		// TODO
		return null;
	}

	public Pareja buscarPareja(int idPareja) {
		// TODO
		return null;
	}

	public static ControladorArmadoJuegos getInstancia() {
		if (instancia == null) {
			instancia = new ControladorArmadoJuegos();
		}
		return instancia;
	}
}
