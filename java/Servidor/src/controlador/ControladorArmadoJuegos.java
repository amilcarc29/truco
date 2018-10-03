package controlador;

import java.util.Vector;

import excepciones.GrupoJuegoException;
import excepciones.JugadorException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import negocio.Categoria;
import negocio.GrupoJuego;
import negocio.Jugador;
import negocio.JugadorIndividual;
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

	public void agregarJugadorLibreAEspera(int idUsuario) throws UsuarioException {
		getJugadoresEnEspera().add(new JugadorIndividual(ControladorUsuario.getInstancia().buscarUsuarioPorId(idUsuario)));
	}

	public void agregarParejaLibreAEspera(Pareja pareja) {
		getParejasEnEspera().add(pareja);
	}

	// TODO Renombrar en Diagrama
	public void confirmarGrupo(GrupoJuego grupo) {
		ControladorJuego.getInstancia().iniciarJuego(grupo);
	}

	public boolean armarGrupoDeIgualCategoria() throws UsuarioException {
		// TODO
		Categoria categoria = ControladorUsuario.getInstancia().buscarUsuarioPorApodo(getJugadoresEnEspera().get(0).getNombre()).getCategoria();
		Vector<Jugador> jugadoresGrupo = new Vector<>();
		int cantidadJugadoresEnGrupo = 0;
		for (Jugador jugador : getJugadoresEnEspera()) {
			if (ControladorUsuario.getInstancia().buscarUsuarioPorApodo(jugador.getNombre()).getCategoria().getIdCategoria() == categoria.getIdCategoria()
					&& cantidadJugadoresEnGrupo < 4) {
				jugadoresGrupo.add(jugador);
				cantidadJugadoresEnGrupo++;
			}
		}
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

	public void cancelarEsperaJugador(int idJugador) throws JugadorException {
		getJugadoresEnEspera().remove(buscarJugador(idJugador));
	}

	public void cancelarEsperaPareja(int idPareja) throws ParejaException {
		getParejasEnEspera().remove(buscarPareja(idPareja));
	}

	public GrupoJuego buscarGrupo(int idGrupo) throws GrupoJuegoException {
		for (GrupoJuego grupoJuego : grupos) {
			if (grupoJuego.esGrupoJuego(idGrupo)) {
				return grupoJuego;
			}
		}
		throw new GrupoJuegoException("El GrupoJuego: " + idGrupo + "no existe.");
	}

	public Jugador buscarJugador(int idJugador) throws JugadorException {
		for (Jugador jugador : jugadores) {
			if (jugador.esJugador(idJugador)) {
				return jugador;
			}
		}
		throw new JugadorException("El jugador: " + idJugador + "no existe.");
	}

	public Pareja buscarPareja(int idPareja) throws ParejaException {
		for (Pareja pareja : parejas) {
			if (pareja.esPareja(idPareja)) {
				return pareja;
			}
		}
		throw new ParejaException("La pareja: " + idPareja + "no existe.");
	}

	public static ControladorArmadoJuegos getInstancia() {
		if (instancia == null) {
			instancia = new ControladorArmadoJuegos();
		}
		return instancia;
	}
}
