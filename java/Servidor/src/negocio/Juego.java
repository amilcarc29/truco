package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Juego {
	private static int cnt = 0;
	private int idJuego;
	private List<Pareja> parejas;
	private List<Chico> chicos;
	private Pareja ganador;
	private int puntoBase;
	private Date fecha;
	private boolean activo;

	private static int getID() {
		return cnt++;
	}

	public Juego() {
		super();
		this.parejas = new ArrayList<>();
		this.chicos = new ArrayList<>();
		setPuntoBase(0);
		this.fecha = new Date();
		setActivo(true);
		this.idJuego = getID();
	}

	public abstract void calcularPuntos();

	public boolean sosJuego(int id) {
		return (this.idJuego == id);
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	public Pareja getGanador() {
		return ganador;
	}

	public void setGanador(Pareja ganador) {
		this.ganador = ganador;
	}

	public int getPuntoBase() {
		return puntoBase;
	}

	public void setPuntoBase(int puntoBase) {
		this.puntoBase = puntoBase;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Pareja obtenerGanador() {
		return null;

	}

	public boolean termino() {
		return false;
	}

	public void finalizarJuego() {

	}

	public Categoria obtenerCategoriaMayor() {
		return null;

	}

	// TODO Agregar a Diagrama.
	public void crearChico() {
		List<Jugador> jugadores = new ArrayList<Jugador>();

		for (int i = 0; i < this.parejas.size(); i++) {

			jugadores.addAll(this.parejas.get(i).getJugadores());
		}
		Chico chico = new Chico(parejas);
		chico.altaMano(parejas, jugadores, 0);
		chicos.add(chico);
	}

	// TODO Agregar a Diagrama.

	public void cantarTruco(int idJugador) {

		chicos.get(chicos.size() - 1).cantarTruco(idJugador);

	}

	public void cantarReTruco(int idJugador) {

		chicos.get(chicos.size() - 1).cantarReTruco(idJugador);

	}

	public void cantarVale4(int idJugador) {

		chicos.get(chicos.size() - 1).cantarVale4(idJugador);

	}

	public void cantarQuieroEnvido(boolean quieroSiNo, int idJugador) {
		// TODO Auto-generated method stub
		chicos.get(chicos.size() - 1).cantarQuieroEnvido(quieroSiNo, idJugador);
	}

	public void cantarQuieroTruco(boolean quieroSiNo, int idJugador) {
		// TODO Auto-generated method stub
		chicos.get(chicos.size() - 1).cantarQuieroTruco(quieroSiNo, idJugador);
	}

	public void cantarEnvido(int idJugador) {
		chicos.get(chicos.size() - 1).cantarEnvido(idJugador);

	}

	public void jugarCarta(int idJugador, int numero, String palo) {
		// TODO Auto-generated method stub

		
		chicos.get(chicos.size() - 1).jugarCarta(idJugador, numero, palo);
		
		if (chicos.get(chicos.size() - 1).finalizoChico())
			crearChico();


	}

}
