package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import excepciones.CartaException;
import excepciones.JugadorException;

public class Mano {

	private List<Pareja> parejas;
	private List<Baza> bazas;
	private List<Jugador> jugadores;
	private Envido envido;
	private Truco truco;
	private int puntoParaTerminarChico;
	private Mazo mazo;
	private Pareja ganadorBaza1;

	public Mano(List<Pareja> parejas, List<Jugador> jugadores, int puntoParaTerminarChico) {
		super();
		this.parejas = parejas;
		this.jugadores = jugadores;
		setPuntoParaTerminarChico(puntoParaTerminarChico);
		this.mazo = new Mazo();
		this.bazas = new ArrayList<>();
		repartir();
		// TODO primera baza
		altaBaza();

		for (Jugador jug : jugadores) {
			System.out.println(" JUG = >" + jug.getNombre());
		}

	}

	private void altaBaza() {
		Baza b = new Baza();
		b.setJugadores(jugadores);
		this.bazas.add(b);
		System.out.println(" baza num " + this.bazas.size());
	}

	private void repartir() {
		for (Jugador jug : jugadores) {

			Vector<Carta> cartas = this.mazo.getTresCartasRandom();
			jug.setCartas(cartas);

		}
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public int getPuntoParaTerminarChico() {
		return puntoParaTerminarChico;
	}

	public void setPuntoParaTerminarChico(int puntoParaTerminarChico) {
		this.puntoParaTerminarChico = puntoParaTerminarChico;
	}

	public Pareja getGanadorBaza1() {
		return ganadorBaza1;
	}

	public void setGanadorBaza1(Pareja ganadorBaza1) {
		this.ganadorBaza1 = ganadorBaza1;
	}
	// TODO AGREGAR BUSCA UN JUGADOR EN UNA PAREJA

	public void cantarTruco(int idJugador) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarTruco(idJugador);

		this.truco = new Truco();

	}

	public void cantarVale4(int idJugador) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarTruco(idJugador);

		if (this.truco == null)
			this.truco = new Truco();

		Vale4 v4 = new Vale4();
		this.truco.addDec(v4);

	}

	public void cantarReTruco(int idJugador) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarTruco(idJugador);
		this.truco = new Truco();

		ReTruco rt = new ReTruco();

		Vale4 v4 = new Vale4();
		v4.addDec(rt);

		this.truco.addDec(v4);

	}

	public void cantarQuieroTruco(boolean quieroSiNo, int idJugador) {
		if (quieroSiNo)
			System.out.println("puntos quiero Truco " + this.truco.getPuntosQuiero());
		else
			System.out.println("puntos no Quiero Truco  " + this.truco.getPuntosNoQuiero());

		// TODO Auto-generated method stub

	}

	public void cantarEnvido(int idJugador) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarEnvido(idJugador);
		if (this.envido == null)
			this.envido = new Envido();
		else {
			Envido env = new Envido();
			this.envido.addDec(env);

		}

	}

	public void cantarQuieroEnvido(boolean quieroSiNo, int idJugador) {
		if (quieroSiNo)
			System.out.println("puntos quiero Envido " + this.envido.getPuntosQuiero());
		else
			System.out.println("puntos no quiero Envido  " + this.envido.getPuntosNoQuiero());

		// TODO Auto-generated method stub

	}

	public void jugarCarta(int idJugador, int numero, String palo) throws JugadorException, CartaException {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).jugarCarta(idJugador, numero, palo);

	}

	public boolean finalizoMano() {
		return this.bazas.size() == 3;
	}

	public void calcularPuntos() {
		// TODO Auto-generated method stub
		
	}
}
