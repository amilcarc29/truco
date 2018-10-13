package remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controlador.Controlador;
import controlador.ControladorUsuario;
import excepciones.UsuarioException;
import interfaces.InterfaceRemota;
import interfaces.InterfaceRemotaTruco;

public class ObjetoRemotoTruco extends UnicastRemoteObject implements InterfaceRemotaTruco {

	private static final long serialVersionUID = 7432516509801597745L;

	public ObjetoRemotoTruco() throws RemoteException {}

	@Override
	public void login(String apodo, String password) throws RemoteException {
		 try {
			new ControladorUsuario().getInstancia().loggearUsuario(apodo, password);
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
