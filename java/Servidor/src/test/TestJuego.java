package test;

import negocio.GrupoJuego;
import negocio.Jugador;
import negocio.JugadorIndividual;
import servidor.ControladorJuego;

public class TestJuego {

	static ControladorJuego ju;

	public static void main(String[] args) {
		
		Jugador j1 = new JugadorIndividual();
		j1.setNombre("Emiliano");
		
		Jugador j2 = new JugadorIndividual();
		j2.setNombre("Lucas");

		Jugador j3 = new JugadorIndividual();
		j3.setNombre("Debi");

		Jugador j4 = new JugadorIndividual();
		j4.setNombre("Amilkar");

		GrupoJuego g = new GrupoJuego();
		g.agregarJugador(j1);
		g.agregarJugador(j2);
		g.agregarJugador(j3);
		g.agregarJugador(j4);

		g.setTipoJuego("LIBRE");
		ju = new ControladorJuego();
		ju.iniciarJuego(g);
	}

}
