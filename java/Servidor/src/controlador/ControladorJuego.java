package controlador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dao.CartaDAO;
import dao.JuegoDAO;
import dao.JugadorCartaDAO;
import dao.JugadorDAO;
import dao.UsuarioDAO;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.BazaException;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.ChicoException;
import excepciones.JuegoException;
import excepciones.JugadaException;
import excepciones.JugadorException;
import excepciones.ManoException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import negocio.Baza;
import negocio.Carta;
import negocio.Chico;
import negocio.FactoryJuegos;
import negocio.GrupoJuego;
import negocio.Juego;
import negocio.Jugador;
import negocio.JugadorIndividual;
import negocio.Mano;
import negocio.Pareja;
import negocio.Usuario;

public class ControladorJuego {

	private List<Juego> juegos;
	private static FactoryJuegos fcJuegos;
	private static ControladorJuego controlador;

	public static ControladorJuego getInstancia() throws UsuarioException, ParejaException, CategoriaException {
		if (controlador == null)
			controlador = new ControladorJuego();
		return controlador;
	}

	public ControladorJuego() throws UsuarioException, ParejaException, CategoriaException {
		juegos = new LinkedList<Juego>();
		try {
			juegos = JuegoDAO.getInstancia().getJuegosActivos();
		} catch (CategoriaException e) {
			throw e;
		}
		fcJuegos = new FactoryJuegos();
	}

	public void iniciarJuego(GrupoJuego grupo)
			throws JuegoException, UsuarioException, CategoriaException, ParejaException, MiembroException, ManoException, CartaException, ChicoException {
		Juego j = fcJuegos.getJuego(grupo.getParejas(), grupo.getTipoJuego());
		if (j != null) {

			j.save();
			// para tener el id el crear va despues del save
			j = JuegoDAO.getInstancia().buscarJuego(j.getId());
			j.crearChico();

			juegos.add(j);
			// imprimirDbg();
		}
	}

	public void finalizarJuego(int idJuego)
			throws JuegoException, UsuarioException, CategoriaException, ParejaException, MiembroException {
		// Finaliza el juego y actualiza la categoria del usuario
		buscarJuego(idJuego).finalizarJuego();
	}

	// --------------------------------------------------------------------------------------------------------------------------------

	/*
	 * FUNCIONES DE TRUCO
	 */


