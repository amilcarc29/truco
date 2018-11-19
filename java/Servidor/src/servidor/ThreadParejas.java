package servidor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import controlador.ControladorArmadoJuegos;
import controlador.ControladorJuego;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.ChicoException;
import excepciones.ErrorCode;
import excepciones.JuegoException;
import excepciones.ManoException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import negocio.Jugador;
import negocio.JugadorIndividual;

public class ThreadParejas implements Runnable {

	public Integer loop = 10000;
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
			try {
				armarParejasIndividuales();
				
			} catch (UsuarioException e) {
				ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
				error.setDescripcion(e.getMessage());
				System.out.println(error.toString());
			} catch (ParejaException e) {
				ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
				error.setDescripcion(e.getMessage());
				System.out.println(error.toString());
			} catch (CategoriaException e) {
				ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
				error.setDescripcion(e.getMessage());
				System.out.println(error.toString());
			}
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
			ErrorCode error = ErrorCode.USUARIO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CategoriaException e) {
			ErrorCode error = ErrorCode.CATEGORIA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (JuegoException e) {
			ErrorCode error = ErrorCode.JUEGO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ParejaException e) {
			ErrorCode error = ErrorCode.PAREJA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ManoException e) {
			ErrorCode error = ErrorCode.MANO_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (CartaException e) {
			ErrorCode error = ErrorCode.CARTA_NO_ENCONTRADA;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (ChicoException e) {
			ErrorCode error = ErrorCode.CHICO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		} catch (MiembroException e) {
			ErrorCode error = ErrorCode.MIEMBRO_NO_ENCONTRADO;
			error.setDescripcion(e.getMessage());
			System.out.println(error.toString());
		}
	}

	public void armarParejasIndividuales() throws UsuarioException, ParejaException, CategoriaException {
		Vector<JugadorIndividual> jugadoresLibres = new Vector<>();
		
		//carga los juegos que quedaron guardados
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

				Thread.sleep(loop);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
