package controlador;

import java.util.LinkedList;
import java.util.List;

import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.JuegoException;
import excepciones.JugadorException;
import excepciones.UsuarioException;
import negocio.FactoryJuegos;
import negocio.GrupoJuego;
import negocio.Juego;
import negocio.Jugador;
import negocio.JugadorIndividual;
import negocio.Pareja;

public class ControladorJuego {

	private List<Juego> juegos;
	private static FactoryJuegos fcJuegos;
	private static ControladorJuego controlador;

	public static ControladorJuego getInstancia() {
		if (controlador == null)
			controlador = new ControladorJuego();
		return controlador;
	}

	public ControladorJuego() {
		juegos = new LinkedList<Juego>();
		fcJuegos = new FactoryJuegos();
	}

	public void iniciarJuego(GrupoJuego grupo) throws JuegoException, UsuarioException, CategoriaException {
		Juego j = fcJuegos.getJuego(grupo.getTipoJuego());
		if (j != null) {
			j.setParejas(grupo.getParejas());
			j.crearChico();
			j.save("LIBRE");
			juegos.add(j);
			imprimirDbg();
		}
	}

	public void cantarTruco(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		j.cantarTruco();

	}

	public void cantarReTruco(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		j.cantarReTruco();

	}

	public void cantarVale4(int idJuego, int idJugador) throws JuegoException {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.cantarVale4(idJugador);
	}

	public void cantarQuieroTruco(int idJuego, boolean quieroSiNo) throws JuegoException {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.cantarQuieroTruco(quieroSiNo);
	}

	public void cantarQuieroEnvido(int idJuego, boolean quieroSiNo) throws JuegoException {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.cantarQuieroEnvido(quieroSiNo);
	}

	public void cantarEnvido(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		j.cantarEnvido();
	}

	// TODO AGREGAR
	public void aceptarTruco(int idJuego, int idJugador) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
	}

	public Juego buscarJuego(int idJuego) throws JuegoException {
		for (Juego juego : juegos) {
			if (juego.sosJuego(idJuego))
				return juego;
		}
		throw new JuegoException("El juego " + idJuego + "no existe.");
	}

	public void jugarCarta(int idJuego, int numero, String palo)
			throws JugadorException, CartaException, JuegoException {
		Juego j = this.buscarJuego(idJuego);
		j.jugarCarta(numero, palo);
	}

	public boolean verificarFinJuego(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		return j.verificarFinJuego();
	}

	public boolean terminoMano(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		return j.terminoMano();
	}

	public void sinCantar(int idJuego) throws JuegoException {
		// TODO Auto-generated method stub
		Juego j = this.buscarJuego(idJuego);
		j.sinCantar();

	}

	public boolean sePuedeTruco(int idJuego) throws JuegoException {
		return true;
	}

	public boolean sePuedeCantarEnvido(int idJuego) throws JuegoException {
		Juego j = this.buscarJuego(idJuego);
		return j.sePuedeCantarEnvido();
	}

	public void imprimirDbg() throws JuegoException {
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
				juego.puntosDbg(p.getIdPareja());

				System.out.println("");

			}
			// System.out.println("juega " + juego.proximoDbg().getNombre());
			System.out.println("");
		}
	}

}
