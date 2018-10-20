package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import dao.BazaDAO;
import dao.JugadorDAO;
import dao.ManoDAO;
import entities.BazaEntity;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.JugadorException;
import excepciones.UsuarioException;

public class Mano {

	private int idMano;
	private List<Pareja> parejas;
	private List<Baza> bazas;
	private List<Jugador> jugadores;
	private List<Puntuacion> puntos;

	private Envido envido;
	private Truco truco;

	private int puntoParaTerminarChico;
	private Mazo mazo;

	// ESTO NO SE PARA QUE ESTA
	private List<Pareja> historicoPuntos = null;
	private Pareja ganadorBaza1 = null;
	private Jugada jugadaMayor = null;
	private boolean trucoCantado = false;

	// esto mantiene el turno de los jugadores se puede iniciar donde sea pero se
	// incrementa con cada jugada

	//
	public Mano(int idMano) {
		this.idMano = idMano;
	}

	public Mano(List<Pareja> parejas, List<Jugador> jugadores, List<Puntuacion> puntos, int puntoParaTerminarChico)
			throws UsuarioException, CategoriaException {
		super();
		this.parejas = parejas;
		this.jugadores = jugadores;
		this.puntos = puntos;
		setPuntoParaTerminarChico(puntoParaTerminarChico);
		// FALTA SACAR CARTAS DE LA BD Y ARMAR EL MAZO
		// this.mazo = obtenerMazo();
		this.bazas = new ArrayList<Baza>();
		// FALTA VER SI SE CREA ENVIDO Y/O TRUCO

		mazo = new Mazo();
		repartir();

	}

	public void altaBaza() {
		Baza b = new Baza(this.getJugadores());
		b.save(this);
		// FIX arreglar
		this.bazas = new ArrayList<Baza>();
		this.bazas.add(b);
		// System.out.println("BAZA NUMERO " + this.bazas.size());

		// turno del primer jugador
		JugadorDAO.getInstancia().setTurno(this.jugadores.get(0));
	}

	public List<Puntuacion> getPuntos() {
		return this.puntos;
	}

	private void repartir() throws UsuarioException, CategoriaException {
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

	// TODO AGREGAR BUSCA UN JUGADOR EN UNA PAREJA

	public void cantarTruco(Jugador j) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarTruco(j);

		this.truco = new Truco();

	}

	public void cantarVale4(Jugador j) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarTruco(j);

		if (this.truco == null)
			this.truco = new Truco();

