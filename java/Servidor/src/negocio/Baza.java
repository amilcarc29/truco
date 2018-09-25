package negocio;

import java.util.LinkedList;
import java.util.List;

public class Baza {
	private List<Jugador> jugadores;
	private List<Jugada> jugadas;
	private Jugada jugadaMayor = null;
	
	
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
		//TODO cuenta desde 0 las jugadas en la jugada simple son 4 maximo
		return (this.jugadas.size() == 3);

	}

	public void finalizarBaza() {

	}

	public void cantarTruco(int idJugador) {
		// TODO qué hace esta función
		jugadores.stream()
			.filter(jugador -> jugador.esJugador(idJugador))
			.findFirst()
			.ifPresent(jugador -> {
				System.out.println("Jugador " + jugador.getNombre() + "cantó Truco.");
			});
	}

	public void cantarEnvido(int idJugador) {
		// TODO qué hace esta función
		jugadores.stream()
		.filter(jugador -> jugador.esJugador(idJugador))
		.findFirst()
		.ifPresent(jugador -> {
			System.out.println("Jugador " + jugador.getNombre() + "cantó Envido.");
		});

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

	}

	private Jugador buscaJugador(int idJugador) {
		for (Jugador j : this.jugadores) {
			if (j.esJugador(idJugador))
				return j;

		}
		return null;
	}

}
