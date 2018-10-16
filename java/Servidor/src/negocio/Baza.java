package negocio;

import java.util.LinkedList;
import java.util.List;

import excepciones.CartaException;
import excepciones.JugadorException;

public class Baza {
	
	private int idBaza;
	private List<Jugador> jugadores;
	private List<Jugada> jugadas;
	private Jugada jugadaMayor = null;

	private boolean parda = false;
	private int numero;
	
	

	public Baza(List<Jugador> jugadores) {
		super();
		this.jugadores = jugadores;
	}

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
		// Conflicto con el nombre del jugador
		// jugadores.stream().filter(jugador ->
		// jugador.esJugador(idJugador)).findFirst().ifPresent(jugador -> {
		// System.out.println("Jugador " + jugador.getNombre() + "cantó Truco.");
		// });
	}

	public void cantarEnvido(int idJugador) {
		// Conflicto con el nombre del jugador
		// jugadores.stream().filter(jugador ->
		// jugador.esJugador(idJugador)).findFirst().ifPresent(jugador -> {
		// System.out.println("Jugador " + jugador.getNombre() + "cantó Envido.");
		// });
	}

	public void jugarCarta(int indiceJugador, int numero, String palo) throws JugadorException, CartaException {
		Jugador jugador = jugadores.get(indiceJugador);
		// solo para debug
		if (jugadores.size() > (indiceJugador + 1)) {
			Jugador nextj = jugadores.get(indiceJugador + 1);
		}
		// solo para debug
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
			// Conflicto con nombre de Jugador
			// System.out.println("jugada mayor " +
			// jugadores.get(this.jugadaMayor.getJugador().getId()).getNombre() + " , "
			// + this.jugadaMayor.getCarta().getNumero() + " " +
			// this.jugadaMayor.getCarta().getPalo());
		}
		numero++;
	}

	public int getIdBaza() {
		return idBaza;
	}

	public void setIdBaza(int idBaza) {
		this.idBaza = idBaza;
	}

	public Jugada getJugadaMayor() {
		return jugadaMayor;
	}

	public void setJugadaMayor(Jugada jugadaMayor) {
		this.jugadaMayor = jugadaMayor;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}
	
	
}
