package negocio;

import java.util.LinkedList;
import java.util.List;

import excepciones.CartaException;

public abstract class Jugador {
	private String nombre = "";
	private int idJugador = 0;
	private List<Carta> cartas;

	private static int cnt = 0;

	private static int getID() {
		return cnt++;
	}

	public Jugador(String nombre) {
		super();
		setNombre(nombre);
		// TODO levantar el id de la bd.
		this.idJugador = 0;
		cartas = new LinkedList<>();
	}

	public Jugador() {
		super();
		idJugador = getID();
		cartas = new LinkedList<Carta>();
	}
	
	public int getId(){
		return this.idJugador;
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

	public Carta getCarta(int numero, String palo) throws CartaException {

		for (Carta carta : cartas) {
			if (carta.esCarta(numero, palo)) {
				this.cartas.remove(carta);
				return carta;
			}
		}

		throw new CartaException("No se encontró a la carta: " + numero + " de " + palo);
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
