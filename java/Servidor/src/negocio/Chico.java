package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.ChicoDAO;
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

		// CREAR LAS PUNTUACIONES ACA PUEDE GENERAR PROBLEMAS. HAY QUE PERSISTIR PUNTUACIONES Y CHICO A LA VEZ
		for (Pareja pareja : this.parejas) {

			/// puntos en 0
			Puntuacion p = new Puntuacion(pareja);
			this.puntosChico.add(p);
		}

		// puntos por manos

		this.ganador = null;
		
		// puntos totales para terminar el chico (30). Es un chico, no dos de 15
		this.puntosPorGanar = 30;
//		altaMano(this.puntosPorGanar);

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
	
	// ESTO NO SE PARA QUE SE PUEDE USAR
	private void sumarPuntosMano(Puntuacion puntuacion) {
		for (Puntuacion p : puntosChico) {

			if (p.esPuntuacion(puntuacion)) {
				p.sumarPuntos(puntuacion);

			}

		}
	}

	public boolean terminoMano() {
		return this.manos.get(this.manos.size() - 1).finalizoMano();
	}

	public boolean finalizoChico() {
		// finalizo ultima mano

		Mano ultimaMano = this.manos.get(this.manos.size() - 1);

		if (ultimaMano.finalizoMano()) {

			System.out.println("FIN MANO");
			sePuedeCantarEnvido = true;
			
			List<Puntuacion> puntosMano = ultimaMano.getPuntos();

			for (Puntuacion puntuacion : puntosMano) {
				sumarPuntosMano(puntuacion);
//				System.out.println(
//						"getIdPareja	" + puntuacion.getPareja().getIdPareja() + " " + puntuacion.getPuntos());
			}

			for (Puntuacion p : this.puntosChico) {
				// MAYOR PORQUE PUEDE QUE SUME MAS DE 30
				
				System.out.println(
						"getIdPareja	" + p.getPareja().getIdPareja() + " " + p.getPuntos());

				
				if (p.getPuntos() >= puntosPorGanar) {
					this.ganador = p.getPareja();
					return true;
				}
				
			
			}

			cambiarOrden();
			Mano mano = new Mano(parejas, jugadores, puntosChico, puntosPorGanar);
			manos.add(mano);
			return false;

		} else {

		}

		return false;

	}
	private void cambiarOrden() {
		jugadores.add(jugadores.get(0));
		jugadores.remove(0);
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
	public void altaMano(int puntosParaTerminar) {
		// FIXME Por qué parámetros? no debería usar las parejas, jugadores y
		// puntosPorTerminar del Chico?   
		// ---> Porque en el chico ya tenemos las parejas, los puntos y los jugadores.

		Mano mano = new Mano(parejas, jugadores, puntosChico, puntosParaTerminar);		
		mano.save(this);
		mano.altaBaza();		
		
		manos.add(mano);

//		System.out.println("MANO NUMERO " + manos.size());
	}

	// TODO AGREGAR BUSCA UN JUGADOR EN UNA PAREJA

	public void cantarTruco() {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarTruco();
	}

	public void cantarVale4(int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarVale4();
	}

	public void cantarReTruco() {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarReTruco();
	}

	public void cantarQuieroTruco(boolean quieroSiNo) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarQuieroTruco(quieroSiNo);
	}

	public void cantarQuieroEnvido(boolean quieroSiNo) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarQuieroEnvido(quieroSiNo);
	}

	public void cantarEnvido() {
		sePuedeCantarEnvido = false;

		this.manos.get(this.manos.size() - 1).cantarEnvido();

	}

	public void sinCantar() {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).sinCantar();

	}

	public void jugarCarta(Carta carta, Jugador jugador) throws JugadorException, CartaException, UsuarioException, CategoriaException {
		// TODO Auto-generated method stub
		
		try {
			this.manos.get(this.manos.size() - 1).jugarCarta(carta, jugador);
			
		// VER BIEN LAS EXCEPCIONES
		} catch (UsuarioException e) {
			e.printStackTrace();
		} catch (CategoriaException e1) {
			e1.printStackTrace();
		}

	}


	public boolean sePuedeCantarEnvido() {
		// TODO Auto-generated method stub
		return sePuedeCantarEnvido;
	}

	public Jugador proximoDbg() {
		// TODO Auto-generated method stub
		return this.manos.get(this.manos.size() - 1).proximoDbg();
	}

	public void puntosDbg(int idPareja) {
		// TODO Auto-generated method stub
		 this.manos.get(this.manos.size() - 1).puntosDbg(idPareja);

	}
	
	public void save (Juego juego) throws UsuarioException, CategoriaException, ParejaException {
		this.setIdChico(ChicoDAO.getInstancia().guardarChico(juego, this));
	}

	public boolean esTurno(Jugador jugador) {
		return  this.manos.get(this.manos.size() - 1).esTurno(jugador);		
	}
	
	public void crearMano () {
		
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
					if(jugador.esJugador(jug.getId()))
						return jugador.getCartas();
		}
		return null;
	}
	
	


}
