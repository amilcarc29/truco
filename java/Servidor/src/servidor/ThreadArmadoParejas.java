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
import excepciones.JuegoException;
import excepciones.ManoException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void armarParejas() throws UsuarioException {
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
						if (parejas.size() < 2) {
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