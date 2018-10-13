package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRemotaTruco extends Remote {

	public void login(String usuario, String pass)throws RemoteException;
	
}
