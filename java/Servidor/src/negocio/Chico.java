package negocio;

import java.util.ArrayList;
import java.util.List;

public class Chico {
	private int idChico;
	private List<Mano> manos;
	private List<Pareja> parejas;
	private List<Puntuacion> puntos;
	private Pareja ganador;
	private int puntosPorGanar;
	
	public Chico(List<Pareja> parejas) {
		this.manos = new ArrayList<>();
		this.parejas = parejas;
		this.puntos = new ArrayList<>();
		this.ganador = null;
		this.puntosPorGanar = 30;
	}
	
	public int getIdChico() {
		return idChico;
	}

	public List<Mano> getManos() {
		return manos;
	}

	public void setManos(List<Mano> manos) {
		this.manos = manos;
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

	public boolean verificarChico() {
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
		// FIXME Por qué parámetros? no debería usar las parejas, jugadores y puntosPorTerminar del Chico?
		Mano mano = new Mano(parejas, jugadores, puntosParaTerminar);
		manos.add(mano);
	}
}
