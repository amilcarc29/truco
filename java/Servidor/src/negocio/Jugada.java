package negocio;

import dao.JugadaDAO;
import dao.JugadorCartaDAO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;

public class Jugada {

	private int idJugada;
	private Jugador jugador;
	private Carta carta;
	
	// NO SE PARA QUE SE USA ORDEN
	private int orden;

	public Jugada(Jugador jugador, Carta carta) {
		super();
		setJugador(jugador);
		setCarta(carta);
	}

	public Jugada() {
	}

	public Jugador getJugador() {
		return jugador;
	}
	

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}
	public boolean esMayor(Jugada jugada) {
		return carta.esMayor(jugada.getCarta());

	}
	
	public int getIdJugada() {
		return idJugada;
	}

	public void setIdJugada(int idJugada) {
		this.idJugada = idJugada;
	}

	public void save(Baza baza) throws UsuarioException, CategoriaException {
		try {
			

			
			this.setIdJugada(JugadaDAO.getInstancia().guardarJugada(this, baza));
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
	}
}
