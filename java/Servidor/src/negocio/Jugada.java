package negocio;

public class Jugada {

	private Jugador jugador;
	private Carta carta;
	private int orden;

	public Jugada(Jugador jugador, Carta carta, int orden) {
		super();
		setJugador(jugador);
		setCarta(carta);
		setOrden(orden);
	}

	public Jugada() {
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public boolean esMayor(Jugada jugada) {

		return carta.esMayor(jugada.getCarta());

	}

}
