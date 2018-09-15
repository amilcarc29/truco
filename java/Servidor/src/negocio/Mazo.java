package negocio;

import java.util.Random;
import java.util.Vector;

public class Mazo {

	private Vector<Carta> cartas;
	private static Mazo instancia = null;
	// TODO Agregar en Diagrama
	private Random generator;

	public Mazo() {
		// Agregar carta por carta o vemos de agregar todas las cartas en este constructor.
		cartas = new Vector<>();
		generator = new Random();
	}

	// TODO Cambiar a Public en Diagrama
	public Vector<Carta> getTresCartasRandom() {
		Vector<Carta> baraja = new Vector<>();
		for (int i = 1; i <= 3; i++) {
			int index = generator.nextInt(cartas.size());
			baraja.add(cartas.elementAt(index));
			cartas.removeElementAt(index);
		}
		return baraja;
	}

	public static Mazo getInstancia() {
		if (instancia == null) {
			instancia = new Mazo();
		}
		return instancia;
	}
}
