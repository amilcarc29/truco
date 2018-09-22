package negocio;

import java.util.LinkedList;
import java.util.List;

public abstract class Jugador {
	private String nombre = "";
	private static int idJugador = 0;
	private List<Carta> cartas;

	public Jugador() {
		super();
		idJugador++;
		cartas = new LinkedList<Carta>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	// TODO agregar

	public boolean esJugador(int idjugador) {

		return (idJugador == idjugador);
	}

	public boolean tieneCartas() {

		return (cartas.size() > 0);
	}

	public Carta getCarta(int idCarta) {
		for (Carta carta : cartas) {
			if (carta.esCarta(idCarta)) {
				this.cartas.remove(carta);
				return carta;
			}
		}
		return null;
	}

	public void setCartas(List<Carta> cartas) {
		this.cartas = cartas;
	}
}
