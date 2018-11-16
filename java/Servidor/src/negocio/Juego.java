package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.JugadorDAO;
import dao.ParejaDAO;
import dto.ChicoDTO;
import dto.JuegoDTO;
import dto.ParejaDTO;
import excepciones.BazaException;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.ChicoException;
import excepciones.JuegoException;
import excepciones.JugadaException;
import excepciones.JugadorException;
import excepciones.ManoException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;

public abstract class Juego {

	private int idJuego;
	private List<Pareja> parejas;
	private List<Chico> chicos;
	private Pareja ganador;
	private int puntoBase;
	private Date fecha;
	private boolean activo;

	private int JugadorTurno = 0;

	public Juego(List<Pareja> parejas) {
		super();
		this.parejas = parejas;
		this.chicos = new ArrayList<>();
		setPuntoBase(0);
		this.fecha = new Date();
		setActivo(true);
	}

	public Juego() {
	}

	public int getId() {
		return this.idJuego;
	}

	public void setId(int id) {
		this.idJuego = id;
	}

	public abstract int calcularPuntos();

	public boolean sosJuego(Juego juego) {
		return (this.idJuego == juego.getId());
	}

	public Pareja getPareja1() {
		return this.parejas.get(0);
	}

	public Pareja getPareja2() {
		return this.parejas.get(1);
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
		return getUltimoChico().getParejaGanadora();
	}

	public abstract void finalizarJuego()
			throws UsuarioException, CategoriaException, ParejaException, MiembroException;

	public int calcularPuntosSegunCategoria(Usuario usuario) throws CategoriaException, UsuarioException {
		int puntosAgregados = 0;
		if (esCategoriaInferior(usuario.getCategoria().getNombre())) {
			puntosAgregados = 2;
		}
		return calcularPuntos() + puntosAgregados;
	}

	public boolean esCategoriaInferior(String nombreCategoria) throws CategoriaException, UsuarioException {

		switch (nombreCategoria) {
		case "NOVATO":
			return obtenerCategoriaMayor().getNombre().equalsIgnoreCase("CALIFICADO") ? true : false;
		case "CALIFICADO":
			return obtenerCategoriaMayor().getNombre().equalsIgnoreCase("EXPERTO") ? true : false;
		case "EXPERTO":
			return obtenerCategoriaMayor().getNombre().equalsIgnoreCase("MASTER") ? true : false;
		default:
			return false;
		}
	}

	public Categoria obtenerCategoriaMayor() throws CategoriaException, UsuarioException {
		if (getPareja1().obtenerMayorCategoria().getNombre()
				.equalsIgnoreCase(getPareja2().obtenerMayorCategoria().getNombre())) {
			return getPareja1().obtenerMayorCategoria();
		}
		return getPareja2().obtenerMayorCategoria();
	}

	// TODO tener en cuenta el orden para cada mano
	public void crearChico() throws UsuarioException, CategoriaException, ParejaException, ManoException,
			CartaException, ChicoException {
		Chico chico = new Chico(parejas);
		chico.save(this);
		chico.altaMano(chico.getPuntosParaTerminar());
		chicos.add(chico);
	}

	// TODO Agregar a Diagrama.

	public void cantarTruco() throws ManoException {
		chicos.get(chicos.size() - 1).cantarTruco();
	}

	public void cantarReTruco() throws CategoriaException, UsuarioException, ManoException {
		chicos.get(chicos.size() - 1).cantarReTruco();
	}

	public void cantarVale4() throws CategoriaException, UsuarioException, ManoException {
		chicos.get(chicos.size() - 1).cantarVale4();
	}

	public Pareja obtenerParejaContraria(Jugador jugador) throws ParejaException {
		for (Pareja pareja : this.parejas) {
			if (!pareja.tieneJugador(jugador.getId()))
				return pareja;
		}
		throw new ParejaException("No se encontro la pareja del jugador: " + jugador.getId());
	}

	public void jugarCarta(Carta carta) throws JugadorException, CartaException, UsuarioException, CategoriaException,
			JuegoException, ManoException, BazaException, JugadaException {

		// TODO ver si se pasa a baza
		Jugador jugador = JugadorDAO.getInstancia().getJugadorConTurno(this);

		chicos.get(chicos.size() - 1).jugarCarta(carta, jugador);

		JugadorDAO.getInstancia().getPasarTurno(this);

	}

	public boolean sePuedeCantarEnvido() {
		return chicos.get(chicos.size() - 1).sePuedeCantarEnvido();
	}

