package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import dao.BazaDAO;
import dao.JugadaDAO;
import dao.JugadorDAO;
import dao.ManoDAO;
import dao.ParejaDAO;
import dto.BazaDTO;
import dto.JugadorDTO;
import dto.ManoDTO;
import dto.ParejaDTO;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.JugadorException;
import excepciones.UsuarioException;

public class Mano {

	private int idMano;
	private List<Pareja> parejas;
	private List<Baza> bazas;
	private List<Jugador> jugadores;

	private Envido envido;
	private Truco truco;

	private int puntoParaTerminarChico;
	private Mazo mazo;

	private boolean seCantoEnvido;
	private boolean seCantoTruco;

	// esto mantiene el turno de los jugadores se puede iniciar donde sea pero se
	// incrementa con cada jugada

	public Mano(int idMano) {
		this.idMano = idMano;
	}

	public Mano(List<Pareja> parejas, List<Jugador> jugadores, int puntoParaTerminarChico)
			throws UsuarioException, CategoriaException {
		super();
		this.parejas = parejas;
		this.jugadores = jugadores;
		setPuntoParaTerminarChico(puntoParaTerminarChico);
		// FALTA SACAR CARTAS DE LA BD Y ARMAR EL MAZO
		// this.mazo = obtenerMazo();
		this.bazas = new ArrayList<Baza>();
		// FALTA VER SI SE CREA ENVIDO
		this.truco = new Truco();

		this.seCantoEnvido = false;
		this.seCantoTruco = false;

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
		Truco truco = new Truco();
		this.truco.addDec(truco);
	}

	public void cantarReTruco(Jugador j) {
		// TODO Auto-generated method stub

		ReTruco rt = new ReTruco();
		this.truco.addDec(rt);

	}

	public void cantarVale4(Jugador j) {
		// TODO Auto-generated method stub

		Vale4 v4 = new Vale4();
		this.truco.addDec(v4);
	}

	public void cantarQuieroTruco(Jugador j, boolean quiero) {

	}

	public void cantarEnvido(Jugador j) {
		// TODO Auto-generated method stub
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

	public void cantarQuieroEnvido(Jugador j, boolean quiero) {

		// TODO Auto-generated method stub el jugador +1 es de la otra pareja

	}

	/// TODO AGREGAR!
	public void sinCantar() {

	}

	public void jugarCarta(Carta carta, Jugador jugador)
			throws JugadorException, CartaException, UsuarioException, CategoriaException {
		// TODO Auto-generated method stub

		// despues de jugar una carta se tiene que guardar la baza
		Baza nuevaBaza = new Baza(this.getJugadores());
		nuevaBaza.save(this);
		
	

		if (this.bazas == null) {

			// es la primera baza
			this.bazas = new ArrayList<>();

		}
		this.bazas.add(nuevaBaza);
		Baza ultimaBaza = this.bazas.get(this.bazas.size() - 1);
		
		ultimaBaza.jugarCarta(carta, jugador);
		
		// la jugada mayor esta en baza y son 4 esta mal, el update es por id mano
		// actualizo la jugadamayor en todas las bazas de la mano
	
		BazaDAO.getInstancia().actualizarJugadaMayor(this);

	}

	public Pareja obtenerParejaGanadora() throws CategoriaException {
		Baza ultimaBaza = this.bazas.get(this.bazas.size() - 1);

		Jugador jMayorUlimaBaza = ultimaBaza.getJugadaMayor().getJugador();
		Pareja pMayorUlimaBaza = ParejaDAO.getInstancia().buscarParejaDeUnJugador(jMayorUlimaBaza.getId());

		Jugador jMayorPrimeraBaza = this.getBazas().get(0).getJugadaMayor().getJugador();
		Pareja pMayorPrimeraBaz = ParejaDAO.getInstancia().buscarParejaDeUnJugador(jMayorPrimeraBaza.getId());

		// si gano la primera y la segunda la misma pareja es la primera
		if (pMayorUlimaBaza.esPareja(pMayorPrimeraBaz.getIdPareja())) {

			return pMayorUlimaBaza;

		} else {
			// si no es ni la primera pareja ni la tercera entonces gano la pareja del medio
			Jugador jMayorSegundaBaza = this.getBazas().get(1).getJugadaMayor().getJugador();
			return ParejaDAO.getInstancia().buscarParejaDeUnJugador(jMayorSegundaBaza.getId());

		}

	}

	public void cambiarOrden() {
		// // preguntar quien gano , ponerlo adelante
		Jugador jugador = this.bazas.get(this.bazas.size() - 1).getJugadaMayor().getJugador();

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

	public void armarNuevaBaza() {

		// modifica el orden de los jugadores para la nueva baza
		cambiarOrden();

		Baza b = new Baza(this.getJugadores());
		b.save(this);
		// FIX arreglar
		this.bazas.add(b);

		// turno del primer jugador
		JugadorDAO.getInstancia().setTurno(this.jugadores.get(0));

	}

	public boolean terminoMano() throws CategoriaException {
		// 3 Bazas maximo

		if (this.getBazas().size() == 3)
			return true;
		else {
			Pareja pareja = ParejaDAO.getInstancia()
					.buscarParejaDeUnJugador(this.getBazas().get(0).getJugadaMayor().getJugador().getId());
			Pareja pareja1 = ParejaDAO.getInstancia()
					.buscarParejaDeUnJugador(this.getBazas().get(1).getJugadaMayor().getJugador().getId());
			if (pareja.getIdPareja() == pareja1.getIdPareja())
				return true;
		}
		return false;
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

	public int getIdMano() {
		return idMano;
	}

	public void setIdMano(int idMano) {
		this.idMano = idMano;
	}

	public boolean seCantoEnvido() {
		return seCantoEnvido;
	}

	public void setSeCantoEnvido(boolean seCantoEnvido) {
		this.seCantoEnvido = seCantoEnvido;
	}

	public Baza getUltimaBaza() {
		return this.bazas.get(this.bazas.size() - 1);
	}

	public boolean seCantoTruco() {
		return seCantoTruco;
	}

	public void setSeCantoTruco(boolean seCantoTruco) {
		this.seCantoTruco = seCantoTruco;
	}

	public void save(Chico chico) {
		this.setIdMano(ManoDAO.getInstancia().guardarMano(chico, this));
	}

	public ManoDTO toDTO() {
		// TODO Auto-generated method stub

		List<ParejaDTO> parDTO = new ArrayList<>();
		for (Pareja p : parejas) {
			parDTO.add(p.toDTO());
		}

		List<BazaDTO> bazDTO = new ArrayList<>();
		for (Baza b : bazas) {
			bazDTO.add(b.toDTO());
		}

		List<JugadorDTO> jugDTO = new ArrayList<>();
		for (Jugador j : jugadores) {
			jugDTO.add(j.toDTO());
		}

		return new ManoDTO(idMano, parDTO, bazDTO, jugDTO, puntoParaTerminarChico, seCantoEnvido, seCantoTruco);
	}
}
