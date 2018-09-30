package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import excepciones.CartaException;
import excepciones.JuegoException;
import excepciones.JugadorException;
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

	public static void main(String[] args) throws JugadorException, CartaException, JuegoException {

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

		// ju.jugarCarta(0, 0, 2);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		

		
		while (true) {
			try {
				System.out.println("  ");
				System.out.println("  ");

				mostrarCartas();
			

				System.out.println("carta numero: ");
				int cn = Integer.parseInt(br.readLine());

				System.out.println("carta palo: ");
				String cp = br.readLine();
				ju.jugarCarta(0, cn, cp);
				
				ju.contarPuntos(0);

				// ju.cantarEnvido(0, 1);
				// ju.cantarEnvido(0, 3);
				// ju.cantarQuieroEnvido(true, 0, 3);
				//
				// ju.cantarTruco(0, 1);
				// ju.cantarReTruco(0, 3);
				// ju.cantarVale4(0, 1);

				// ju.cantarQuieroTruco(false, 0, 3);
					

				if (ju.verificarFinJuego(0)){
					ju.sinCantar(0);
					break;
				}

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
