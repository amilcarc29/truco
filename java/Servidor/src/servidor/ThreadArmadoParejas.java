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
import negocio.JugadorIndividual;
import negocio.Pareja;

public class ThreadArmadoParejas implements Runnable {

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
				armarParejas();
				
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

	public void juntarParejas(Vector<Pareja> parejasLibres) {
		System.out.println("Se encontraron 2 parejas libres");
		try {
			ControladorArmadoJuegos.getInstancia().iniciarPartidaEnPareja(
					parejasLibres.get(0), 
					parejasLibres.get(1)
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

	public void armarParejas() throws UsuarioException, ParejaException, CategoriaException {
		Vector<Pareja> parejasLibres = new Vector<>();
		
		//carga los juegos que quedaron guardados
		ControladorJuego.getInstancia();
		
		while (!stop) {
			try {
				System.out.print("buscando parejas");
				Vector <Pareja> parejas = ControladorArmadoJuegos.getInstancia().getParejasEnEspera();
				if (!parejas.isEmpty()){
					for (Iterator <Pareja> iterator = parejas.iterator(); iterator.hasNext();){
						Pareja p = iterator.next();
						Pareja pareja = p;
						if (parejasLibres.size() < 2) {
							parejasLibres.add(pareja);
							iterator.remove();
						} else {
							juntarParejas(parejasLibres);
							parejasLibres = new Vector <>();
						}
					}
					// sobrante
					if (parejasLibres.size() == 2)
						juntarParejas(parejasLibres);
				} else {
					System.out.println("no se encuentran parejas libres");
				}
				Thread.sleep(loop);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
