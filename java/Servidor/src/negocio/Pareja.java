package negocio;

import java.util.LinkedList;
import java.util.List;

public class Pareja {
	private int idPareja = 0;
	private List<Jugador> jugadores;
	
	private static int cnt = 0;

	private static int getID() {
		return cnt++;
	}
	
	public Pareja() {
		idPareja = getID();
		jugadores = new LinkedList<Jugador>();
	}

	public Categoria obtenerMayorCategoria() {
		return null;
	}

	public int getIdPareja() {
		return idPareja;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public boolean tieneLugar() {
		return (jugadores != null) && (jugadores.size() < 2);
	}

	// TODO addJugadores YA NO ESTA
	public void addJugador(Jugador jugador) {
		if (this.jugadores.size() < 2)
			this.jugadores.add(jugador);
	}

	// TODO tiene jugador
	
	public boolean tieneJugador(int idJugador) {
		for (Jugador jug : jugadores) {
			if (jug.esJugador(idJugador))
				return true;
		}
		return false;
	}

	// TODO Agregar a Diagrama.
	public boolean esPareja(int idPareja) {
		return getIdPareja() == idPareja;
	}
	public int getMayorTantoTruco() {
		int mayor = 0;
		for (Jugador jug : jugadores) {
			if (mayor<jug.getTantoTruco())
				mayor = jug.getTantoTruco();
		}
		return mayor;
	}
	public int getMayorTantoEnvido() {
		int mayor = 0;
		for (Jugador jug : jugadores) {
			if (mayor<jug.getTantoEnvido())
				mayor = jug.getTantoEnvido();
		}
		return mayor;
	}
}
