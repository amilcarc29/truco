package test;

import negocio.GrupoJuego;
import negocio.Jugador;
import negocio.JugadorIndividual;
import negocio.Usuario;
import servidor.ControladorJuego;

public class TestJuego {

	static ControladorJuego ju;

	public static void main(String[] args) {
		
		
		Usuario us1 = new Usuario();

		Jugador j1 = new JugadorIndividual(us1);
		j1.setNombre("Emiliano");

		Jugador j2 = new JugadorIndividual(us1);
		j2.setNombre("Lucas");

		Jugador j3 = new JugadorIndividual(us1);
		j3.setNombre("Debi");

		Jugador j4 = new JugadorIndividual(us1);
		j4.setNombre("Amilcar");

		GrupoJuego g = new GrupoJuego();
		g.agregarJugador(j1, j2);
		g.agregarJugador(j3, j4);

		g.setTipoJuego("LIBRE");
		ju = new ControladorJuego();
		ju.iniciarJuego(g);
		
		
		
	}

}
