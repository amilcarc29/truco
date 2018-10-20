package controlador;

import java.util.List;
import java.util.Vector;

import dao.JugadorDAO;
import dto.GrupoDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import excepciones.GrupoJuegoException;
import excepciones.JuegoException;
import excepciones.JugadorException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import negocio.GrupoJuego;
import negocio.Jugador;
import negocio.JugadorGrupal;
import negocio.JugadorIndividual;
import negocio.Miembro;
import negocio.Pareja;
import negocio.Usuario;

public class ControladorArmadoJuegos {

	private Vector<GrupoJuego> grupos;
	private Vector<Jugador> jugadores;
	private Vector<Pareja> parejas;
	private static Vector<Jugador> jugadoresEnEspera;
	private static Vector<Pareja> parejasEnEspera;

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
		ControladorArmadoJuegos.jugadoresEnEspera = jugadoresEnEspera;
		ControladorArmadoJuegos.parejasEnEspera = parejasEnEspera;
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

	public Vector<Pareja> getParejasEnEspera() {
		return parejasEnEspera;
	}

	public void agregarJugadorLibreAEspera(UsuarioDTO usuario) throws UsuarioException, CategoriaException {

		Usuario us = ControladorUsuario.getInstancia().buscarUsuarioPorId(usuario.getIdUsuario());

		getJugadoresEnEspera().add(new JugadorIndividual(us));
	}

	public void agregarParejaLibreAEspera(Pareja pareja) {
		getParejasEnEspera().add(pareja);
	}

	// TODO Renombrar en Diagrama
	public void confirmarGrupo(GrupoJuego grupo)
			throws JuegoException, UsuarioException, CategoriaException, ParejaException, MiembroException {
		ControladorJuego.getInstancia().iniciarJuego(grupo);
	}

	public boolean armarGrupoDeIgualCategoria() throws UsuarioException {

		// TODO
		// Categoria categoria =
		// ControladorUsuario.getInstancia().buscarUsuarioPorApodo(getJugadoresEnEspera().get(0).getNombre()).getCategoria();
		// Vector<Jugador> jugadoresGrupo = new Vector<>();
		// int cantidadJugadoresEnGrupo = 0;
		// for (Jugador jugador : getJugadoresEnEspera()) {
		// if
		// (ControladorUsuario.getInstancia().buscarUsuarioPorApodo(jugador.getNombre()).getCategoria().getIdCategoria()
		// == categoria.getIdCategoria()
		// && cantidadJugadoresEnGrupo < 4) {
		// jugadoresGrupo.add(jugador);
		// cantidadJugadoresEnGrupo++;
		// }
		// }
		return false;
	}

	public void iniciarPartidaLibre(UsuarioDTO u1, UsuarioDTO u2, UsuarioDTO u3, UsuarioDTO u4)
			throws UsuarioException, CategoriaException, JuegoException, ParejaException {
		try {

			Pareja p1 = this.armarPareja(u1, u2);
			Pareja p2 = this.armarPareja(u3, u4);

			GrupoJuego grupo = new GrupoJuego(p1, p2);
			grupo.setTipoJuego("LIBRE");
			ControladorJuego.getInstancia().iniciarJuego(grupo);

			
			
			
		} catch (UsuarioException e) {

			e.printStackTrace();
		} catch (CategoriaException e1) {

			e1.printStackTrace();
		} catch (JuegoException e2) {

			e2.printStackTrace();
		} catch (MiembroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void iniciarPartidaEnPareja(UsuarioDTO u1, UsuarioDTO u2, UsuarioDTO u3, UsuarioDTO u4)
			throws UsuarioException, CategoriaException, JuegoException, ParejaException {
		try {

			Pareja p1 = this.armarPareja(u1, u2);
			Pareja p2 = this.armarPareja(u3, u4);
			GrupoJuego gj = new GrupoJuego(p1, p2);
			gj.setTipoJuego("ENPAREJA");
			ControladorJuego.getInstancia().iniciarJuego(gj);

		} catch (UsuarioException e) {

			e.printStackTrace();
		} catch (CategoriaException e1) {

			e1.printStackTrace();
		} catch (JuegoException e2) {

			e2.printStackTrace();
		} catch (MiembroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void iniciarPartidaCerrada(UsuarioDTO u1, UsuarioDTO u2, UsuarioDTO u3, UsuarioDTO u4, GrupoDTO g)
			throws UsuarioException, CategoriaException, JuegoException, MiembroException, ParejaException {
		{
			try {

				Pareja p1 = this.armarParejaCerrada(u1, u2, g);
				Pareja p2 = this.armarParejaCerrada(u3, u4, g);
				GrupoJuego gj = new GrupoJuego(p1, p2);
				gj.setTipoJuego("CERRADA");
				ControladorJuego.getInstancia().iniciarJuego(gj);

			} catch (UsuarioException e) {

				e.printStackTrace();
			} catch (CategoriaException e1) {

				e1.printStackTrace();
			} catch (JuegoException e2) {

				e2.printStackTrace();
			} catch (MiembroException e3) {

				e3.printStackTrace();
			}
		}

	}

	public boolean armarGrupoDeMayorCategoria() {
		// TODO
		return false;
	}

	public boolean armarGrupoDeMenorCategoria() {
		// TODO
		return false;
	}

	public Pareja armarParejaCerrada(UsuarioDTO u1, UsuarioDTO u2, GrupoDTO g)
			throws UsuarioException, CategoriaException, MiembroException {
		Miembro m1 = ControladorGrupo.getInstancia().buscarMiembro(u1, g);
		Miembro m2 = ControladorGrupo.getInstancia().buscarMiembro(u2, g);
		JugadorGrupal j1 = new JugadorGrupal(m1);
		JugadorGrupal j2 = new JugadorGrupal(m2);
		Pareja p = new Pareja(j1, j2);
		return p.saveGrupal();

	}

	public Pareja armarPareja(UsuarioDTO u1, UsuarioDTO u2) throws UsuarioException, CategoriaException {

		Usuario usuario1 = ControladorUsuario.getInstancia().buscarUsuarioPorId(u1.getIdUsuario());
		Usuario usuario2 = ControladorUsuario.getInstancia().buscarUsuarioPorId(u2.getIdUsuario());

		JugadorIndividual j1 = new JugadorIndividual(usuario1);
		JugadorIndividual j2 = new JugadorIndividual(usuario2);

		Pareja p = new Pareja(j1, j2);
		return p.saveIndividual();

	}

	public void cancelarEsperaJugador(int idJugador) throws JugadorException {
		getJugadoresEnEspera().remove(buscarJugador(idJugador));
	}

	public void cancelarEsperaPareja(int idPareja) throws ParejaException {
		getParejasEnEspera().remove(buscarPareja(idPareja));
	}

	public GrupoJuego buscarGrupoJuego(int idGrupo) throws GrupoJuegoException {
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

	public List<JuegoDTO> getJuegosActivo(UsuarioDTO usuario) throws CategoriaException, UsuarioException {
		return ControladorJuego.getInstancia().getJuegosActivos(usuario);

	}
}
