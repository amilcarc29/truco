package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.ControladorArmadoJuegos;
import controlador.ControladorJuego;
import controlador.ControladorUsuario;
import dto.CartaDTO;
import dto.JuegoDTO;
import dto.JugadorDTO;
import dto.UsuarioDTO;
import excepciones.CartaException;
import excepciones.CategoriaException;
import excepciones.JuegoException;
import excepciones.JugadorException;
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
		}
	}

	@Override
	public List<JuegoDTO> getJuegosActivo(UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorArmadoJuegos.getInstancia().getJuegosActivo(usuario);
		} catch (CategoriaException e) {
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
		}
		return false;
	}

	@Override
	public List<CartaDTO> getCartas(JuegoDTO juego, UsuarioDTO usuario) throws RemoteException {
		try {
			return ControladorJuego.getInstancia().getCartas(juego, usuario);
		} catch (CategoriaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JuegoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void jugarCarta(JuegoDTO juego, CartaDTO carta, JugadorDTO jugador) throws RemoteException{
		try {
			ControladorJuego.getInstancia().jugarCarta(juego, carta, jugador);
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
		}
	}
}
