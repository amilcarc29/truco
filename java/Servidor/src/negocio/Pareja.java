package negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pareja {
	private int idPareja = 0;
	private List<Jugador> jugadores;
	public Pareja() {
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

}
