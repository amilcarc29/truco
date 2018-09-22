package negocio;

import java.util.ArrayList;
import java.util.List;

public class Chico {
	private List<Mano> manos;
	private List<Pareja> parejas;
	private List<Puntuacion> puntos;
	private Pareja ganador;
	private int puntosPorGanar;
	private List<Jugador> jugadores;
	private int puntosParaTerminar;

	public Chico(List<Pareja> parejas) {
		this.manos = new ArrayList<>();
		this.parejas = parejas;
		this.puntos = new ArrayList<>();
		this.ganador = null;
		this.puntosPorGanar = 30;
	}

	public List<Puntuacion> getPuntos() {
		return puntos;
	}

	public void setPuntos(List<Puntuacion> puntos) {
		this.puntos = puntos;
	}

	public Pareja getGanador() {
		return ganador;
	}

	public void setGanador(Pareja ganador) {
		this.ganador = ganador;
	}

	public int getPuntosPorGanar() {
		return puntosPorGanar;
	}

	public void setPuntosPorGanar(int puntosPorGanar) {
		this.puntosPorGanar = puntosPorGanar;
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	public boolean finalizoChico() {

		return false;

	}

	public void finalizarChico() {

	}

	public int getPuntosParaTerminar() {
		return 0;

	}

	public void aumentarPuntosEnvidos(Envite envite, Pareja pareja) {

	}

	public void aumentarPuntosTruco(Truco truco, Pareja pareja) {

	}

	// TODO Agregar parámetro parejas a Diagrama.
	public void altaMano(List<Pareja> parejas, List<Jugador> jugadores, int puntosParaTerminar) {
		// FIXME Por qué parámetros? no debería usar las parejas, jugadores y
		// puntosPorTerminar del Chico?
		this.parejas = parejas;
		this.jugadores = jugadores;
		this.puntosParaTerminar = puntosParaTerminar;

		Mano mano = new Mano(parejas, jugadores, puntosParaTerminar);

		manos.add(mano);

		System.out.println("MANO NUM" + manos.size());
	}

	// TODO AGREGAR BUSCA UN JUGADOR EN UNA PAREJA

	public void cantarTruco(int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarTruco(idJugador);
	}

	public void cantarVale4(int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarVale4(idJugador);
	}

	public void cantarReTruco(int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarReTruco(idJugador);
	}

	public void cantarQuieroTruco(boolean quieroSiNo, int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarQuieroTruco(quieroSiNo, idJugador);
	}

	public void cantarQuieroEnvido(boolean quieroSiNo, int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarQuieroEnvido(quieroSiNo, idJugador);
	}

	public void cantarEnvido(int idJugador) {
		this.manos.get(this.manos.size() - 1).cantarEnvido(idJugador);

	}

	public void jugarCarta(int idJugador, int numero, String palo) {
		// TODO Auto-generated method stub

		this.manos.get(this.manos.size() - 1).jugarCarta(idJugador, numero, palo);

		if (this.manos.get(this.manos.size() - 1).finalizoMano())
			altaMano(parejas, jugadores, puntosParaTerminar);

	}

}
