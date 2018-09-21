package negocio;

import java.util.List;

public class Baza {
	private int idBaza;
	private List<Jugador> jugadores;
	private List<Jugada> jugadas;
	private Jugador jugadaMayor;
	private boolean parda;
	private int numero;

	public int getIdBaza() {
		return idBaza;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Jugada> getJugadas() {
		return jugadas;
	}

	public void setJugadas(List<Jugada> jugadas) {
		this.jugadas = jugadas;
	}

	public Jugador getJugadaMayor() {
		return jugadaMayor;
	}

	public void setJugadaMayor(Jugador jugadaMayor) {
		this.jugadaMayor = jugadaMayor;
	}

	public boolean isParda() {
		return parda;
	}

	public void setParda(boolean parda) {
		this.parda = parda;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean controlarFinalizacion() {
		return false;

	}

	public void finalizarBaza() {

	}

	public void cantarTruco(int idJugador) {
		// TODO Auto-generated method stub

	}

	public void cantarEnvido(int idJugador) {
		// TODO Auto-generated method stub

	}
}
