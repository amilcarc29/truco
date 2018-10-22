package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.ChicoDAO;
import dao.JugadorDAO;
import dao.PuntuacionDAO;
import dto.ChicoDTO;
import dto.JugadorDTO;
import dto.ManoDTO;
import dto.ParejaDTO;
import dto.PuntuacionDTO;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.JugadorException;
import excepciones.ParejaException;
import excepciones.UsuarioException;

public class Chico {

	private int idChico;
	private List<Mano> manos;
	private List<Pareja> parejas;
	private List<Puntuacion> puntosChico;
	private Pareja ganador;

	// 30 PUNTOS MAXIMO
	private int puntosPorGanar;

	private List<Jugador> jugadores;

	private boolean sePuedeCantarEnvido = true;

	public Chico(List<Pareja> parejas) {
		this.manos = new ArrayList<>();
		this.jugadores = new ArrayList<>();
		this.parejas = parejas;
		this.puntosChico = new ArrayList<>();

		/// se tiene que pasar a una funcion
		jugadores.add(this.parejas.get(0).getJugadores().get(0));
		jugadores.add(this.parejas.get(1).getJugadores().get(0));
		jugadores.add(this.parejas.get(0).getJugadores().get(1));
		jugadores.add(this.parejas.get(1).getJugadores().get(1));

		// CREAR LAS PUNTUACIONES ACA PUEDE GENERAR PROBLEMAS. HAY QUE PERSISTIR
		// PUNTUACIONES Y CHICO A LA VEZ
		for (Pareja pareja : this.parejas) {

			/// puntos en 0
			Puntuacion p = new Puntuacion(pareja);
//			p.setChico(this);
			this.puntosChico.add(p);
		}

		// puntos por manos

		this.ganador = null;

		// puntos totales para terminar el chico (30). Es un chico, no dos de 15
		this.puntosPorGanar = 30;
		// altaMano(this.puntosPorGanar);
		//JugadorDAO.getInstancia().setJugadores(jugadores);
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



	public boolean terminoChico() {
		for (Puntuacion puntacion : this.getPuntosChico()) {
			if (puntacion.getPuntos() >= 30)
				return true;
		}

		return false;
	}



	public Pareja getParejaGanadora() {

		for (Puntuacion p : this.puntosChico) {
			if (p.getPuntos() >= 30)
				return p.getPareja();
		}

		return null;
	}

	public void finalizarChico() throws ParejaException {

		// buscar ganador
		this.setGanador(this.getParejaGanadora());

		ChicoDAO.getInstancia().actualizarParejaGanadora(this);
	}

	public int getPuntosParaTerminar() {

		int puntos = 0;

		for (Puntuacion p : this.puntosChico) {
			if (p.getPuntos() > puntos)
				puntos = p.getPuntos();

		}

		return (this.puntosPorGanar - puntos);

	}

	public void aumentarPuntosTruco(Pareja pareja) throws CategoriaException, ParejaException {
		
		for (Puntuacion p : this.getPuntosChico()) {
			if (p.esPuntuacion(pareja)) {
				p.sumarPuntos(this.getUltimaMano().getTruco().getPuntos());
				// aumenta los puntos
				PuntuacionDAO.getInstancia().actualizarPuntos(p);

			}
		}
	}

	// TODO Agregar parámetro parejas a Diagrama.
	public void altaMano(int puntosParaTerminar) throws UsuarioException, CategoriaException {
		// FIXME Por qué parámetros? no debería usar las parejas, jugadores y
		// puntosPorTerminar del Chico?
		// ---> Porque en el chico ya tenemos las parejas, los puntos y los jugadores.
	
		Mano mano = new Mano(parejas, jugadores, puntosParaTerminar);
		mano.save(this);
//		mano.altaBaza();

		manos.add(mano);
		
		
		JugadorDAO.getInstancia().actualizarTurnos(jugadores);
		// System.out.println("MANO NUMERO " + manos.size());
	}

	// TODO AGREGAR BUSCA UN JUGADOR EN UNA PAREJA

	public void cantarTruco(Jugador j) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarTruco(j);
	}
	
