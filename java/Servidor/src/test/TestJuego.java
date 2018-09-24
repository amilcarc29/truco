package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import negocio.GrupoJuego;
import negocio.Jugador;
import negocio.JugadorIndividual;
import negocio.Usuario;
import servidor.ControladorJuego;

public class TestJuego {

	static ControladorJuego ju;
	static Usuario us1 = new Usuario();

	static Jugador j1 = new JugadorIndividual(us1);
	static Jugador j2 = new JugadorIndividual(us1);
	static Jugador j3 = new JugadorIndividual(us1);
	static Jugador j4 = new JugadorIndividual(us1);

	public static void main(String[] args) {

		j1.setNombre("Emiliano");

		j2.setNombre("Lucas");

		j3.setNombre("Debi");

		j4.setNombre("Amilcar");

		GrupoJuego g = new GrupoJuego();
		g.agregarJugador(j1, j2);
		g.agregarJugador(j3, j4);

		g.setTipoJuego("LIBRE");
		ju = new ControladorJuego();
		ju.iniciarJuego(g);

		// ju.cantarEnvido(1, 1);
		// ju.cantarEnvido(1, 3);
		// ju.cantarQuieroEnvido(true, 1, 3);
		//
		//
		// ju.cantarTruco(1, 1);
		// ju.cantarReTruco(1, 3);
		// ju.cantarVale4(1, 1);
		//
		// ju.cantarQuieroTruco(false, 1, 3);

		// ju.jugarCarta(0, 0, 2);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				
				mostrarCartas();
				
				
				System.out.println("jug num: ");
				int idj = Integer.parseInt(br.readLine());
				if (idj < 0)
					break;

				System.out.println("carta numero: ");
				int cn = Integer.parseInt(br.readLine());

				System.out.println("carta palo: ");
				String cp = br.readLine();

				ju.jugarCarta(0, idj, cn, cp);

			} catch (NumberFormatException nfe) {
				System.err.println("Invalid Format!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void mostrarCartas() {

		System.out.println(" ------------------------PAREJA1---------------------------  ");
		System.out.println(j1.getNombre());
		j1.mostrarCartas();
		System.out.println(j2.getNombre());
		j2.mostrarCartas();
		System.out.println(" ------------------------PAREJA2---------------------------  ");
		System.out.println(j3.getNombre());
		j3.mostrarCartas();
		System.out.println(j4.getNombre());
		j4.mostrarCartas();
		System.out.println(" ------------------------------------------------------------");
		System.out.println(" ");


	}
}