		Vale4 v4 = new Vale4();
		this.truco.addDec(v4);

	}

	public void cantarReTruco(Jugador j) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarTruco(j);
		this.truco = new Truco();

		ReTruco rt = new ReTruco();

		Vale4 v4 = new Vale4();
		v4.addDec(rt);

		this.truco.addDec(v4);

	}

	public void cantarQuieroTruco(Jugador j, boolean quieroSiNo) {

		// TODO Auto-generated method stub el jugador +1 es de la otra pareja
		Puntuacion p;
		// si quiere Truco
		//

		if (quieroSiNo) {

			Pareja parejaactual = getParejaActual(j.getId());
			Pareja parejacontraria = getParejaContrariaActual(j.getId());

			if (parejaactual.getMayorTantoTruco() > parejacontraria.getMayorTantoTruco()) {
				// gana pareja 1
				p = getPuntosPareja(j);

			} else {
				// gana pareja 2

				p = getPuntosParejaContraria(j);

			}

			p.sumarPuntos(this.truco.getPuntosQuiero());

			System.out.println("puntos quiero Truco " + this.truco.getPuntosQuiero());

		} else {
			p = getPuntosPareja(j);
			p.sumarPuntos(this.truco.getPuntosNoQuiero());

			System.out.println("puntos no quiero Truco  " + this.truco.getPuntosNoQuiero());

		}
		trucoCantado = true;

	}

	public void cantarEnvido(Jugador j) {
		// TODO Auto-generated method stub
		this.bazas.get(this.bazas.size() - 1).cantarEnvido(j);
		if (this.envido == null)
			this.envido = new Envido();
		else {
			Envido env = new Envido();
			this.envido.addDec(env);

		}

	}

	public Pareja getPareja(int idJugador) {
		for (Pareja p : parejas) {
			if (p.tieneJugador(idJugador))
				return p;
		}
		return null;
	}

	public Pareja getParejaActual(int idJugador) {
		for (Pareja p : parejas) {
			if (p.tieneJugador(idJugador))
				return p;
		}
		return null;
	}

	public Pareja getParejaContrariaActual(int idJugador) {
		for (Pareja p : parejas) {
			if (!p.tieneJugador(idJugador))
				return p;
		}
		return null;
	}

	public void cantarQuieroEnvido(Jugador j, boolean quieroSiNo) {

		// TODO Auto-generated method stub el jugador +1 es de la otra pareja
		Puntuacion p;
		// si quiere envido
		//

		if (quieroSiNo) {

			Pareja parejaactual = getParejaActual(j.getId());
			Pareja parejacontraria = getParejaContrariaActual(j.getId());

			if (parejaactual.getMayorTantoEnvido() > parejacontraria.getMayorTantoEnvido()) {
				// gana pareja 1
				p = getPuntosPareja(j);

			} else {
				// gana pareja 2

				p = getPuntosParejaContraria(j);

			}

			p.sumarPuntos(this.envido.getPuntosQuiero());

			System.out.println("puntos quiero Envido " + this.envido.getPuntosQuiero());

		} else {
			p = getPuntosPareja(j);
			p.sumarPuntos(this.envido.getPuntosNoQuiero());

			System.out.println("puntos no quiero Envido  " + this.envido.getPuntosNoQuiero());

		}

	}

	/// TODO AGREGAR!
	public void sinCantar() {
		// TODO Auto-generated method stub

		Jugada j = this.bazas.get(this.bazas.size() - 1).jugadaMayor();
		Puntuacion p = getPuntosPareja(j.getJugador());

		// sin truco ni envido 1 PUNTO
		p.sumarPuntos(1);

	}

	// TODO agregar

	private Puntuacion getPuntosParejaContraria(Jugador j) {
		for (Puntuacion pun : puntos)
			/// TODO VER!!
			if (!pun.tieneJugador(j.getId()))
				return pun;

		return null;
	}

	private Puntuacion getPuntosPareja(Jugador j) {
		for (Puntuacion pun : puntos)
			/// TODO VER!!
			if (pun.tieneJugador(j.getId()))
				return pun;

		return null;
	}

	public void jugarCarta(Carta carta, Jugador jugador)
			throws JugadorException, CartaException, UsuarioException, CategoriaException {
		// TODO Auto-generated method stub
		try {

			this.bazas.get(this.bazas.size() - 1).jugarCarta(carta, jugador);

			// VER BIEN LAS EXCEPCIONES
		} catch (UsuarioException e) {
			e.printStackTrace();
		} catch (CategoriaException e1) {
			e1.printStackTrace();
		}
	}

	public boolean finalizoMano() {

		jugadaMayor = this.bazas.get(this.bazas.size() - 1).jugadaMayor();

		if (trucoCantado) {

			return true;

		} else {
			if ((this.bazas.get(this.bazas.size() - 1).finalizoBaza())) {

				Pareja g = getPareja(jugadaMayor.getJugador().getId());

				if (this.bazas.size() < 3) {

					if (historicoPuntos.indexOf(g) > 0) {
						ganadorBaza1 = g;

						return true;

					} else
						historicoPuntos.add(g);

					cambiarOrden();
					altaBaza();

				} else {

					Puntuacion pmano = getPuntosPareja(jugadaMayor.getJugador());
					pmano.sumarPuntos(1);

					ganadorBaza1 = g;

					return true;
				}

			}
		}

		return false;
	}

	private void cambiarOrden() {
		// preguntar quien gano , ponerlo adelante
		Jugador jugador = jugadaMayor.getJugador();

		int i = 0;
		int j = jugadores.indexOf(jugador);

		List<Jugador> jugadoresNuevo = new ArrayList<Jugador>();

		jugadoresNuevo.add(jugador);

		while (i < 3) {
			j++;

			if (j > 3)
				j = 0;

			jugadoresNuevo.add(jugadores.get(j));

			i++;

		}
		jugadores = jugadoresNuevo;
	}

	public boolean sePuedeCantarEnvido() {
		// TODO Auto-generated method stub
		return false;
	}

	public void puntosDbg(int idPareja) {
		// TODO Auto-generated method stub
		for (Puntuacion p : this.puntos) {
			if (idPareja == p.getPareja().getIdPareja()) {
				System.out.println("Puntos =>" + p.getPuntos());
			}

		}
	}

	public List<Baza> getBazas() {
		return bazas;
	}

	public void setBazas(List<Baza> bazas) {
		this.bazas = bazas;
	}

	public Envido getEnvido() {
		return envido;
	}

	public void setEnvido(Envido envido) {
		this.envido = envido;
	}

	public Truco getTruco() {
		return truco;
	}

	public void setTruco(Truco truco) {
		this.truco = truco;
	}

	public Mazo getMazo() {
		return mazo;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public List<Pareja> getHistoricoPuntos() {
		return historicoPuntos;
	}

	public void setHistoricoPuntos(List<Pareja> historicoPuntos) {
		this.historicoPuntos = historicoPuntos;
	}

	public Pareja getGanadorBaza1() {
		return ganadorBaza1;
	}

	public void setGanadorBaza1(Pareja ganadorBaza1) {
		this.ganadorBaza1 = ganadorBaza1;
	}

	public Jugada getJugadaMayor() {
		return jugadaMayor;
	}

	public void setJugadaMayor(Jugada jugadaMayor) {
		this.jugadaMayor = jugadaMayor;
	}

	public boolean isTrucoCantado() {
		return trucoCantado;
	}

	public void setTrucoCantado(boolean trucoCantado) {
		this.trucoCantado = trucoCantado;
	}

	public void setPuntos(List<Puntuacion> puntos) {
		this.puntos = puntos;
	}

	public int getIdMano() {
		return idMano;
	}

	public void setIdMano(int idMano) {
		this.idMano = idMano;
	}

	public void save(Chico chico) {
		this.setIdMano(ManoDAO.getInstancia().guardarMano(chico, this));

	}

}
