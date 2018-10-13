package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controlador.Controlador;
import controlador.ControladorArmadoJuegos;
import controlador.ControladorJuego;
import controlador.ControladorUsuario;
import dto.UsuarioDTO;
import excepciones.CategoriaException;
import excepciones.UsuarioException;
import interfaces.InterfaceRemota;
import interfaces.InterfaceRemotaTruco;

public class ObjetoRemotoTruco extends UnicastRemoteObject implements InterfaceRemotaTruco {

	private static final long serialVersionUID = 7432516509801597745L;

	public ObjetoRemotoTruco() throws RemoteException {
	}

	@Override
	public UsuarioDTO login(String apodo, String password) throws RemoteException {
		UsuarioDTO us = null;
		try {

			us = new ControladorUsuario().getInstancia().loggearUsuario(apodo, password);

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

			new ControladorUsuario().getInstancia().altaUsuario(apodo, email, password);

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

}