	public boolean terminoJuego() {
		// Es al mejor de 3
		if (this.getChicos().size() == 3)
			return true;
		else if (this.getChicos().size() > 1) {
			Pareja pareja = this.chicos.get(0).getParejaGanadora();
			Pareja pareja1 = this.chicos.get(1).getParejaGanadora();
			if (pareja.getIdPareja() == pareja1.getIdPareja())
				return true;
		}
		return false;
	}

	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public List<Chico> getChicos() {
		return chicos;
	}

	public void setChicos(List<Chico> chicos) {
		this.chicos = chicos;
	}

	public abstract void save() throws ParejaException, CategoriaException, MiembroException;

	public JuegoDTO toDTO() {
		List<ParejaDTO> pdto = new ArrayList<>();
		List<ChicoDTO> cdto = new ArrayList<>();

		for (Pareja p : this.parejas) {
			pdto.add(p.toDTO());
		}

		for (Chico c : this.chicos) {
			cdto.add(c.toDTO());
		}
		JuegoDTO j;
		if (this.ganador != null)
			j = new JuegoDTO(this.idJuego, pdto, cdto, this.ganador.toDTO(), this.fecha);
		else
			j = new JuegoDTO(this.idJuego, pdto, cdto, null, this.fecha);

		return j;
	}

	public void setChico(List<Chico> chicos) {
		this.chicos = chicos;
	}

	public List<Carta> getCartas(Jugador jug) {
		// TODO Auto-generated method stub
		return chicos.get(chicos.size() - 1).getCartas(jug);
	}

	public Mano getUltimaMano() {

		return this.getUltimoChico().getUltimaMano();
	}

	public Baza getUltimaBaza() throws BazaException {

		return this.getUltimaMano().getUltimaBaza();
	}

	public Chico getUltimoChico() {

		return chicos.get(chicos.size() - 1);
	}

	public void armarNuevoChico() throws UsuarioException, CategoriaException, ParejaException, ManoException,
			CartaException, ChicoException {
		// TODO Auto-generated method stub

		this.getUltimoChico().setJugadores(JugadorDAO.getInstancia().getJugadores(this.getId()));
		this.getUltimoChico().cambiarOrden();

		List<Jugador> jugadores = this.getUltimoChico().getJugadores();
		Pareja p1 = ParejaDAO.getInstancia().buscarParejaDeUnJugador(jugadores.get(0).getId());
		Pareja p2 = ParejaDAO.getInstancia().buscarParejaDeUnJugador(jugadores.get(1).getId());

		this.parejas = new ArrayList<>();
		this.parejas.add(p1);
		this.parejas.add(p2);

		Chico chico = new Chico(parejas);
		chico.save(this);
		chico.altaMano(chico.getPuntosParaTerminar());
		chicos.add(chico);

	}

	public void aumentarPuntosTruco(Pareja parejaG) throws CategoriaException, ParejaException {

		this.getUltimoChico().aumentarPuntosTruco(parejaG);

	}

	public boolean terminoUltimoChico() {

		return (this.getUltimoChico().terminoChico());

	}

	public void finalizarUltimoChico() throws ParejaException, ChicoException {

		this.getUltimoChico().finalizarChico();

	}

	// LE FALTA JUGADOR. VER! (DIFERENCIA CON TRUCO)

	public void cantarEnvido() throws ManoException {

		this.getUltimoChico().cantarEnvido();

	}

	public void cantarRealEnvido() throws ManoException {

		this.getUltimoChico().cantarRealEnvido();

	}

	public void cantarFaltaEnvido() throws ManoException {

		this.getUltimoChico().cantarFaltaEnvido();

	}

	public void aumentarPuntosEnvidoNoQuerido(Pareja parejaG) throws CategoriaException, ParejaException {

		this.getUltimoChico().aumentarPuntosEnvidoNoQuerido(parejaG);

	}

	public void aumentarPuntosEnvidoQuerido() throws CategoriaException, ParejaException {

		this.getUltimoChico().aumentarPuntosEnvidoQuerido();

	}

	public void aumentarPuntosTrucoNoQuerido(Pareja parejaG) throws CategoriaException, ParejaException {

		this.getUltimoChico().aumentarPuntosTrucoNoQuerido(parejaG);

	}

	public void setTieneQueContestar(Jugador jug, String tanto) throws ParejaException {
		this.getUltimoChico().setTieneQueContestar(jug, tanto);

	}

	public boolean alguienTieneQueContestar() {
		return (this.getUltimoChico().alguienTieneQueContestar());
	}

	public void inicializarContestar() {
		this.getUltimoChico().inicializarContestar();

	}

	// public boolean terminoUltimaMano() {

	// return this.getUltimoChico().terminoUlitmaM

	// }

	// public boolean terminoUltimaBaza() {
	//
	// return this.getUltimoChico().terminoUltimaBaza();
	//
	// }

}