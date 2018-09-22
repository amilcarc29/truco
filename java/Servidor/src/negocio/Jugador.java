package negocio;

import java.util.LinkedList;
import java.util.List;

public abstract class Jugador {
	private String nombre = "";
	private int idJugador = 0;
	private List<Carta> cartas;

	private static int cnt = 0;

	private static int getID() {
		return cnt++;
	}

	public Jugador() {
		super();
		idJugador = getID();
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

	public Carta getCarta(int numero, String palo) {

		for (Carta carta : cartas) {
			if (carta.esCarta(numero, palo)) {
				this.cartas.remove(carta);
				return carta;
			}
		}
		return null;
	}

	public void mostrarCartas() {
		for (Carta carta : cartas) {
			System.out.println("Carta " + carta.getNumero() + " "+ carta.getPalo()  );
		}
	}

	public void setCartas(List<Carta> cartas) {

		this.cartas = cartas;
	}
}
