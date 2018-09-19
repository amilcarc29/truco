package negocio;

import java.util.ArrayList;
import java.util.List;

public class Mano {

	private int idMano;
	private List<Pareja> parejas;
	private List<Baza> bazas;
	private List<Jugador> jugadores;
	private Envite envite;
	private Truco truco;
	private int puntoParaTerminarChico;
	private Mazo mazo;
	private Pareja ganadorBaza1;

	public Mano(List<Pareja> parejas, List<Jugador> jugadores, int puntoParaTerminarChico) {
		super();
		setParejas(parejas);
		setJugadores(jugadores);
		setPuntoParaTerminarChico(puntoParaTerminarChico);
		this.mazo = new Mazo();
		this.bazas = new ArrayList<>();
	}

	public int getIdMano() {
		return idMano;
	}

	public void setIdMano(int idMano) {
		this.idMano = idMano;
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	public List<Baza> getBazas() {
		return bazas;
	}

	public void setBazas(List<Baza> bazas) {
		this.bazas = bazas;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Envite getEnvite() {
		return envite;
	}

	public void setEnvite(Envite envite) {
		this.envite = envite;
	}

	public Truco getTruco() {
		return truco;
	}

	public void setTruco(Truco truco) {
		this.truco = truco;
	}

	public int getPuntoParaTerminarChico() {
		return puntoParaTerminarChico;
	}

	public void setPuntoParaTerminarChico(int puntoParaTerminarChico) {
		this.puntoParaTerminarChico = puntoParaTerminarChico;
	}

	public Mazo getMazo() {
		return mazo;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public Pareja getGanadorBaza1() {
		return ganadorBaza1;
	}

	public void setGanadorBaza1(Pareja ganadorBaza1) {
		this.ganadorBaza1 = ganadorBaza1;
	}

}
