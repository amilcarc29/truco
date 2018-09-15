package negocio;

import java.util.List;

public class Chico {
	private int idChico;
	private List<Mano> manos;
	private List<Puntuacion> puntos;
	private Pareja ganador;
	private int puntosPorGanar;

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

	public void altaMano(List<Jugador> jugadores, int puntosParaTerminar) {

	}
}
