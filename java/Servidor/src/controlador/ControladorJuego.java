package controlador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dao.CartaDAO;
import dao.JuegoDAO;
import dao.JugadorCartaDAO;
import dao.JugadorDAO;
import dao.ParejaDAO;
import dao.UsuarioDAO;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.JuegoException;
import excepciones.JugadorException;
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

	public static ControladorJuego getInstancia() throws UsuarioException {
		if (controlador == null)
			controlador = new ControladorJuego();
		return controlador;
	}

	public ControladorJuego() throws UsuarioException {
		juegos = new LinkedList<Juego>();
		try {
			juegos = JuegoDAO.getInstancia().getJuegosActivos();
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fcJuegos = new FactoryJuegos();
	}

	public void iniciarJuego(GrupoJuego grupo)
			throws JuegoException, UsuarioException, CategoriaException, ParejaException, MiembroException {
		Juego j = fcJuegos.getJuego(grupo.getParejas(), grupo.getTipoJuego());
		if (j != null) {
			// Creo que se debería crear el chico en el constructor de Juego y no aca (VER)
			j.save();
			// para tener el id el crear va despues del save

			j.crearChico();

			JugadorDAO.getInstancia().setTurnoPrimero(j);

			juegos.add(j);
			// imprimirDbg();
		}
	}

	public void finalizarJuego(int idJuego) throws JuegoException, UsuarioException, CategoriaException, ParejaException, MiembroException {
		buscarJuego(idJuego).finalizarJuego();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	
	/* FUNCIONES DE TRUCO
	 * FALTA HACER LA PARTE DE LA BD. TRAER EL TRUCO AL LEVANTAR UN JUEGO Y ACTUALIZAR EL TRUCO EN LA BD
	*/

	// ESTA FUNCION ES PARA CUANDO DICEN QUIERO A UN TRUCO => AUMENTA EL VALOR DEL TRUCO A 2
	public void cantarTruco(JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarTruco();
	}
	
	// ESTA FUNCION ES PARA CUANDO DICEN QUIERO A UN RETRUCO => AUMENTA EL VALOR DEL TRUCO A 3
	public void cantarReTruco(JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarTruco();
		jue.cantarReTruco();
	}

	// ESTA FUNCION ES PARA CUANDO DICEN QUIERO A UN VALECUATRO => AUMENTA EL VALOR DEL TRUCO A 4
	public void cantarVale4(JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarTruco();
		jue.cantarReTruco();
		jue.cantarVale4();
	}
	
	
	// ESTO CREO QUE NO HACE FALTA. CUANDO DICEN QUIERO SE DISPARAN LAS FUNCIONES cantar...
//	public void cantarQuieroTruco(int idJuego, boolean quieroSiNo)
//			throws JuegoException, CategoriaException, UsuarioException {
//		// TODO Auto-generated method stub
//		Juego j = this.buscarJuego(idJuego);
//		j.cantarQuieroTruco(quieroSiNo);
//	}
	
	
	// ESTA FUNCION ES PARA CUANDO DICEN NO QUIERO A UN TRUCO (VALE 1 PUNTO)
	public void noQuieroTruco(UsuarioDTO usuario, JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(juego.getIdJuego(), usuario.getIdUsuario());
		Pareja parejaG = jue.obtenerParejaContraria(jug);
		//ESTA FUNCION NO HACE FALTA. SOLO HAY QUE DAR PUNTOS. EL TRUCO YA VALE 1
		//jue.noQuieroTruco();
		jue.aumentarPuntosTruco(parejaG);		

		if (jue.terminoUltimoChico()) {
			
			jue.finalizarUltimoChico();

			if (jue.terminoJuego()) {
				
				// FALTA HACER LA FINALIZACION DEL JUEGO
				// ACTUALIZACION DE USUARIOS

			} else {				
				
				jue.armarNuevoChico();
				
			}
		}
	}
	
	// ESTA FUNCION ES PARA CUANDO DICEN NO QUIERO A UN RETRUCO (VALE 2 PUNTOS)
	public void noQuieroReTruco(UsuarioDTO usuario, JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(juego.getIdJuego(), usuario.getIdUsuario());
		Pareja parejaG = jue.obtenerParejaContraria(jug);
		jue.noQuieroReTruco();

		jue.aumentarPuntosTruco(parejaG);
		
		if (jue.terminoUltimoChico()) {
			
			jue.finalizarUltimoChico();

			if (jue.terminoJuego()) {
				
				// FALTA HACER LA FINALIZACION DEL JUEGO
				// ACTUALIZACION DE USUARIOS

			} else {				
				
				jue.armarNuevoChico();
				
			}
		}
	}
	
	// ESTA FUNCION ES PARA CUANDO DICEN NO QUIERO A UN VALECUATRO (VALE 3 PUNTOS)
	public void noQuieroValeCuatro(UsuarioDTO usuario, JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(juego.getIdJuego(), usuario.getIdUsuario());
		Pareja parejaG = jue.obtenerParejaContraria(jug);
		jue.noQuieroValeCuatro();
		jue.aumentarPuntosTruco(parejaG);
		
		if (jue.terminoUltimoChico()) {
			
			jue.finalizarUltimoChico();

			if (jue.terminoJuego()) {
				
				// FALTA HACER LA FINALIZACION DEL JUEGO
				// ACTUALIZACION DE USUARIOS

			} else {				
				
				jue.armarNuevoChico();
				
			}
		}
	}
	/*
	 * FIN FUNCIONES DE TRUCO
	*/
	//--------------------------------------------------------------------------------------------------------------------------------
	/* 
	 * FUNCIONES DE ENVIDO
	*/
	public void cantarEnvido(JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarEnvido();
	}
	
	public void cantarRealEnvido(JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarRealEnvido();
	}
	
	public void cantarFaltaEnvido(JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.cantarFaltaEnvido();
	}
	
	public void quieroEnvido(JuegoDTO juego) throws JuegoException, CategoriaException, UsuarioException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		jue.aumentarPuntosEnvidoQuerido();
		
		if (jue.terminoUltimoChico()) {
			
			jue.finalizarUltimoChico();

			if (jue.terminoJuego()) {
				
				// FALTA HACER LA FINALIZACION DEL JUEGO
				// ACTUALIZACION DE USUARIOS

			} else {				
				
				jue.armarNuevoChico();
				
			}
		}
	}
	
	public void noQuieroEnvido(JuegoDTO juego, UsuarioDTO usuario) throws JuegoException, CategoriaException, UsuarioException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(jue.getId(), usuario.getIdUsuario());
		Pareja parejaG = jue.obtenerParejaContraria(jug);
		jue.aumentarPuntosEnvidoNoQuerido(parejaG);
		
		if (jue.terminoUltimoChico()) {
			
			jue.finalizarUltimoChico();

			if (jue.terminoJuego()) {
				
				// FALTA HACER LA FINALIZACION DEL JUEGO
				// ACTUALIZACION DE USUARIOS

			} else {				
				
				jue.armarNuevoChico();
				
			}
		}
	}
	
	
	/*
	 * FIN FUNCIONES DE ENVIDO
	*/

	public Juego buscarJuego(int idJuego) throws JuegoException, CategoriaException, UsuarioException {
		return JuegoDAO.getInstancia().buscarJuego(idJuego);
	}


	public void jugarCarta(JuegoDTO juego, CartaDTO carta, UsuarioDTO usuario) throws JugadorException, CartaException,
			JuegoException, CategoriaException, UsuarioException, ParejaException {
		Juego jue = this.buscarJuego(juego.getIdJuego());
		Carta car = CartaDAO.getInstancia().buscarCartaPorID(carta.getIdCarta());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(juego.getIdJuego(), usuario.getIdUsuario());

		if (jug.isTieneTurno()) {
			jue.jugarCarta(car);

			Mano utlimaB = jue.getUltimaMano();
			
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
							
						
						}else {
							
							
							jue.armarNuevoChico();
							
						}
					} else {
						ultimoChico.armarNuevaMano();
					}

				} else {

					ultimaM.armarNuevaBaza();

				}
			}
		}

	}

	public boolean sePuedeTruco(int idJuego) throws JuegoException {
		return true;
	}

	public boolean sePuedeCantarEnvido(int idJuego) throws JuegoException, CategoriaException, UsuarioException {
		Juego j = this.buscarJuego(idJuego);
		return j.sePuedeCantarEnvido();
	}

	public void imprimirDbg() throws JuegoException, CategoriaException, UsuarioException {
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

	public List<JuegoDTO> getJuegosActivos(UsuarioDTO usuario) throws CategoriaException, UsuarioException {
		List<Juego> juegos = JuegoDAO.getInstancia().buscarJuegosActivos(usuario);
		List<JuegoDTO> juegosDto = new ArrayList<>();
		for (Juego j : juegos) {
			juegosDto.add(j.toDTO());
		}
		return juegosDto;
	}

	public boolean turnoJugador(JuegoDTO juego, UsuarioDTO usuario)
			throws CategoriaException, UsuarioException, JuegoException {
		Juego ju = JuegoDAO.getInstancia().buscarJuego(juego.getIdJuego());
		Usuario us = UsuarioDAO.getInstancia().buscarUsuarioById(usuario.getIdUsuario());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(ju.getId(), us.getIdUsuario());

		return jug.isTieneTurno();
	}

	public List<CartaDTO> getCartas(JuegoDTO juego, UsuarioDTO usuario)
			throws CategoriaException, UsuarioException, JuegoException {
		Juego ju = JuegoDAO.getInstancia().buscarJuego(juego.getIdJuego());
		Usuario us = UsuarioDAO.getInstancia().buscarUsuarioById(usuario.getIdUsuario());
		Jugador jug = JugadorDAO.getInstancia().buscarJugadorByUsario(ju.getId(), us.getIdUsuario());

		List<Carta> cartas = JugadorCartaDAO.getInstancia().getCartasbyJugador(jug);
		List<CartaDTO> cartasDto = new ArrayList<>();

		for (Carta carta : cartas) {
			cartasDto.add(carta.toDTO());
		}
		return cartasDto;
	}
}
