package controlador;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import dao.JugadorDAO;
import dao.ParejaDAO;
import dao.UsuarioDAO;
import dto.GrupoDTO;
import dto.JuegoDTO;
import dto.JugadorDTO;
import dto.ParejaDTO;
import dto.UsuarioDTO;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.ChicoException;
import excepciones.ErrorCode;
import excepciones.GrupoException;
import excepciones.GrupoJuegoException;
import excepciones.JuegoException;
import excepciones.JugadorException;
import excepciones.ManoException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import negocio.Grupo;
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
	private static Vector<JugadorIndividual> jugadoresEnEspera;
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
			Vector<JugadorIndividual> jugadoresEnEspera, Vector<Pareja> parejasEnEspera) {
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

	public Vector<JugadorIndividual> getJugadoresEnEspera() {
		return jugadoresEnEspera;
	}

	public Vector<Pareja> getParejasEnEspera() {
		return parejasEnEspera;
	}

	public ParejaDTO agregarParejaLibreAEspera(UsuarioDTO usuario1, UsuarioDTO usuario2)
			throws CategoriaException, UsuarioException, ParejaException {
		

		Pareja pareja = this.armarPareja(usuario1, usuario2);

		getParejasEnEspera().add(pareja);
		
		return pareja.toDTO();
	}
	
	public void agregarJugadorLibreAEspera(UsuarioDTO usuario) throws UsuarioException, CategoriaException {

		Usuario us = ControladorUsuario.getInstancia().buscarUsuarioPorId(usuario.getIdUsuario());

		getJugadoresEnEspera().add(new JugadorIndividual(us));
	}

	// TODO Renombrar en Diagrama
	public void confirmarGrupo(GrupoJuego grupo) throws JuegoException, UsuarioException, CategoriaException,
			ParejaException, MiembroException, ManoException, CartaException, ChicoException {
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

	public void iniciarPartidaLibre(UsuarioDTO u1, UsuarioDTO u2, UsuarioDTO u3, UsuarioDTO u4) throws UsuarioException,
			CategoriaException, JuegoException, ParejaException, ManoException, CartaException, ChicoException, MiembroException {
		try {

			Pareja p1 = this.armarPareja(u1, u2);
			Pareja p2 = this.armarPareja(u3, u4);

			GrupoJuego grupo = new GrupoJuego(p1, p2);
			grupo.setTipoJuego("LIBRE");
			ControladorJuego.getInstancia().iniciarJuego(grupo);

		} catch (UsuarioException e) {
			throw e;
		} catch (CategoriaException e1) {
			throw e1;
		} catch (JuegoException e2) {
			throw e2;
		} catch (MiembroException e) {
			throw e;
		}

	}

	public void iniciarPartidaEnPareja(Pareja p1, Pareja p2) throws UsuarioException, CategoriaException,
			JuegoException, ParejaException, ManoException, CartaException, ChicoException, MiembroException {
		try {
			
			Pareja pareja1 = ParejaDAO.getInstancia().buscarParejaPorIdNegocio(p1.getIdPareja());
			Pareja pareja2 = ParejaDAO.getInstancia().buscarParejaPorIdNegocio(p2.getIdPareja());

			GrupoJuego gj = new GrupoJuego(pareja1, pareja2);
			gj.setTipoJuego("ENPAREJA");
			ControladorJuego.getInstancia().iniciarJuego(gj);

		} catch (UsuarioException e) {
			throw e;
		} catch (CategoriaException e1) {
			throw e1;
		} catch (JuegoException e2) {
			throw e2;
		} catch (MiembroException e) {
			throw e;
		}

	}

	public void iniciarPartidaCerrada(UsuarioDTO u1, UsuarioDTO u2, UsuarioDTO u3, UsuarioDTO u4, GrupoDTO g)
			throws UsuarioException, CategoriaException, JuegoException, MiembroException, ParejaException,
			ManoException, CartaException, ChicoException, GrupoException {
		{
			try {

				Pareja p1 = this.armarParejaCerrada(u1, u2, g);
				Pareja p2 = this.armarParejaCerrada(u3, u4, g);
				GrupoJuego gj = new GrupoJuego(p1, p2);
				gj.setTipoJuego("CERRADA");
				ControladorJuego.getInstancia().iniciarJuego(gj);

			} catch (UsuarioException e) {
				throw e;
			} catch (CategoriaException e1) {
				throw e1;
			} catch (JuegoException e2) {
				throw e2;
			} catch (MiembroException e3) {
				throw e3;
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
			throws UsuarioException, CategoriaException, MiembroException, ParejaException, GrupoException {
		Grupo grupo = ControladorGrupo.getInstancia().buscarGrupo(g.getNombre());
		
		Usuario user1 = ControladorUsuario.getInstancia().buscarUsuarioPorId(u1.getIdUsuario());
		Usuario user2 = ControladorUsuario.getInstancia().buscarUsuarioPorId(u2.getIdUsuario());
		
		Miembro m1 = grupo.buscarMiembro(user1);
		Miembro m2 = grupo.buscarMiembro(user2);
		
		JugadorGrupal j1 = new JugadorGrupal(m1);
		JugadorGrupal j2 = new JugadorGrupal(m2);
		
		Pareja p = new Pareja(j1, j2);
		return p.saveGrupal();

	}

	public Pareja armarPareja(UsuarioDTO u1, UsuarioDTO u2)
			throws UsuarioException, CategoriaException, ParejaException {

		Usuario usuario1 = ControladorUsuario.getInstancia().buscarUsuarioPorId(u1.getIdUsuario());
		Usuario usuario2 = ControladorUsuario.getInstancia().buscarUsuarioPorId(u2.getIdUsuario());

		JugadorIndividual j1 = new JugadorIndividual(usuario1);
		JugadorIndividual j2 = new JugadorIndividual(usuario2);

		Pareja p = new Pareja(j1, j2);
		p.saveIndividual();
		return p;

	}
	
	public void cancelarEsperaJugador(UsuarioDTO usuario) throws JugadorException, UsuarioException, CategoriaException {
		
		JugadorIndividual jug = buscarJugadorEnEspera(usuario.getIdUsuario());
		
		if (jug != null) {
			ControladorArmadoJuegos.getInstancia().getJugadoresEnEspera().remove(jug);
		} else {
			throw new UsuarioException("No estas en la cola de espera para Juegos Individuales");
		}		
	}
	
	public JugadorIndividual buscarJugadorEnEspera(int idUsuario) {
		
		Vector<JugadorIndividual> jugadores = ControladorArmadoJuegos.getInstancia().getJugadoresEnEspera();
		
		for (JugadorIndividual jug : jugadores) {
			if (jug.getUsuario().sosElUsuario(idUsuario))
				return jug;
		}
		
		return null;
	}
	
	// La ParejaDTO la tenemos en el front porque al agregarla en la funcion retornamos la versión DTO de la misma
	public void cancelarEsperaPareja(ParejaDTO pareja) throws ParejaException {
		Pareja par = buscarParejaEnEspera (pareja.getIdPareja());
		
		if (par != null) {
			this.getParejasEnEspera().remove(par);
		} else {
			throw new ParejaException("Tu pareja no esta en la cola de espera para Juegos en Pareja");
		}
	}
	
	public Pareja buscarParejaEnEspera(int idPareja) throws ParejaException {
		for (Pareja par : parejasEnEspera) {
			if (par.esPareja(idPareja)) 
				return par;
		}
		return null;
	}
	
	public static ControladorArmadoJuegos getInstancia() {
		if (instancia == null) {
			instancia = new ControladorArmadoJuegos();
		}
		return instancia;
	}

	public List<JuegoDTO> getJuegosActivo(UsuarioDTO usuario) throws CategoriaException, UsuarioException, ParejaException {
		return ControladorJuego.getInstancia().getJuegosActivos(usuario);

	}
}