	public void cantarTruco(JuegoDTO juego, UsuarioDTO usuario) throws JuegoException, CategoriaException, UsuarioException, ManoException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarTruco();
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(jue.getId(), usuario.getIdUsuario());
		jue.setTieneQueContestar(jug, "TRUCO");
	}

	
	
	public void cantarReTruco(JuegoDTO juego, UsuarioDTO usuario) throws JuegoException, CategoriaException, UsuarioException, ManoException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarReTruco();
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(jue.getId(), usuario.getIdUsuario());
		jue.setTieneQueContestar(jug, "RE TRUCO");
	}


	public void cantarVale4(JuegoDTO juego, UsuarioDTO usuario) throws JuegoException, CategoriaException, UsuarioException, ManoException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarVale4();
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(jue.getId(), usuario.getIdUsuario());
		jue.setTieneQueContestar(jug, "VALE CUATRO");
	}
	
	public void quieroTruco(JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.inicializarContestar();
	}
	
	public void noQuieroTruco(UsuarioDTO usuario, JuegoDTO juego)
			throws JuegoException, CategoriaException, UsuarioException, ParejaException, ManoException, MiembroException, CartaException, ChicoException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(juego.getIdJuego(), usuario.getIdUsuario());
		Pareja parejaG = jue.obtenerParejaContraria(jug);

		jue.inicializarContestar();
		jue.aumentarPuntosTrucoNoQuerido(parejaG);

		if (jue.terminoUltimoChico()) {

			jue.finalizarUltimoChico();

			if (jue.terminoJuego()) {

				jue.finalizarJuego();

			} else {

				jue.armarNuevoChico();

			}
		}
	}

	/*
	 * FIN FUNCIONES DE TRUCO
	 */
	// --------------------------------------------------------------------------------------------------------------------------------
	/*
	 * FUNCIONES DE ENVIDO
	 */

	public void cantarEnvido(JuegoDTO juego, UsuarioDTO usuario) throws JuegoException, CategoriaException, UsuarioException, ManoException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarEnvido();
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(jue.getId(), usuario.getIdUsuario());
		
		jue.setTieneQueContestar(jug, "ENVIDO");
	}


	public void cantarRealEnvido(JuegoDTO juego, UsuarioDTO usuario) throws JuegoException, CategoriaException, UsuarioException, ManoException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarRealEnvido();
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(jue.getId(), usuario.getIdUsuario());
		jue.setTieneQueContestar(jug, "REAL ENVIDO");
	}


	public void cantarFaltaEnvido(JuegoDTO juego, UsuarioDTO usuario) throws JuegoException, CategoriaException, UsuarioException, ManoException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarFaltaEnvido();
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(jue.getId(), usuario.getIdUsuario());
		jue.setTieneQueContestar(jug, "FALTA ENVIDO");
	}

	public void quieroEnvido(JuegoDTO juego)
			throws JuegoException, CategoriaException, UsuarioException, ParejaException, ManoException, MiembroException, CartaException, ChicoException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.aumentarPuntosEnvidoQuerido();
		jue.inicializarContestar();

		if (jue.terminoUltimoChico()) {

			jue.finalizarUltimoChico();

			if (jue.terminoJuego()) {
				
				jue.finalizarJuego();

			} else {

				jue.armarNuevoChico();

			}
		}
	}

	public void noQuieroEnvido(JuegoDTO juego, UsuarioDTO usuario)
			throws JuegoException, CategoriaException, UsuarioException, ParejaException, ManoException, MiembroException, CartaException, ChicoException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(jue.getId(), usuario.getIdUsuario());
		Pareja parejaG = jue.obtenerParejaContraria(jug);
		jue.aumentarPuntosEnvidoNoQuerido(parejaG);
		jue.inicializarContestar();

		if (jue.terminoUltimoChico()) {

			jue.finalizarUltimoChico();

			if (jue.terminoJuego()) {

				jue.finalizarJuego();

			} else {

				jue.armarNuevoChico();

			}
		}
	}

	/*
	 * FIN FUNCIONES DE ENVIDO
	 */

	public Juego buscarJuego(int idJuego) throws JuegoException, CategoriaException, UsuarioException, ParejaException {
		return JuegoDAO.getInstancia().buscarJuego(idJuego);
	}

	public void jugarCarta(JuegoDTO juego, CartaDTO carta, UsuarioDTO usuario) throws JugadorException, CartaException,
			JuegoException, CategoriaException, UsuarioException, ParejaException, MiembroException, ManoException, BazaException, ChicoException, JugadaException {

		Juego jue = this.buscarJuego(juego.getIdJuego());
		Carta car = CartaDAO.getInstancia().buscarCartaPorID(carta.getIdCarta());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(juego.getIdJuego(), usuario.getIdUsuario());

		if (jug.isTieneTurno()) {
			jue.jugarCarta(car);

			Baza utlimaB = jue.getUltimaBaza();

			if (utlimaB.terminoBaza()) {

				Mano ultimaM = jue.getUltimaMano();

				if (ultimaM.terminoMano()) {

					Pareja pGanadora = ultimaM.obtenerParejaGanadora();
					Chico ultimoChico = jue.getUltimoChico();

					// aumenta los puntos del truco
					ultimoChico.aumentarPuntosTruco(pGanadora);

					// pregunta si termino el chico
					if (ultimoChico.terminoChico()) {

						ultimoChico.finalizarChico();

						if (jue.terminoJuego()) {

							jue.finalizarJuego();
							
						} else {

							jue.armarNuevoChico();

						}
					} else {

//						ultimoChico.setJugadores(JugadorDAO.getInstancia().getJugadores(jue.getId()));
						ultimoChico.armarNuevaMano();
					}

				} else {
					List<Jugador> nuevoOrdenJugadores = cambiarOrden(jue.getId(), jue.getUltimaBaza());
//					ultimaM.setJugadores(nuevoOrdenJugadores);
					ultimaM.armarNuevaBaza();
				}
			}
		}

	}

	public List<Jugador> cambiarOrden(int idJuego, Baza ultima) throws UsuarioException, CategoriaException {
		// preguntar quien gano , ponerlo adelante
		Jugador jugador = ultima.getJugadaMayor().getJugador();

		int i = 0;
		int j = 0;
		List<Jugador> jugadores = JugadorDAO.getInstancia().getJugadores(idJuego);
		for (int x = 0; x < jugadores.size(); x++) {

			if (jugadores.get(x).esJugador(jugador.getId()))
				break;

			j++;
		}

		List<Jugador> jugadoresNuevo = new ArrayList<Jugador>();

		jugadoresNuevo.add(jugador);

		while (i < 3) {
			j++;

			if (j > 3)
				j = 0;

			jugadoresNuevo.add(jugadores.get(j));

			i++;

		}
		jugadores = jugadoresNuevo;

		JugadorDAO.getInstancia().actualizarTurnos(jugadores);

		return jugadores;
	}

	public boolean sePuedeTruco(int idJuego) throws JuegoException {
		return true;
	}

	public boolean sePuedeCantarEnvido(int idJuego) throws JuegoException, CategoriaException, UsuarioException, ParejaException {
		Juego j = this.buscarJuego(idJuego);
		return j.sePuedeCantarEnvido();
	}

	// REVISAR PARA QUE ESTA
	public void imprimirDbg() throws JuegoException, CategoriaException, UsuarioException, ParejaException {
		for (Juego juego : juegos) {
			List<Pareja> par = juego.getParejas();
			for (Pareja p : par) {
				// CONFLICTOS CON NOMBRE JUGADOR
				System.out.println("Pareja num " + p.getIdPareja());
				for (Jugador j : p.getJugadores()) {
					System.out.println(((JugadorIndividual) j).getUsuario().getApodo());
					j.dbgCartas();
				}
				if (this.sePuedeCantarEnvido(juego.getId())) {
					System.out.println("Tanto para envido " + p.getMayorTantoEnvido());
					System.out.println("Tanto para truco " + p.getMayorTantoTruco());
				}
				System.out.println("");
			}
			// System.out.println("juega " + juego.proximoDbg().getNombre());
			System.out.println("");
		}
	}

	public List<JuegoDTO> getJuegosActivos(UsuarioDTO usuario) throws CategoriaException, UsuarioException, ParejaException {
		List<Juego> juegos = JuegoDAO.getInstancia().buscarJuegosActivos(usuario);
		List<JuegoDTO> juegosDto = new ArrayList<>();
		for (Juego j : juegos) {
			juegosDto.add(j.toDTO());
		}
		return juegosDto;
	}
	
	public String responderJugador(JuegoDTO juego, UsuarioDTO usuario)
			throws CategoriaException, UsuarioException, JuegoException, ParejaException {
		Juego ju = JuegoDAO.getInstancia().buscarJuego(juego.getIdJuego());
		Usuario us = UsuarioDAO.getInstancia().buscarUsuarioById(usuario.getIdUsuario());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(ju.getId(), us.getIdUsuario());

		return jug.getTanto();
	}

	public boolean turnoJugador(JuegoDTO juego, UsuarioDTO usuario)
			throws CategoriaException, UsuarioException, JuegoException, ParejaException {
		Juego ju = JuegoDAO.getInstancia().buscarJuego(juego.getIdJuego());
		Usuario us = UsuarioDAO.getInstancia().buscarUsuarioById(usuario.getIdUsuario());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(ju.getId(), us.getIdUsuario());

		return jug.isTieneTurno();
	}

	public List<CartaDTO> getCartas(JuegoDTO juego, UsuarioDTO usuario, boolean jugada)
			throws CategoriaException, UsuarioException, JuegoException, ParejaException {
		Juego ju = JuegoDAO.getInstancia().buscarJuego(juego.getIdJuego());
		Usuario us = UsuarioDAO.getInstancia().buscarUsuarioById(usuario.getIdUsuario());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(ju.getId(), us.getIdUsuario());
		
		List<Carta> cartas = JugadorCartaDAO.getInstancia().getCartasbyJugador(jug,ju.getUltimaMano(), jugada);
		List<CartaDTO> cartasDto = new ArrayList<>();

		for (Carta carta : cartas) {
			cartasDto.add(carta.toDTO());
		}
		return cartasDto;
	}

	public boolean alguienTieneQueContestar(JuegoDTO juego) throws CategoriaException, JuegoException, UsuarioException, ParejaException {
		Juego ju = JuegoDAO.getInstancia().buscarJuego(juego.getIdJuego());
		return ju.alguienTieneQueContestar();
	}
	
	public void salirJuego(JuegoDTO juego, UsuarioDTO usuario) throws CategoriaException, JuegoException, UsuarioException, ParejaException, ChicoException, MiembroException {
		Juego ju = JuegoDAO.getInstancia().buscarJuego(juego.getIdJuego());
		Usuario us = UsuarioDAO.getInstancia().buscarUsuarioById(usuario.getIdUsuario());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(ju.getId(), us.getIdUsuario());
		
		ju.concederJuego(jug);
	}

	
	
}
