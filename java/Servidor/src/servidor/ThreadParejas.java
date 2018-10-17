package servidor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import controlador.ControladorArmadoJuegos;
import controlador.ControladorJuego;
import excepciones.CategoriaException;
import excepciones.JuegoException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import negocio.Jugador;
import negocio.JugadorIndividual;
import negocio.Pareja;

public class ThreadParejas implements Runnable {
	public Integer loop = 1000;
	public boolean stop = false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		InputStream input = null;

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			input = new FileInputStream(classLoader.getResource("parejas.properties").getFile());
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			loop = Integer.valueOf(prop.getProperty("timeRunthread"));
			System.out.println("Hilo de parejas ,hilo cada " + loop + "ms");
			armarParejasIndividuales();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void juntarParejas(Vector<JugadorIndividual> jugadoresLibres) {
		System.out.println("Se encontraron 4 jugadores libres");
		try {

			
			ControladorArmadoJuegos.getInstancia().iniciarPartidaLibre(
					jugadoresLibres.get(0).getUsuario().toDTO(), jugadoresLibres.get(1).getUsuario().toDTO(),
					jugadoresLibres.get(2).getUsuario().toDTO(), jugadoresLibres.get(3).getUsuario().toDTO()
			);
			
			
			System.out.println("Parejas creadas");
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void armarParejasIndividuales() {
		
		Vector<JugadorIndividual> jugadoresLibres = new Vector<>();
		ControladorJuego.getInstancia();
		
		while (!stop) {
			try {
				System.out.println("buscando jugadores individuales");
				Vector<Jugador> jugadores = ControladorArmadoJuegos.getInstancia().getJugadoresEnEspera();
				
				if (!jugadores.isEmpty()) {
					for (Iterator<Jugador> iterator = jugadores.iterator(); iterator.hasNext();) {
						Jugador j = iterator.next();
						JugadorIndividual jugadorIndividual = ((JugadorIndividual) j);
						if (jugadoresLibres.size() < 4) {
							
							jugadoresLibres.add(jugadorIndividual);
							iterator.remove();
						} else {

							juntarParejas(jugadoresLibres);
							jugadoresLibres = new Vector<>();

						}

					}
					// sobrante
					if (jugadoresLibres.size() == 4)
						juntarParejas(jugadoresLibres);
				} else {
					System.out.println("no se encuentran jugadores libres");
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
