package negocio;

import java.util.LinkedList;
import java.util.List;

public class Baza {
	private List<Jugador> jugadores;
	private List<Jugada> jugadas;
	private Jugador jugadaMayor;
	private boolean parda;
	private int numero;

	public Baza() {
		this.jugadas = new LinkedList<Jugada>();

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

	public boolean finalizoBaza() {
		return (this.jugadas.size() == 4);

	}

	public void finalizarBaza() {

	}

	public void cantarTruco(int idJugador) {
		// TODO Auto-generated method stub

	}

	public void cantarEnvido(int idJugador) {
		// TODO Auto-generated method stub

	}

	public void jugarCarta(int idJugador, int numero, String palo) {
		// TODO Auto-generated method stub
		Jugador jugador = this.buscaJugador(idJugador);
		Carta c = jugador.getCarta(numero, palo);

		if ((jugador == null) || (c == null)) {

			System.out.println("NO se encuentra la carta o el jugador");
		} else {
			Jugada jugada = new Jugada();
			jugada.setJugador(jugador);

			jugada.setCarta(c);
			this.jugadas.add(jugada);
			System.out.println("jugador " + jugador.getNombre() + " , jugo " + c.getNumero() + " " + c.getPalo());
		}

	}

	private Jugador buscaJugador(int idJugador) {
		for (Jugador j : this.jugadores) {
			if (j.esJugador(idJugador))
				return j;

		}
		return null;
	}

}
