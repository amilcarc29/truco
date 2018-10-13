package delegado;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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

	public void login(String usuario, String pass) throws ComunicacionException {
		try {
			ir.login(usuario, pass);
		} catch (RemoteException e) {
			throw new ComunicacionException("Error en las comunicaciones");
		}
	}

}
