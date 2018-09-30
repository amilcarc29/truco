package negocio;

import java.util.ArrayList;
import java.util.List;

import excepciones.CartaException;
import excepciones.JugadorException;

public class Chico {
	private List<Mano> manos;
	private List<Pareja> parejas;
	private List<Puntuacion> puntosChico;
	private Pareja ganador;

	// 30 PUNTOS MAXIMO
	private int puntosPorGanar;

	private List<Jugador> jugadores;

	// PARA FALTA ENVIDO
	private int puntosParaTerminar;
	
	

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

		for (Pareja pareja : this.parejas) {

			/// puntos en 0
			Puntuacion p = new Puntuacion();
			p.setPareja(pareja);
			this.puntosChico.add(p);
		}

		// puntos por manos

		this.ganador = null;
		this.puntosPorGanar = 30;
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

	private void sumarPuntosMano(Puntuacion puntuacion) {
		for (Puntuacion puntosChico : puntosChico) {

			if (puntosChico.esPuntuacion(puntuacion)) {
				puntosChico.sumarPuntos(puntuacion);
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

			List<Puntuacion> puntosMano = ultimaMano.getPuntos();

			for (Puntuacion puntuacion : puntosMano) {
				sumarPuntosMano(puntuacion);
			}

			for (Puntuacion p : this.puntosChico) {
				// MAYOR PORQUE PUEDE QUE SUME MAS DE 30
				if (p.getPuntos() >= puntosPorGanar) {
					this.ganador = p.getPareja();
					return true;
				}

			}

			cambiarOrden();
			Mano mano = new Mano(parejas, jugadores, puntosParaTerminar);
			manos.add(mano);

		}else{
			
		}

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

	// TODO Agregar parámetro parejas a Diagrama.
	public void altaMano(List<Pareja> parejas, List<Jugador> jugadores, int puntosParaTerminar) {
		// FIXME Por qué parámetros? no debería usar las parejas, jugadores y
		// puntosPorTerminar del Chico?
		this.parejas = parejas;
		this.jugadores = jugadores;
		this.puntosParaTerminar = puntosParaTerminar;

		Mano mano = new Mano(parejas, jugadores, puntosParaTerminar);

		manos.add(mano);

		System.out.println("MANO NUM" + manos.size());
	}

	// TODO AGREGAR BUSCA UN JUGADOR EN UNA PAREJA

	public void cantarTruco(int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarTruco(idJugador);
	}

	public void cantarVale4(int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarVale4(idJugador);
	}

	public void cantarReTruco(int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarReTruco(idJugador);
	}

	public void cantarQuieroTruco(boolean quieroSiNo, int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarQuieroTruco(quieroSiNo, idJugador);
	}

	public void cantarQuieroEnvido(boolean quieroSiNo, int idJugador) {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).cantarQuieroEnvido(quieroSiNo, idJugador);
	}

	public void cantarEnvido(int idJugador) {
		this.manos.get(this.manos.size() - 1).cantarEnvido(idJugador);

	}

	public void sinCantar() {
		// TODO Auto-generated method stub
		this.manos.get(this.manos.size() - 1).sinCantar();

	}

	public void jugarCarta(int numero, String palo) throws JugadorException, CartaException {
		// TODO Auto-generated method stub
		
		this.manos.get(this.manos.size() - 1).jugarCarta(numero, palo);
		
	}

	// pasa el primer jugador al final del vecor para modificar la mano
	private void cambiarOrden() {
		jugadores.add(jugadores.get(0));
		jugadores.remove(0);
		
		System.out.println("ORDEN---------------------------");
		for (Jugador jugador : jugadores) {
			System.out.println(" "  + jugador.getNombre());
		}
	}
}
