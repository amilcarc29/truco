package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.JuegoDAO;
import dto.JuegoDTO;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.JugadorException;
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

	public abstract void calcularPuntos();

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

	// TODO tener en cuenta el orden para cada mano
	public void crearChico() throws UsuarioException, CategoriaException, ParejaException {
		List<Jugador> jugadores = new ArrayList<Jugador>();

		for (int i = 0; i < this.parejas.size(); i++) {

			jugadores.addAll(this.parejas.get(i).getJugadores());
		}

		Chico chico = new Chico(parejas);
		
		chico.altaMano(chico.getPuntosParaTerminar());
		// IMPEMENTAR GUARDADO DE JUEGO EN BD
		chico.save(this);
		chicos.add(chico);
		
	}

	// TODO Agregar a Diagrama.

	public void cantarTruco() {

		chicos.get(chicos.size() - 1).cantarTruco();

	}

	public void cantarReTruco() {

		chicos.get(chicos.size() - 1).cantarReTruco();

	}

	public void cantarVale4(int idJugador) {

		chicos.get(chicos.size() - 1).cantarVale4(idJugador);

	}

	public void cantarQuieroEnvido(boolean quieroSiNo) {
		// TODO Auto-generated method stub
		chicos.get(chicos.size() - 1).cantarQuieroEnvido(quieroSiNo);
	}

	public void cantarQuieroTruco(boolean quieroSiNo) {
		// TODO Auto-generated method stub
		chicos.get(chicos.size() - 1).cantarQuieroTruco(quieroSiNo);
	}

	public void cantarEnvido() {
		chicos.get(chicos.size() - 1).cantarEnvido();
	}

	public void sinCantar() {
		// TODO Auto-generated method stub
		chicos.get(chicos.size() - 1).sinCantar();

	}

	public void jugarCarta(int numero, String palo) throws JugadorException, CartaException {
		// TODO Auto-generated method stub
		chicos.get(chicos.size() - 1).jugarCarta(numero, palo);
	}

	public boolean terminoMano() {
		return chicos.get(chicos.size() - 1).terminoMano();
	}

	public boolean sePuedeCantarEnvido() {
		return chicos.get(chicos.size() - 1).sePuedeCantarEnvido();
	}

	public boolean verificarFinJuego() throws UsuarioException, CategoriaException, ParejaException {
		/* FIJARSE QUE UNA PAREJA GANE DOS CHCICOS PARA TERMINAR */
		if (chicos.size() >= 2) {
			System.out.println("FIN CHICOS");

			return true;
		} else {
			if (chicos.get(chicos.size() - 1).finalizoChico())
				crearChico();
		}

		return false;
	}

	public Jugador proximoDbg() {
		// TODO Auto-generated method stub
		return chicos.get(chicos.size() - 1).proximoDbg();
	}

	public void puntosDbg(int idPareja) {
		chicos.get(chicos.size() - 1).puntosDbg(idPareja);
	}

	public abstract void save() throws ParejaException;

	public JuegoDTO toDTO() {
		JuegoDTO j = new JuegoDTO();
		j.setIdJuego(this.idJuego);

		return j;
	}

	public boolean esTurno(Usuario us) {
		return chicos.get(chicos.size() - 1).esTurno(us);
		 
	}
}
