package negocio;

public class JugadorCarta {
	private Jugador jugador;
	private Carta carta;
	private boolean cartaJugada;

	public JugadorCarta(Jugador jugador, Carta carta, boolean cartaJugada) {

		this.jugador = jugador;
		this.carta = carta;
		this.cartaJugada = cartaJugada;
	}

}