	public void cantarReTruco(Jugador j) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarReTruco(j);
	}

	public void cantarVale4(Jugador j) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarVale4(j);
	}
	
	public void compFinalizacionChico() throws CategoriaException {
		Mano mUltima = this.manos.get(this.manos.size() - 1);
		// si finalizo retorna la pareja que gano si no retorna null

	}

	public void jugarCarta(Carta carta, Jugador jugador)
			throws JugadorException, CartaException, UsuarioException, CategoriaException {
		// TODO Auto-generated method stub
		Mano mUltima = this.manos.get(this.manos.size() - 1);

		mUltima.jugarCarta(carta, jugador);

	}

	public boolean sePuedeCantarEnvido() {
		// TODO Auto-generated method stub
		return sePuedeCantarEnvido;
	}

	public void save(Juego juego) throws UsuarioException, CategoriaException, ParejaException {
		this.setIdChico(ChicoDAO.getInstancia().guardarChico(juego, this));
	}

	public int getIdChico() {
		return idChico;
	}

	public void setIdChico(int idJuego) {
		this.idChico = idJuego;
	}

	public List<Mano> getManos() {
		return manos;
	}

	public void setManos(List<Mano> manos) {
		this.manos = manos;
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	public List<Puntuacion> getPuntosChico() {
		return puntosChico;
	}

	public void setPuntosChico(List<Puntuacion> puntosChico) {
		this.puntosChico = puntosChico;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public boolean isSePuedeCantarEnvido() {
		return sePuedeCantarEnvido;
	}

	public void setSePuedeCantarEnvido(boolean sePuedeCantarEnvido) {
		this.sePuedeCantarEnvido = sePuedeCantarEnvido;
	}

	public List<Carta> getCartas(Jugador jug) {
		// TODO Auto-generated method stub

		for (Jugador jugador : jugadores) {
			if (jugador.esJugador(jug.getId()))
				return jugador.getCartas();
		}
		return null;
	}

	public Mano getUltimaMano() {
		// TODO Auto-generated method stub
		return this.getManos().get(this.getManos().size() - 1);
	}
	public void cambiarOrden() throws UsuarioException, CategoriaException {
		jugadores.add(jugadores.get(0));
		jugadores.remove(0);
		
		JugadorDAO.getInstancia().actualizarTurnos(jugadores);
		
	}
	public void armarNuevaMano() throws UsuarioException, CategoriaException {
		// TODO Auto-generated method stub
		//modifico el orden antes de la proxima mano
		cambiarOrden();
		
		Mano mano = new Mano(parejas, jugadores, this.getPuntosParaTerminar());
		mano.save(this);
		mano.altaBaza();

		manos.add(mano);

	}
	
	

	public ChicoDTO toDTO() {
		// TODO Auto-generated method stub

		
		List<ManoDTO> manDTO= new ArrayList<>();
		for(Mano m: manos){
			manDTO.add(m.toDTO());
		}
		
		List<ParejaDTO> parDTO= new ArrayList<>();
		for(Pareja p: parejas){
			parDTO.add(p.toDTO());
		}

		List<PuntuacionDTO> punDTO= new ArrayList<>();
		for(Puntuacion punto: puntosChico){
			punDTO.add(punto.toDTO());
		}


		List<JugadorDTO> jugDTO= new ArrayList<>();
		for(Jugador j: jugadores){
			jugDTO.add(j.toDTO());
		}
		
		
		return new ChicoDTO(idChico,manDTO,parDTO,punDTO,this.ganador.toDTO(),puntosPorGanar,jugDTO,sePuedeCantarEnvido);
	}

	public void noQuieroReTruco() {

		this.getUltimaMano().noQuieroReTruco();
		
	}
	
	public void noQuieroValeCuatro() {

		this.getUltimaMano().noQuieroValeCuatro();
		
	}

//	public boolean terminoUltimaBaza() {

//		return this.getUltimaMano().terminoBaza();
		
//	}

	public boolean terminoUltimaMano() throws CategoriaException {

		return this.getUltimaMano().terminoMano();
		
	}
	
	// LE FALTA JUGADOR. VER! (DIFERENCIA CON TRUCO)
	public void cantarEnvido() {

		this.getUltimaMano().cantarEnvido();
		
	}

	public void cantarRealEnvido() {

		this.getUltimaMano().cantarRealEnvido();
		
	}
	
	
	// YA NO NECESITAMOS PASARLE AL CHICO PUNTOS PARA TERMINAR. SACARLO SI HAY TIEMPO
	public void cantarFaltaEnvido() {
		
		
		this.getUltimaMano().cantarFaltaEnvido(this.getPuntosParaTerminar());
		
	}

	public void aumentarPuntosEnvidoNoQuerido(Pareja pareja) throws CategoriaException, ParejaException {


		for (Puntuacion p : this.getPuntosChico()) {
			if (p.esPuntuacion(pareja)) {
				p.sumarPuntos(this.getUltimaMano().getEnvido().getPuntosNoQuiero());
				// aumenta los puntos
				PuntuacionDAO.getInstancia().actualizarPuntos(p);

			}
		}
		
	}

	public void aumentarPuntosEnvidoQuerido() throws CategoriaException, ParejaException {
		Pareja pareja = this.getUltimaMano().obtenerParejaGanadoraEnvido();
		
		for (Puntuacion p : this.getPuntosChico()) {
			if (p.esPuntuacion(pareja)) {
				p.sumarPuntos(this.getUltimaMano().getEnvido().getPuntosQuiero());
				// aumenta los puntos
				PuntuacionDAO.getInstancia().actualizarPuntos(p);

			}
		}
		
	}

}
