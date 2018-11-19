package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import controlador.ControladorArmadoJuegos;
import controlador.ControladorJuego;
import controlador.ControladorUsuario;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.BazaException;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.ChicoException;
import excepciones.JuegoException;
import excepciones.JugadaException;
import excepciones.JugadorException;
import excepciones.ManoException;
import excepciones.MiembroException;
import excepciones.ParejaException;
import excepciones.UsuarioException;
import interfaces.InterfaceRemotaTruco;

public class ObjetoRemotoTruco extends UnicastRemoteObject implements InterfaceRemotaTruco {

	private static final long serialVersionUID = 7432516509801597745L;

	public ObjetoRemotoTruco() throws RemoteException {
	}

	@Override
	public UsuarioDTO login(String apodo, String password) throws RemoteException {
		UsuarioDTO us = null;
		try {
			us = ControladorUsuario.getInstancia().loggearUsuario(apodo, password);
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us;
	}

	@Override
	public void altaUsuario(String apodo, String email, String password) throws RemoteException {
		try {
			ControladorUsuario.getInstancia().altaUsuario(apodo, email, password);
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void agregarAListaEspera(UsuarioDTO usuario) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			ControladorArmadoJuegos.getInstancia().agregarJugadorLibreAEspera(usuario);
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void armarPareja(UsuarioDTO u1, UsuarioDTO u2) throws RemoteException {
		try {
			ControladorArmadoJuegos.getInstancia().armarPareja(u1, u2);
		} catch (UsuarioException e) {
			e.printStackTrace();
		} catch (CategoriaException e) {
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<JuegoDTO> getJuegosActivo(UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorArmadoJuegos.getInstancia().getJuegosActivo(usuario);
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Devuelve una lista vacia en lugar de null
		return new ArrayList<JuegoDTO>();
	}
	
	@Override
	public JuegoDTO getJuegosById(int idJuego) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().buscarJuego(idJuego).toDTO();
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean esMiTurno(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().turnoJugador(juego, usuario);
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<CartaDTO> getCartas(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().getCartas(juego, usuario, false);
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public void cantarEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException{
		try {
			ControladorJuego.getInstancia().cantarEnvido(juego, usuario);
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void cantarRealEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException{
		try {
			ControladorJuego.getInstancia().cantarRealEnvido(juego, usuario);
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void cantarFaltaEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException{
		try {
			ControladorJuego.getInstancia().cantarFaltaEnvido(juego, usuario);
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void quieroEnvido(JuegoDTO juego) throws RemoteException {
		try {
			ControladorJuego.getInstancia().quieroEnvido(juego);
		} catch (JuegoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (CategoriaException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UsuarioException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MiembroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void noQuieroEnvido(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().noQuieroEnvido(juego, usuario);
		} catch (JuegoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (CategoriaException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UsuarioException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MiembroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void jugarCarta(JuegoDTO juego, CartaDTO carta, UsuarioDTO usuario) throws RemoteException{
		try {
			ControladorJuego.getInstancia().jugarCarta(juego, carta, usuario);
		} catch (JugadorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JuegoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (CategoriaException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UsuarioException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MiembroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BazaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JugadaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	@Override
	public void modificarUsario(String apodo, String password, String nuevoEmail, String nuevaPass, String nuevoApodo)
			throws RemoteException {
	
			try {
				ControladorUsuario.getInstancia().modificarUsuario(apodo, password, nuevoEmail, nuevaPass, nuevoApodo);
			} catch (UsuarioException | CategoriaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

	@Override
	public void cantarTruco(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().cantarTruco(juego, usuario);
		} catch (JuegoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (CategoriaException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UsuarioException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void cantarReTruco(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().cantarReTruco(juego, usuario);
		} catch (JuegoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (CategoriaException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UsuarioException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void cantarValeCuatro(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			ControladorJuego.getInstancia().cantarVale4(juego, usuario);
		} catch (JuegoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (CategoriaException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UsuarioException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void quieroTruco(JuegoDTO juego) throws RemoteException {
		try {
			ControladorJuego.getInstancia().quieroTruco(juego);
		} catch (JuegoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (CategoriaException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UsuarioException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void noQuieroTruco(JuegoDTO juego, UsuarioDTO us1) throws RemoteException {
		try {
			ControladorJuego.getInstancia().noQuieroTruco(us1, juego);
		} catch (JuegoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (CategoriaException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (UsuarioException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ManoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MiembroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ChicoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String tengoQueContestar(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().responderJugador(juego, usuario);
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParejaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean alguienTieneQueContestar(JuegoDTO juego) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().alguienTieneQueContestar(juego);
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
		return false;
	}

	
}
