package delegado;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dto.UsuarioDTO;
import excepciones.ComunicacionException;
import interfaces.InterfaceRemota;
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
}
