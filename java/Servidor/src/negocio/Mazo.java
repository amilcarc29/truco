package negocio;

import java.util.Random;
import java.util.Vector;

public class Mazo {

	private static Vector<Carta> cartas;
	private static Mazo instancia = null;
	// TODO Agregar en Diagrama
	private Random generator;

	private static String ESPADA = "E";
	private static String BASTO = "B";
	private static String ORO = "O";
	private static String COPA = "C";

	private static void generarCartas() {

		cartas.add(new Carta(1, ESPADA, 14, 1));
		cartas.add(new Carta(1, BASTO, 13, 1));

		cartas.add(new Carta(7, ESPADA, 12, 7));
		cartas.add(new Carta(7, ORO, 11, 7));

		cartas.add(new Carta(3, ESPADA, 10, 3));
		cartas.add(new Carta(3, BASTO, 10, 3));
		cartas.add(new Carta(3, ORO, 10, 3));
		cartas.add(new Carta(3, COPA, 10, 3));

		cartas.add(new Carta(2, ESPADA, 9, 2));
		cartas.add(new Carta(2, BASTO, 9, 2));
		cartas.add(new Carta(2, ORO, 9, 2));
		cartas.add(new Carta(2, COPA, 9, 2));

		cartas.add(new Carta(1, COPA, 8, 1));
		cartas.add(new Carta(1, ORO, 8, 1));

		cartas.add(new Carta(12, ESPADA, 7, 0));
		cartas.add(new Carta(12, BASTO, 7, 0));
		cartas.add(new Carta(12, ORO, 7, 0));
		cartas.add(new Carta(12, COPA, 7, 0));

		cartas.add(new Carta(11, ESPADA, 6, 0));
		cartas.add(new Carta(11, BASTO, 6, 0));
		cartas.add(new Carta(11, ORO, 6, 0));
		cartas.add(new Carta(11, COPA, 6, 0));

		cartas.add(new Carta(10, ESPADA, 5, 0));
		cartas.add(new Carta(10, BASTO, 5, 0));
		cartas.add(new Carta(10, ORO, 5, 0));
		cartas.add(new Carta(10, COPA, 5, 0));

		cartas.add(new Carta(7, BASTO, 4, 7));
		cartas.add(new Carta(7, COPA, 4, 7));

		cartas.add(new Carta(6, ESPADA, 3, 6));
		cartas.add(new Carta(6, BASTO, 3, 6));
		cartas.add(new Carta(6, ORO, 3, 6));
		cartas.add(new Carta(6, COPA, 3, 6));

		cartas.add(new Carta(5, ESPADA, 2, 5));
		cartas.add(new Carta(5, BASTO, 2, 5));
		cartas.add(new Carta(5, ORO, 2, 5));
		cartas.add(new Carta(5, COPA, 2, 5));

		cartas.add(new Carta(4, ESPADA, 1, 4));
		cartas.add(new Carta(4, BASTO, 1, 4));
		cartas.add(new Carta(4, ORO, 1, 4));
		cartas.add(new Carta(4, COPA, 1, 4));

	}

	public Mazo() {
		// Agregar carta por carta o vemos de agregar todas las cartas en este
		// constructor.
		cartas = new Vector<>();
		generator = new Random();
		generarCartas();


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
