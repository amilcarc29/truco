package delegado;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import dto.CartaDTO;
import dto.JuegoDTO;
import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import interfaces.InterfaceRemotaTruco;

public class BusinessDelegateTruco {

	private InterfaceRemotaTruco ir;

	public BusinessDelegateTruco() throws ComunicacionException {
		try {
			ir = (InterfaceRemotaTruco) Naming.lookup("//127.0.0.1/truco");
		} catch (MalformedURLException e) {
			throw new ComunicacionException("La direccion especificada no es correcta");
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		} catch (NotBoundException e) {
			throw new ComunicacionException("El servidor no esta disponible");
		}
	}

	public UsuarioDTO login(String usuario, String pass) throws ComunicacionException {
		try {
			return ir.login(usuario, pass);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}

	public void alta(String apodo, String email, String password) throws ComunicacionException {
		try {
			ir.altaUsuario(apodo, email, password);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}

	public void agregarAListaEspera(UsuarioDTO usuario) throws ComunicacionException {
		try {
			ir.agregarAListaEspera(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}

	public List<JuegoDTO> getJuegosActivo(UsuarioDTO usuario) throws ComunicacionException {
		try {
			return ir.getJuegosActivo(usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}
	public JuegoDTO getJuegosById(int juego) throws ComunicacionException {
		try {
			return ir.getJuegosById(juego);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}
	public boolean esMiTurno(JuegoDTO juego, UsuarioDTO usuario) throws ComunicacionException {
		try {
			return ir.esMiTurno(juego, usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}

	public List<CartaDTO> getCartas(JuegoDTO juego, UsuarioDTO usuario) throws ComunicacionException {
		try {
			return ir.getCartas(juego, usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}
	public List<CartaDTO> getCartasJugadas(JuegoDTO juego, UsuarioDTO usuario) throws ComunicacionException {
		try {
			return ir.getCartasJugadas(juego, usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}

	public void jugarCarta(JuegoDTO juego, CartaDTO carta, UsuarioDTO usuario) throws ComunicacionException {
		try {
			ir.jugarCarta(juego, carta, usuario);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}
	
	public void cantarEnvido(JuegoDTO juego) throws ComunicacionException {
		try {
			ir.cantarEnvido(juego);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}
	
	public void cantarRealEnvido(JuegoDTO juego) throws ComunicacionException{
		try {
			ir.cantarRealEnvido(juego);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}
	
	public void cantarFaltaEnvido(JuegoDTO juego) throws ComunicacionException{
		try {
			ir.cantarFaltaEnvido(juego);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}

	public void quieroEnvido(JuegoDTO juego) throws ComunicacionException {
		try {
			ir.quieroEnvido(juego);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
		
	}

	public void noQuieroEnvido(JuegoDTO juego, UsuarioDTO us1) throws ComunicacionException {
		try {
			ir.noQuieroEnvido(juego, us1);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
		
	}
	
	public void cantarTruco(JuegoDTO juego) throws ComunicacionException {
		try {
			ir.cantarTruco(juego);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
		
	}
	public void cantarReTruco(JuegoDTO juego) throws ComunicacionException {
		try {
			ir.cantarReTruco(juego);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
		
	}
	
	public void cantarValeCuatro(JuegoDTO juego) throws ComunicacionException {
		try {
			ir.cantarValeCuatro(juego);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
		
	}
	
	public void noQuieroTruco(JuegoDTO juego, UsuarioDTO us1) throws ComunicacionException {
		try {
			ir.noQuieroTruco(juego, us1);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
		
	}
	
	
	public void modificarUsuario(String apodo, String password, String nuevoEmail, String nuevaPass, String nuevoApodo) throws ComunicacionException {
		try {
			ir.modificarUsario(apodo, password, nuevoEmail, nuevaPass, nuevoApodo);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
		
	}
	
	
	
}
