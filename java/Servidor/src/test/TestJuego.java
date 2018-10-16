package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controlador.ControladorJuego;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.JuegoException;
import excepciones.JugadorException;
import negocio.Jugador;
import negocio.JugadorIndividual;
import negocio.Usuario;

public class TestJuego {

	static ControladorJuego ju;
	static Usuario us1 = new Usuario();

	static Jugador j1 = new JugadorIndividual(us1);
	static Jugador j2 = new JugadorIndividual(us1);
	static Jugador j3 = new JugadorIndividual(us1);
	static Jugador j4 = new JugadorIndividual(us1);

	public static void main(String[] args) throws JugadorException, CartaException, JuegoException, CategoriaException {

		// CONFLICTO CON NOMBRE JUGADOR
		// j1.setNombre("Emiliano");
		//
		// j2.setNombre("Lucas");
		//
		// j3.setNombre("Debi");
		//
		// j4.setNombre("Amilcar");

		// CONFLICTO CON CONSTRUCTOR GRUPOJUEGO
		// // GrupoJuego g = new GrupoJuego(j1, j2, j3, j4);
		//
		// g.setTipoJuego("LIBRE");
		//
		// ju = new ControladorJuego();
		// ju.iniciarJuego(g);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				System.out.println("");
				ju.imprimirDbg();

				try {
					if (ju.sePuedeCantarEnvido(0)) {

						String env = null;
						System.out.println("cantar envido  ?: ");
						env = br.readLine();

						if ((env != null) && (env.equals("S"))) {
							ju.cantarEnvido(0);

							System.out.println("quiere envido ?: ");
							env = br.readLine();
							if ((env != null) && (env.equals("S")))
								ju.cantarQuieroEnvido(0, true);
							else
								ju.cantarQuieroEnvido(0, false);

						}

					}
					String retruco = null;
					String truco = null;
					System.out.println("cantar truco  ?: ");
					truco = br.readLine();
					if ((truco != null) && (truco.equals("S"))) {
						ju.cantarTruco(0);

						System.out.println("quiere truco ?: ");
						truco = br.readLine();
						if ((truco != null) && (truco.equals("S")))
							ju.cantarQuieroTruco(0, true);
						else if ((truco != null) && (truco.equals("R"))) {
							ju.cantarReTruco(0);

							System.out.println("quiere retruco ?: ");
							retruco = br.readLine();
							if ((retruco != null) && (retruco.equals("S")))
								ju.cantarQuieroTruco(0, true);
							else
								ju.cantarQuieroTruco(0, false);

						} else
							ju.cantarQuieroTruco(0, false);

					} else {

						System.out.println("carta numero: ");
						int cn = Integer.parseInt(br.readLine());
						System.out.println("carta palo: ");
						String cp = br.readLine();

						ju.jugarCarta(0, cn, cp);
					}

				} catch (CartaException e) {

					System.out.println(e.getMessage());
				}

				if (ju.verificarFinJuego(0)) {
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

}
