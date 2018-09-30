package negocio;

import java.util.LinkedList;
import java.util.List;

import excepciones.CartaException;
import excepciones.JugadorException;

public class Baza {
	private List<Jugador> jugadores;
	private List<Jugada> jugadas;
	private Jugada jugadaMayor = null;

	private boolean parda = false;
	private int numero;

	public Baza() {
		this.jugadas = new LinkedList<Jugada>();

	}

	public Jugada jugadaMayor() {
		return this.jugadaMayor;
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
		// TODO cuenta desde 0 las jugadas en la jugada simple son 4 maximo
		if (parda)
			return (this.jugadas.size() >= 3);
		return (this.jugadas.size() == 4);

	}

	public void finalizarBaza() {

	}

	public void cantarTruco(int idJugador) {
		// TODO qué hace esta función
		jugadores.stream().filter(jugador -> jugador.esJugador(idJugador)).findFirst().ifPresent(jugador -> {
			System.out.println("Jugador " + jugador.getNombre() + "cantó Truco.");
		});
	}

	public void cantarEnvido(int idJugador) {
		// TODO qué hace esta función
		jugadores.stream().filter(jugador -> jugador.esJugador(idJugador)).findFirst().ifPresent(jugador -> {
			System.out.println("Jugador " + jugador.getNombre() + "cantó Envido.");
		});

	}

	public void jugarCarta(int idJugador, int numero, String palo) throws JugadorException, CartaException {
		Jugador jugador = jugadores.get(idJugador);
		System.out.println(" Juega " + jugador.getNombre());
		Carta c = jugador.getCarta(numero, palo);

		Jugada jugada = new Jugada();
		jugada.setJugador(jugador);

		jugada.setCarta(c);
		this.jugadas.add(jugada);

		if (jugadaMayor == null)
			jugadaMayor = jugada;
		else {
			if (this.jugadaMayor.esMayor(jugada))
				this.jugadaMayor = jugada;
			System.out.println("jugada mayor " + this.jugadaMayor.getJugador().getNombre() + " ,  "
					+ this.jugadaMayor.getCarta().getNumero() + " " + this.jugadaMayor.getCarta().getPalo());
		}
		System.out.println("jugador " + jugador.getNombre() + " , jugo " + c.getNumero() + " " + c.getPalo());
		numero++;
	}

	private Jugador buscaJugador(int idJugador) throws JugadorException {
		for (Jugador j : this.jugadores) {
			if (j.esJugador(idJugador))
				return j;
		}
		throw new JugadorException("No se encontró al jugador " + idJugador);
	}
}
